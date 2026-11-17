package school.sptech;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Observador")
public class ObservadorTest {

    @Nested
    @DisplayName("Observador 1. Interface")
    class InterfaceTest {

        @Test
        @DisplayName("1. Deve ser uma interface")
        void test1() {
            assertTrue(Observador.class.isInterface(), "Observador deve ser uma interface");
        }
    }

    @Nested
    @DisplayName("Observador 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertDoesNotThrow(
                  () -> Observador.class.getDeclaredMethod("receberPublicacao", Noticia.class),
                  "Deve possuir o método receberPublicacao(Noticia)");
        }

        @Test
        @DisplayName("2. receberPublicacao deve ser abstrato")
        void test2() throws ReflectiveOperationException {
            Method metodo = Observador.class.getDeclaredMethod("receberPublicacao", Noticia.class);

            assertTrue(Modifier.isAbstract(metodo.getModifiers()),
                  "O método receberPublicacao deve ser abstrato");
        }
    }
}
