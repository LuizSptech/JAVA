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
import school.sptech.provider.veiculo.VeiculoCalcularValorFinalProvider;

@DisplayName("Veiculo")
public class VeiculoTest {

    static final String[] METODOS = {"calcularValorFinal"};

    @Nested
    @DisplayName("Veiculo 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Veiculo> clazz = Veiculo.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("placa"),
                        "Deve possuir o atributo placa"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("modelo"),
                        "Deve possuir o atributo modelo"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("marca"),
                        "Deve possuir o atributo marca"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("precoBase"),
                        "Deve possuir o atributo precoBase"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("anosUso"),
                        "Deve possuir o atributo anosUso"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("kmRodados"),
                        "Deve possuir o atributo kmRodados")
            );
        }
    }

    @Nested
    @DisplayName("Veiculo 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Veiculo> clazz = Veiculo.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve possuir o método %s", nome)));

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("Veiculo 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Veiculo> clazz = Veiculo.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Veiculo> clazz = Veiculo.class;
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
            Class<Veiculo> clazz = Veiculo.class;
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
    }

    @Nested
    @DisplayName("Veiculo 4. Método - calcularValorFinal")
    class MetodoCalcularValorFinalTest {

        @ParameterizedTest(name = "{index} - valor final esperado {1}")
        @ArgumentsSource(VeiculoCalcularValorFinalProvider.class)
        @DisplayName("Deve calcular corretamente o valor final do veículo")
        void test1(Object veiculo, Double expected) throws ReflectiveOperationException {
            Method metodo = Veiculo.class.getDeclaredMethod("calcularValorFinal");

            Object resposta = metodo.invoke(veiculo);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularValorFinal deve calcular corretamente o valor final do veículo");
        }
    }
}
