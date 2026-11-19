package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

@DisplayName("Turno")
public class TurnoTest {

    static final String[] CONSTANTES = {"MANHA", "TARDE", "NOITE"};

    @Nested
    @DisplayName("Turno 1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("1. Deve ser um enum")
        void test1() {
            assertTrue(Turno.class.isEnum(), "Turno deve ser um enum");
        }

        @Test
        @DisplayName("2. Validar Constantes")
        void test2() {
            Stream<Executable> validacoes = Arrays.stream(CONSTANTES)
                  .map(nome -> () -> assertDoesNotThrow(() -> Turno.class.getDeclaredField(nome),
                        String.format("Deve possuir a constante %s", nome)));

            assertAll(validacoes);
        }
    }
}
