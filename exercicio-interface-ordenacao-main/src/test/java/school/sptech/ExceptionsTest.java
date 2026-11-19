package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.NaoEncontradoException;
import school.sptech.exception.VagaIndisponivelException;

@DisplayName("Exceptions")
public class ExceptionsTest {

    @Nested
    @DisplayName("Exceptions 1. Herança")
    class HerancaTest {

        @Test
        @DisplayName("1. As exceptions devem ser unchecked")
        void test1() {
            assertAll(
                  () -> assertEquals(RuntimeException.class, DadoInvalidoException.class.getSuperclass(),
                        "DadoInvalidoException deve herdar de RuntimeException"),
                  () -> assertEquals(RuntimeException.class, NaoEncontradoException.class.getSuperclass(),
                        "NaoEncontradoException deve herdar de RuntimeException"),
                  () -> assertEquals(RuntimeException.class, VagaIndisponivelException.class.getSuperclass(),
                        "VagaIndisponivelException deve herdar de RuntimeException")
            );
        }
    }
}
