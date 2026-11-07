package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("TipoEmpresa")
public class TipoEmpresaTest {

    static final String[] CONSTANTES = {"MEI", "ME", "LTDA", "SA", "ONG", "COOPERATIVA"};

    @Nested
    @DisplayName("TipoEmpresa 1. Constantes")
    class ConstantesTest {

        @Test
        @DisplayName("1. Validar Constantes")
        void test1() {
            Class<TipoEmpresa> clazz = TipoEmpresa.class;

            Stream<Executable> validacoes = Arrays.stream(CONSTANTES)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredField(nome),
                        String.format("Deve possuir a constante %s", nome)));

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("TipoEmpresa 2. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<TipoEmpresa> clazz = TipoEmpresa.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("juros"),
                        "Deve possuir o atributo juros")
            );
        }
    }

    @Nested
    @DisplayName("TipoEmpresa 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados e Finais")
        void test1() throws ReflectiveOperationException {
            Class<TipoEmpresa> clazz = TipoEmpresa.class;
            Field[] campos = {clazz.getDeclaredField("juros")};

            Stream<Executable> validacoesPrivado = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            Stream<Executable> validacoesFinal = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isFinal(campo.getModifiers()),
                        String.format("%s deve ser final", campo.getName())));

            assertAll(Stream.concat(validacoesPrivado, validacoesFinal));
        }

        @Test
        @DisplayName("2. Atributos devem possuir apenas getters")
        void test2() throws ReflectiveOperationException {
            Class<TipoEmpresa> clazz = TipoEmpresa.class;
            Field[] campos = {clazz.getDeclaredField("juros")};

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()),
                                String.format("Getter %s deve ser público", getName));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            assertAll(validacoesGetter);
        }
    }

    @Nested
    @DisplayName("TipoEmpresa 4. Juros")
    class JurosTest {

        @ParameterizedTest(name = "{index} - {0} deve ter juros de {1}")
        @CsvSource({
              "MEI, 0.03",
              "ME, 0.05",
              "LTDA, 0.1",
              "SA, 0.15",
              "ONG, 0.0",
              "COOPERATIVA, 0.01"
        })
        @DisplayName("Deve possuir os juros corretos para cada tipo de empresa")
        void test1(String constante, Double expected) throws ReflectiveOperationException {
            Field campo = TipoEmpresa.class.getDeclaredField("juros");
            campo.trySetAccessible();

            Object resposta = campo.get(TipoEmpresa.valueOf(constante));

            assertEquals(expected, (Double) resposta, 0.0001,
                  String.format("%s deve possuir juros de %s", constante, expected));
        }
    }
}
