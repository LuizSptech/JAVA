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
import school.sptech.factory.TuristaFactory;
import school.sptech.provider.turista.CalcularDiariaProvider;
import school.sptech.provider.turista.CalcularPontosFidelidadeProvider;
import school.sptech.provider.turista.CalcularTotalEstadiaProvider;

@DisplayName("Turista")
public class TuristaTest {

    static final String[] METODOS = {"calcularDiaria", "calcularPontosFidelidade", "getTipo"};

    @Nested
    @DisplayName("Turista 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Turista> clazz = Turista.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("guiaTuristico"),
                        "Deve possuir o atributo guiaTuristico"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("altaTemporada"),
                        "Deve possuir o atributo altaTemporada")
            );
        }
    }

    @Nested
    @DisplayName("Turista 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Turista> clazz = Turista.class;

            Stream<Executable> validacoes = Arrays.stream(METODOS)
                  .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome),
                        String.format("Deve sobrescrever o método %s", nome)));

            assertAll(validacoes);
        }
    }

    @Nested
    @DisplayName("Turista 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Turista> clazz = Turista.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Turista> clazz = Turista.class;
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
            Class<Turista> clazz = Turista.class;
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
            assertEquals(Hospede.class, Turista.class.getSuperclass(),
                  "Turista deve ser uma subclasse de Hospede");
        }
    }

    @Nested
    @DisplayName("Turista 4. Método - calcularDiaria")
    class MetodoCalcularDiariaTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularDiariaProvider.class)
        @DisplayName("Deve aplicar a alta temporada antes do guia")
        void test1(Boolean guiaTuristico, Boolean altaTemporada, Double expected) throws ReflectiveOperationException {
            Method metodo = Turista.class.getDeclaredMethod("calcularDiaria");

            Object obj = TuristaFactory.build("Ana", "111", 101, 2, 100.0, guiaTuristico, altaTemporada);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularDiaria deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Turista 5. Método - calcularTotalEstadia")
    class MetodoCalcularTotalEstadiaTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularTotalEstadiaProvider.class)
        @DisplayName("Deve calcular o total da estadia usando a diária do turista")
        void test1(Integer dias, Double consumo, Boolean guiaTuristico, Boolean altaTemporada, Double expected) throws ReflectiveOperationException {
            Method metodo = Turista.class.getSuperclass().getDeclaredMethod("calcularTotalEstadia");

            Object obj = TuristaFactory.build("Ana", "111", 101, dias, consumo, guiaTuristico, altaTemporada);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularTotalEstadia deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Turista 6. Método - calcularPontosFidelidade")
    class MetodoCalcularPontosFidelidadeTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularPontosFidelidadeProvider.class)
        @DisplayName("1 ponto a cada R$ 100,00 completos")
        void test1(Integer dias, Double consumo, Boolean guiaTuristico, Boolean altaTemporada, Integer expected) throws ReflectiveOperationException {
            Method metodo = Turista.class.getDeclaredMethod("calcularPontosFidelidade");

            Object obj = TuristaFactory.build("Ana", "111", 101, dias, consumo, guiaTuristico, altaTemporada);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, resposta,
                  "calcularPontosFidelidade deve retornar o valor correto");
        }
    }

    @Nested
    @DisplayName("Turista 7. Método - getTipo")
    class MetodoGetTipoTest {

        @Test
        @DisplayName("Deve retornar \"Turista\"")
        void test1() throws ReflectiveOperationException {
            Method metodo = Turista.class.getDeclaredMethod("getTipo");
            Object obj = TuristaFactory.build("Ana", "111", 101, 1, 0.0, false, false);

            assertEquals("Turista", metodo.invoke(obj));
        }
    }
}
