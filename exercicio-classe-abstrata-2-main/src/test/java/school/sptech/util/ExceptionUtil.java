package school.sptech.util;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class ExceptionUtil {

    public static void assertLanca(Class<?> esperada, Executable executavel) {
        Throwable lancada = capturar(executavel);

        Assertions.assertNotNull(lancada,
              String.format("Esperava %s, mas nenhuma exception foi lançada",
                    esperada.getSimpleName()));
        Assertions.assertTrue(esperada.isInstance(lancada),
              String.format("Esperava %s, mas foi lançada %s", esperada.getSimpleName(),
                    lancada.getClass().getSimpleName()));
    }

    public static void assertLancaExatamente(Class<?> esperada, Executable executavel) {
        Throwable lancada = capturar(executavel);

        Assertions.assertNotNull(lancada,
              String.format("Esperava %s, mas nenhuma exception foi lançada",
                    esperada.getSimpleName()));
        Assertions.assertEquals(esperada, lancada.getClass(),
              String.format("Esperava exatamente %s, mas foi lançada %s",
                    esperada.getSimpleName(), lancada.getClass().getSimpleName()));
    }

    public static void assertNaoLanca(Executable executavel) {
        Throwable lancada = capturar(executavel);

        Assertions.assertNull(lancada, () -> String.format("Não deveria lançar exception, mas foi lançada %s",
              lancada.getClass().getSimpleName()));
    }

    private static Throwable capturar(Executable executavel) {
        try {
            executavel.execute();
            return null;
        } catch (InvocationTargetException e) {
            return e.getCause();
        } catch (Throwable t) {
            return t;
        }
    }
}
