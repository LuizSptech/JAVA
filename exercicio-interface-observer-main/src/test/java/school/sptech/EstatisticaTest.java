package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
import school.sptech.provider.estatistica.ReceberPublicacaoProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Estatistica")
public class EstatisticaTest {

    @Nested
    @DisplayName("Estatistica 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            assertDoesNotThrow(() -> Estatistica.class.getDeclaredField("qtdPublicacoesRecebidas"),
                  "Deve possuir o atributo qtdPublicacoesRecebidas");
        }
    }

    @Nested
    @DisplayName("Estatistica 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Estatistica> clazz = Estatistica.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getPercentual"),
                        "Deve possuir o método getPercentual"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("receberPublicacao", Noticia.class),
                        "Deve possuir o método receberPublicacao")
            );
        }

        @Test
        @DisplayName("2. getPercentual deve ser abstrato")
        void test2() throws ReflectiveOperationException {
            Method metodo = Estatistica.class.getDeclaredMethod("getPercentual");

            assertTrue(Modifier.isAbstract(metodo.getModifiers()),
                  "O método getPercentual deve ser abstrato");
        }
    }

    @Nested
    @DisplayName("Estatistica 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Estatistica> clazz = Estatistica.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Estatistica> clazz = Estatistica.class;
            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("getPercentual"));
                metodos.add(clazz.getDeclaredMethod("receberPublicacao", Noticia.class));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Estatistica> clazz = Estatistica.class;
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
                  .map(campo -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertThrows(NoSuchMethodException.class,
                            () -> clazz.getDeclaredMethod(setName, campo.getType()),
                            String.format("%s não deve possuir setter", campo.getName()));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @Test
        @DisplayName("4. Classe deve ser abstrata")
        void test4() {
            assertTrue(Modifier.isAbstract(Estatistica.class.getModifiers()),
                  "A classe Estatistica deve ser abstrata");
        }

        @Test
        @DisplayName("5. Deve implementar a interface Observador")
        void test5() {
            List<Class<?>> interfaces = Arrays.asList(Estatistica.class.getInterfaces());

            assertTrue(interfaces.contains(Observador.class),
                  "Estatistica deve implementar a interface Observador");
        }
    }

    @Nested
    @DisplayName("Estatistica 4. Método - receberPublicacao")
    class ReceberPublicacaoTest {

        @ParameterizedTest(name = "{index}. {1} publicações recebidas -> {3}")
        @ArgumentsSource(ReceberPublicacaoProvider.class)
        @DisplayName("Deve incrementar a quantidade de publicações recebidas")
        void test1(Object estatistica, Integer qtdInicial, Object noticia, Integer expected)
              throws ReflectiveOperationException {
            Method metodo = Estatistica.class.getDeclaredMethod("receberPublicacao",
                  Noticia.class);

            metodo.invoke(estatistica, noticia);

            ObjectFieldMapping<Estatistica> mapping = new ObjectFieldMapping<>(Estatistica.class);

            assertEquals(expected, mapping.get("qtdPublicacoesRecebidas").get(estatistica),
                  "qtdPublicacoesRecebidas deve ser incrementado em 1");
        }
    }
}
