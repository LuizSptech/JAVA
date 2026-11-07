package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.FinanciamentoPJFactory;
import school.sptech.financiamento.Financiamento;
import school.sptech.financiamento.FinanciamentoPJ;
import school.sptech.provider.financiamento.pj.CalcularValorFinanciamentoPjProvider;

@DisplayName("FinanciamentoPJ")
public class FinanciamentoPJTest {

    static final String[] METODOS = {"calcularValorFinanciamento"};

    @Nested
    @DisplayName("FinanciamentoPJ 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<FinanciamentoPJ> clazz = FinanciamentoPJ.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("empresa"),
                        "Deve possuir o atributo empresa")
            );
        }
    }

    @Nested
    @DisplayName("FinanciamentoPJ 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<FinanciamentoPJ> clazz = FinanciamentoPJ.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve sobrescrever o método %s", nome)));

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("FinanciamentoPJ 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<FinanciamentoPJ> clazz = FinanciamentoPJ.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<FinanciamentoPJ> clazz = FinanciamentoPJ.class;
            List<Method> metodos = new ArrayList<>();

            for (String nome : METODOS) {
                try {
                    metodos.add(clazz.getDeclaredMethod(nome));
                } catch (ReflectiveOperationException ignored) {
                }
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir getters e setters")
        void test3() {
            Class<FinanciamentoPJ> clazz = FinanciamentoPJ.class;
            Field[] campos = clazz.getDeclaredFields();

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

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method setter = clazz.getDeclaredMethod(setName, campo.getType());
                          assertTrue(Modifier.isPublic(setter.getModifiers()),
                                String.format("Setter %s deve ser público", setName));
                      }, String.format("Deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @Test
        @DisplayName("4. Deve ser uma subclasse de Financiamento")
        void test4() {
            assertEquals(Financiamento.class, FinanciamentoPJ.class.getSuperclass(),
                  "FinanciamentoPJ deve ser uma subclasse de Financiamento");
        }
    }

    @Nested
    @DisplayName("FinanciamentoPJ 4. Método - calcularValorFinanciamento")
    class MetodoCalcularValorFinanciamentoTest {

        @ParameterizedTest(name = "{index} - empresa {1}, valor esperado {2}")
        @ArgumentsSource(CalcularValorFinanciamentoPjProvider.class)
        @DisplayName("Deve calcular o valor final do veículo + juros do tipo da empresa")
        void test1(Object veiculo, String tipoEmpresa, Double expected)
              throws ReflectiveOperationException {
            Method metodo = FinanciamentoPJ.class.getDeclaredMethod("calcularValorFinanciamento");

            Object obj = FinanciamentoPJFactory.build(1, 1, veiculo, tipoEmpresa);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularValorFinanciamento deve calcular corretamente o valor do financiamento");
        }
    }
}
