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

@DisplayName("Tema")
public class TemaTest {

    static final String[] CONSTANTES = {"POLITICA", "TECNOLOGIA", "ENTRETENIMENTO", "ESPORTE"};

    @Nested
    @DisplayName("Tema 1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("1. Deve ser um enum")
        void test1() {
            assertTrue(Tema.class.isEnum(), "Tema deve ser um enum");
        }

        @Test
        @DisplayName("2. Validar Constantes")
        void test2() {
            Class<Tema> clazz = Tema.class;

            Stream<Executable> validacoes = Arrays.stream(CONSTANTES)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredField(nome),
                        String.format("Deve possuir a constante %s", nome)));

            assertAll(validacoes);
        }
    }
}
