package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Raridade")
public class RaridadeTest {

    @Nested
    @DisplayName("Raridade 1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("Deve ser um enum com as constantes COMUM, RARO e MUITO_RARO")
        void test1() {
            Class<Raridade> clazz = Raridade.class;

            assertAll(
                  () -> assertTrue(clazz.isEnum(), "Raridade deve ser um enum"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("COMUM"),
                        "Deve possuir a constante COMUM"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("RARO"),
                        "Deve possuir a constante RARO"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("MUITO_RARO"),
                        "Deve possuir a constante MUITO_RARO")
            );
        }
    }

    @Nested
    @DisplayName("Raridade 2. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve possuir o atributo adicionalPorcentagem do tipo Double")
        void test1() {
            Field campo = assertDoesNotThrow(
                  () -> Raridade.class.getDeclaredField("adicionalPorcentagem"),
                  "Deve possuir o atributo adicionalPorcentagem");

            assertEquals(Double.class, campo.getType(), "adicionalPorcentagem deve ser Double");
        }
    }

    @Nested
    @DisplayName("Raridade 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributo deve ser privado e final")
        void test1() throws ReflectiveOperationException {
            Field campo = Raridade.class.getDeclaredField("adicionalPorcentagem");

            assertAll(
                  () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        "adicionalPorcentagem deve ser privado"),
                  () -> assertTrue(Modifier.isFinal(campo.getModifiers()),
                        "adicionalPorcentagem deve ser final")
            );
        }

        @Test
        @DisplayName("2. Atributo deve possuir apenas getter")
        void test2() {
            assertAll(
                  () -> assertDoesNotThrow(() -> {
                      Method getter = Raridade.class.getDeclaredMethod("getAdicionalPorcentagem");
                      assertTrue(Modifier.isPublic(getter.getModifiers()),
                            "getAdicionalPorcentagem deve ser público");
                  }, "Deve possuir o getter getAdicionalPorcentagem"),
                  () -> assertThrows(NoSuchMethodException.class,
                        () -> Raridade.class.getDeclaredMethod("setAdicionalPorcentagem",
                              Double.class),
                        "Não deve possuir o setter setAdicionalPorcentagem")
            );
        }
    }

    @Nested
    @DisplayName("Raridade 4. Adicional Porcentagem")
    class AdicionalPorcentagemTest {

        @ParameterizedTest(name = "{index}. {0} deve ter adicional de {1}")
        @CsvSource({
              "COMUM, 0.0",
              "RARO, 10.0",
              "MUITO_RARO, 20.0"
        })
        @DisplayName("Deve possuir o adicional correto para cada raridade")
        void test1(String constante, Double expected) throws ReflectiveOperationException {
            Raridade raridade = Raridade.valueOf(constante);
            Field campo = Raridade.class.getDeclaredField("adicionalPorcentagem");
            campo.trySetAccessible();
            Method getter = Raridade.class.getDeclaredMethod("getAdicionalPorcentagem");

            assertAll(
                  () -> assertEquals(expected, campo.get(raridade),
                        "O atributo adicionalPorcentagem deve ter o valor correto"),
                  () -> assertEquals(expected, getter.invoke(raridade),
                        "getAdicionalPorcentagem deve retornar o valor do atributo")
            );
        }
    }
}
