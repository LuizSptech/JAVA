package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Formato")
public class FormatoTest {

    @Nested
    @DisplayName("Formato 1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("Deve ser um enum com as constantes DIGITAL, COMUM e CAPA_DURA")
        void test1() {
            Class<Formato> clazz = Formato.class;

            assertAll(
                  () -> assertTrue(clazz.isEnum(), "Formato deve ser um enum"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("DIGITAL"),
                        "Deve possuir a constante DIGITAL"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("COMUM"),
                        "Deve possuir a constante COMUM"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("CAPA_DURA"),
                        "Deve possuir a constante CAPA_DURA")
            );
        }
    }
}
