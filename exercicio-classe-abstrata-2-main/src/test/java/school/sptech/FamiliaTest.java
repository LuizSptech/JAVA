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
import school.sptech.factory.FamiliaFactory;
import school.sptech.provider.familia.CalcularDiariaProvider;
import school.sptech.provider.familia.CalcularPontosFidelidadeProvider;
import school.sptech.provider.familia.CalcularTotalEstadiaProvider;

@DisplayName("Familia")
public class FamiliaTest {

    static final String[] METODOS = {"calcularDiaria", "calcularTotalEstadia", "calcularPontosFidelidade", "getTipo"};

    @Nested
    @DisplayName("Familia 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Familia> clazz = Familia.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("qtdCriancas"),
                        "Deve possuir o atributo qtdCriancas")
            );
        }
    }

    @Nested
    @DisplayName("Familia 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Familia> clazz = Familia.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve sobrescrever o método %s", nome)));

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("Familia 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Familia> clazz = Familia.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Familia> clazz = Familia.class;
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
            Class<Familia> clazz = Familia.class;
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
        @DisplayName("4. Deve ser uma subclasse de Hospede")
        void test4() {
            assertEquals(Hospede.class, Familia.class.getSuperclass(),
                  "Familia deve ser uma subclasse de Hospede");
        }
    }

    @Nested
    @DisplayName("Familia 4. Método - calcularDiaria")
    class MetodoCalcularDiariaTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularDiariaProvider.class)
        @DisplayName("Deve cobrar apenas a 3ª e a 4ª criança")
        void test1(Integer qtdCriancas, Double expected) throws ReflectiveOperationException {
            Method metodo = Familia.class.getDeclaredMethod("calcularDiaria");

            Object obj = FamiliaFactory.build("Silva", "333", 303, 2, 0.0, qtdCriancas);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularDiaria deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Familia 5. Método - calcularTotalEstadia")
    class MetodoCalcularTotalEstadiaTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularTotalEstadiaProvider.class)
        @DisplayName("Deve reaproveitar o cálculo de Hospede e aplicar 5% acima de 5 dias")
        void test1(Integer dias, Double consumo, Integer qtdCriancas, Double expected) throws ReflectiveOperationException {
            Method metodo = Familia.class.getDeclaredMethod("calcularTotalEstadia");

            Object obj = FamiliaFactory.build("Silva", "333", 303, dias, consumo, qtdCriancas);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularTotalEstadia deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Familia 6. Método - calcularPontosFidelidade")
    class MetodoCalcularPontosFidelidadeTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularPontosFidelidadeProvider.class)
        @DisplayName("1 ponto a cada R$ 200,00 completos + 5 por criança")
        void test1(Integer dias, Double consumo, Integer qtdCriancas, Integer expected) throws ReflectiveOperationException {
            Method metodo = Familia.class.getDeclaredMethod("calcularPontosFidelidade");

            Object obj = FamiliaFactory.build("Silva", "333", 303, dias, consumo, qtdCriancas);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, resposta,
                  "calcularPontosFidelidade deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Familia 7. Método - getTipo")
    class MetodoGetTipoTest {

        @Test
        @DisplayName("Deve retornar \"Familia\"")
        void test1() throws ReflectiveOperationException {
            Method metodo = Familia.class.getDeclaredMethod("getTipo");
            Object obj = FamiliaFactory.build("Silva", "333", 303, 1, 0.0, 0);

            assertEquals("Familia", metodo.invoke(obj));
        }
    }
}
