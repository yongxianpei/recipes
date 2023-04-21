public static <T> T compareAndSetNull(T a, T b) throws IllegalAccessException {
    if (!a.getClass().equals(b.getClass())) {
        throw new IllegalArgumentException("Instances must be of the same class");
    }
    T result = null;
    Field[] fields = a.getClass().getDeclaredFields();
    for (Field field : fields) {
        field.setAccessible(true);
        Object aValue = field.get(a);
        Object bValue = field.get(b);
        if (aValue == null && bValue == null) {
            continue;
        }
        if (aValue == null || !aValue.equals(bValue)) {
            if (result == null) {
                result = (T) a.getClass().newInstance();
            }
            field.set(result, null);
        }
    }
    return result;
}


public static <T> T compareAndSetNull(T a, T b) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    if (!a.getClass().equals(b.getClass())) {
        throw new IllegalArgumentException("Instances must be of the same class");
    }
    T result = null;
    Field[] fields = a.getClass().getDeclaredFields();
    for (Field field : fields) {
        field.setAccessible(true);
        Object aValue = field.get(a);
        Object bValue = field.get(b);
        if (aValue == null && bValue == null) {
            continue;
        }
        if (aValue == null || !aValue.equals(bValue)) {
            if (result == null) {
                result = a.getClass().getDeclaredConstructor().newInstance();
            }
            field.set(result, null);
        }
    }
    return result;
}
