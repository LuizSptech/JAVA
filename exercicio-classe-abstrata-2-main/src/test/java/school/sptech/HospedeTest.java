package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import school.sptech.exceptions.ValorInvalidoException;
import school.sptech.factory.HospedeFactory;
import school.sptech.provider.hospede.CalcularTotalEstadiaProvider;
import school.sptech.util.ExceptionUtil;

@DisplayName("Hospede")
public class HospedeTest {

    static final String[] METODOS = {"calcularDiaria", "calcularPontosFidelidade", "getTipo",
          "calcularTotalEstadia"};

    @Nested
    @DisplayName("Hospede 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Hospede> clazz = Hospede.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("documento"),
                        "Deve possuir o atributo documento"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("quarto"),
                        "Deve possuir o atributo quarto"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("dias"),
                        "Deve possuir o atributo dias"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("consumo"),
                        "Deve possuir o atributo consumo")
            );
        }
    }

    @Nested
    @DisplayName("Hospede 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Hospede> clazz = Hospede.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve possuir o método %s", nome)));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. calcularDiaria, calcularPontosFidelidade e getTipo devem ser abstratos")
        void test2() {
            Class<Hospede> clazz = Hospede.class;

            Stream<Executable> validacoes = Stream.of("calcularDiaria", "calcularPontosFidelidade",
                        "getTipo")
                  .map(nome -> () -> assertTrue(
                        Modifier.isAbstract(clazz.getDeclaredMethod(nome).getModifiers()),
                        String.format("%s deve ser abstrato", nome)));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. calcularTotalEstadia não deve ser abstrato")
        void test3() throws ReflectiveOperationException {
            Method metodo = Hospede.class.getDeclaredMethod("calcularTotalEstadia");
            assertFalse(Modifier.isAbstract(metodo.getModifiers()),
                  "calcularTotalEstadia deve ser implementado em Hospede");
        }
    }

    @Nested
    @DisplayName("Hospede 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Hospede> clazz = Hospede.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Hospede> clazz = Hospede.class;
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
            Class<Hospede> clazz = Hospede.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method setter = clazz.getDeclaredMethod(setName, campo.getType());
                          assertTrue(Modifier.isPublic(setter.getModifiers()));
                      }, String.format("Deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @Test
        @DisplayName("4. Classe deve ser abstrata")
        void test4() {
            assertTrue(Modifier.isAbstract(Hospede.class.getModifiers()),
                  "Hospede deve ser uma classe abstrata");
        }
    }

    @Nested
    @DisplayName("Hospede 4. Método - calcularTotalEstadia")
    class MetodoCalcularTotalEstadiaTest {

        @ParameterizedTest(name = "{index} - {3} dias, consumo {4}, diária {5}")
        @ArgumentsSource(CalcularTotalEstadiaProvider.class)
        @DisplayName("Deve calcular o total com desconto a partir do 8º dia e taxa de serviço")
        void test1(String nome, String documento, Integer quarto, Integer dias, Double consumo,
              Double diaria, Double expected)
              throws ReflectiveOperationException {
            Method metodo = Hospede.class.getDeclaredMethod("calcularTotalEstadia");

            Object obj = HospedeFactory.build(nome, documento, quarto, dias, consumo, diaria);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularTotalEstadia deve calcular corretamente o total da estadia");
        }
    }

    @Nested
    @DisplayName("Hospede 5. Validações")
    class ValidacoesTest {

        Object hospedeValido() throws ReflectiveOperationException {
            return HospedeFactory.build("Ana", "111", 101, 2, 50.0, 1000.0);
        }

        @ParameterizedTest(name = "{index} - dias = {0}")
        @NullSource
        @ValueSource(ints = {0, -1})
        @DisplayName("setDias deve lançar ValorInvalidoException e manter o valor anterior")
        void test1(Integer dias) throws ReflectiveOperationException {
            Object obj = hospedeValido();
            Method setter = Hospede.class.getDeclaredMethod("setDias", Integer.class);

            assertAll(
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> setter.invoke(obj, dias)),
                  () -> assertEquals(2, getCampo(obj, "dias"),
                        "O valor de dias não deve ser alterado")
            );
        }

        @ParameterizedTest(name = "{index} - consumo = {0}")
        @NullSource
        @ValueSource(doubles = {-0.01, -100.0})
        @DisplayName("setConsumo deve lançar ValorInvalidoException e manter o valor anterior")
        void test2(Double consumo) throws ReflectiveOperationException {
            Object obj = hospedeValido();
            Method setter = Hospede.class.getDeclaredMethod("setConsumo", Double.class);

            assertAll(
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> setter.invoke(obj, consumo)),
                  () -> assertEquals(50.0, getCampo(obj, "consumo"),
                        "O valor do consumo não deve ser alterado")
            );
        }

        @Test
        @DisplayName("Setters devem aceitar 1 dia e consumo zero")
        void test3() throws ReflectiveOperationException {
            Object obj = hospedeValido();
            Method setDias = Hospede.class.getDeclaredMethod("setDias", Integer.class);
            Method setConsumo = Hospede.class.getDeclaredMethod("setConsumo", Double.class);

            assertAll(
                  () -> ExceptionUtil.assertNaoLanca(() -> setDias.invoke(obj, 1)),
                  () -> ExceptionUtil.assertNaoLanca(() -> setConsumo.invoke(obj, 0.0)),
                  () -> assertEquals(1, getCampo(obj, "dias")),
                  () -> assertEquals(0.0, getCampo(obj, "consumo"))
            );
        }

        @Test
        @DisplayName("Construtor deve aplicar as mesmas validações dos setters")
        void test4() {
            assertAll(
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> HospedeFactory.construir("Ana", "111", 101, 0, 50.0)),
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> HospedeFactory.construir("Ana", "111", 101, null, 50.0)),
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> HospedeFactory.construir("Ana", "111", 101, 2, -1.0)),
                  () -> ExceptionUtil.assertLanca(ValorInvalidoException.class,
                        () -> HospedeFactory.construir("Ana", "111", 101, 2, null)),
                  () -> ExceptionUtil.assertNaoLanca(
                        () -> HospedeFactory.construir("Ana", "111", null, 1, 0.0))
            );
        }
    }

    static Object getCampo(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Field campo = Hospede.class.getDeclaredField(nomeCampo);
        campo.trySetAccessible();
        return campo.get(obj);
    }
}
