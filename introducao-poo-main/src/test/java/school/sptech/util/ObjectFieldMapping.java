package school.sptech.util;

import java.lang.reflect.Field;

public record ObjectFieldMapping<T>(Class<T> clazz) {

    public Field get(String fieldName) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }
}
