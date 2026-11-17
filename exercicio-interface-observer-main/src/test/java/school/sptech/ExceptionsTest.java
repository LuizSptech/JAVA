package school.sptech;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Exceptions")
public class ExceptionsTest {

    @Nested
    @DisplayName("Exceptions 1. Herança")
    class HerancaTest {

        @Test
        @DisplayName("1. NoticiaInvalidaException deve ser unchecked")
        void test1() {
            assertEquals(RuntimeException.class, NoticiaInvalidaException.class.getSuperclass(),
                  "NoticiaInvalidaException deve herdar de RuntimeException");
        }
    }
}
