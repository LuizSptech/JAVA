package school.sptech;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.exceptions.ExceptionsProvider;

@DisplayName("Exceptions")
public class ExceptionsTest {

    @Nested
    @DisplayName("Exceptions 1. Herança")
    class HerancaTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(ExceptionsProvider.class)
        @DisplayName("Deve herdar da classe correta")
        void test1(Class<?> exception, Class<?> superclasse, String cenario) {
            assertEquals(superclasse, exception.getSuperclass());
        }
    }

    @Nested
    @DisplayName("Exceptions 2. Construtor")
    class ConstrutorTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(ExceptionsProvider.class)
        @DisplayName("Deve possuir construtor que recebe a mensagem")
        void test1(Class<?> exception, Class<?> superclasse, String cenario) {
            Constructor<?> construtor = assertDoesNotThrow(
                  () -> exception.getConstructor(String.class),
                  String.format("%s deve possuir um construtor público que recebe uma String",
                        exception.getSimpleName()));

            Throwable instancia = (Throwable) assertDoesNotThrow(
                  () -> construtor.newInstance("Mensagem de teste"));

            assertEquals("Mensagem de teste", instancia.getMessage(),
                  "A mensagem deve ser repassada para o construtor da superclasse");
        }
    }
}
