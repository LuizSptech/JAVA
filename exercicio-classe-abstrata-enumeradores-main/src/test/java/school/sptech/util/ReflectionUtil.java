package school.sptech.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static Object getCampo(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Class<?> current = obj.getClass();

        while (current != null) {
            try {
                Field campo = current.getDeclaredField(nomeCampo);
                campo.trySetAccessible();
                return campo.get(obj);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }

        throw new NoSuchFieldException(nomeCampo);
    }
}
