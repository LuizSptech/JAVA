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
import school.sptech.exceptions.PagamentoInvalidoException;
import school.sptech.factory.FinanciamentoFactory;
import school.sptech.factory.VeiculoFactory;
import school.sptech.financiamento.Financiamento;
import school.sptech.provider.financiamento.CalcularValorAPagarProvider;
import school.sptech.provider.financiamento.CalcularValorParcelaPagaProvider;
import school.sptech.provider.financiamento.CalcularValorParcelaProvider;
import school.sptech.provider.financiamento.FinanciamentoValorInvalidoProvider;
import school.sptech.util.ExceptionUtil;

@DisplayName("Financiamento")
public class FinanciamentoTest {

    static final String[] METODOS = {"calcularValorFinanciamento", "calcularValorParcela",
          "calcularValorPago", "calcularValorAPagar"};

    static Object getCampo(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Field campo = Financiamento.class.getDeclaredField(nomeCampo);
        campo.trySetAccessible();
        return campo.get(obj);
    }

    @Nested
    @DisplayName("Financiamento 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Financiamento> clazz = Financiamento.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("parcelas"),
                        "Deve possuir o atributo parcelas"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("parcelasPagas"),
                        "Deve possuir o atributo parcelasPagas"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("veiculo"),
                        "Deve possuir o atributo veiculo")
            );
        }
    }

    @Nested
    @DisplayName("Financiamento 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Financiamento> clazz = Financiamento.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve possuir o método %s", nome)));

            Executable validacaoPagarParcela = () -> assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("pagarParcela", Double.class),
                  "Deve possuir o método pagarParcela(Double)");

            assertAll(Stream.concat(validacoes, Stream.of(validacaoPagarParcela)));
        }

        @Test
        @DisplayName("2. calcularValorFinanciamento deve ser abstrato")
        void test2() throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("calcularValorFinanciamento");

            assertTrue(Modifier.isAbstract(metodo.getModifiers()),
                  "O método calcularValorFinanciamento deve ser abstrato");
        }
    }

    @Nested
    @DisplayName("Financiamento 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Financiamento> clazz = Financiamento.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Financiamento> clazz = Financiamento.class;
            List<Method> metodos = new ArrayList<>();

            for (String nome : METODOS) {
                try {
                    metodos.add(clazz.getDeclaredMethod(nome));
                } catch (ReflectiveOperationException ignored) {
                }
            }

            try {
                metodos.add(clazz.getDeclaredMethod("pagarParcela", Double.class));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir getters e setters")
        void test3() {
            Class<Financiamento> clazz = Financiamento.class;
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
        @DisplayName("4. Classe deve ser abstrata")
        void test4() {
            assertTrue(Modifier.isAbstract(Financiamento.class.getModifiers()),
                  "Financiamento deve ser uma classe abstrata");
        }
    }

    @Nested
    @DisplayName("Financiamento 4. Método - calcularValorParcela")
    class MetodoCalcularValorParcelaTest {

        @ParameterizedTest(name = "{index} - {0} parcelas, valor da parcela esperado {2}")
        @ArgumentsSource(CalcularValorParcelaProvider.class)
        @DisplayName("Deve dividir o valor do financiamento pela quantidade de parcelas")
        void test1(Integer parcelas, Object veiculo, Double expected)
              throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("calcularValorParcela");

            Object obj = FinanciamentoFactory.build(parcelas, parcelas, veiculo);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularValorParcela deve calcular corretamente o valor da parcela de acordo com o valor total do financiamento");
        }
    }

    @Nested
    @DisplayName("Financiamento 5. Método - pagarParcela")
    class MetodoPagarParcelaTest {

        @ParameterizedTest(name = "{index} - {0} parcelas, {1} pagas, valor {2}")
        @ArgumentsSource(FinanciamentoValorInvalidoProvider.class)
        @DisplayName("Deve lançar PagamentoInvalidoException e não alterar as parcelas pagas")
        void test1(Integer parcelas, Integer parcelasPagas, Double valor)
              throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("pagarParcela", Double.class);

            Object obj = FinanciamentoFactory.build(parcelas, parcelasPagas,
                  VeiculoFactory.build("AAAA-123", "uno", "fiat", 1000.0, 0, 0));

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(PagamentoInvalidoException.class,
                        () -> metodo.invoke(obj, valor)),
                  () -> assertEquals(parcelasPagas, getCampo(obj, "parcelasPagas"),
                        "A quantidade de parcelas pagas não deve ser alterada")
            );
        }

        @Test
        @DisplayName("Deve aumentar em um a quantidade de parcelas pagas")
        void test2() throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("pagarParcela", Double.class);

            Object obj = FinanciamentoFactory.build(10, 0,
                  VeiculoFactory.build("AAAA-123", "uno", "fiat", 1000.0, 0, 0));

            assertAll(
                  () -> ExceptionUtil.assertNaoLanca(() -> metodo.invoke(obj, 110.0)),
                  () -> assertEquals(1, getCampo(obj, "parcelasPagas"),
                        "Deve aumentar em um a quantidade de parcelas pagas")
            );
        }
    }

    @Nested
    @DisplayName("Financiamento 6. Método - calcularValorPago")
    class MetodoCalcularValorPagoTest {

        @ParameterizedTest(name = "{index} - {0} parcelas, {1} pagas, valor pago esperado {3}")
        @ArgumentsSource(CalcularValorParcelaPagaProvider.class)
        @DisplayName("Deve calcular o valor pago até o momento")
        void test1(Integer parcelas, Integer parcelasPagas, Object veiculo, Double expected)
              throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("calcularValorPago");

            Object obj = FinanciamentoFactory.build(parcelas, parcelasPagas, veiculo);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularValorPago deve calcular corretamente o valor pago do financiamento");
        }
    }

    @Nested
    @DisplayName("Financiamento 7. Método - calcularValorAPagar")
    class MetodoCalcularValorAPagarTest {

        @ParameterizedTest(name = "{index} - {0} parcelas, {1} pagas, valor a pagar esperado {3}")
        @ArgumentsSource(CalcularValorAPagarProvider.class)
        @DisplayName("Deve calcular o valor restante a pagar")
        void test1(Integer parcelas, Integer parcelasPagas, Object veiculo, Double expected)
              throws ReflectiveOperationException {
            Method metodo = Financiamento.class.getDeclaredMethod("calcularValorAPagar");

            Object obj = FinanciamentoFactory.build(parcelas, parcelasPagas, veiculo);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularValorAPagar deve calcular corretamente o valor a pagar do financiamento");
        }
    }
}
