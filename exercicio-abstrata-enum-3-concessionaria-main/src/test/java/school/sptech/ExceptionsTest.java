package school.sptech;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
