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
import school.sptech.provider.estatisticajornalista.ConstructorProvider;
import school.sptech.provider.estatisticajornalista.GetPercentualProvider;
import school.sptech.provider.estatisticajornalista.ReceberPublicacaoProvider;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("EstatisticaJornalista")
public class EstatisticaJornalistaTest {

    @Nested
    @DisplayName("EstatisticaJornalista 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("jornalista"),
                        "Deve possuir o atributo jornalista"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredField("qtdPublicacoesJornalista"),
                        "Deve possuir o atributo qtdPublicacoesJornalista"),
                  () -> assertThrows(NoSuchFieldException.class,
                        () -> clazz.getDeclaredField("qtdPublicacoesRecebidas"),
                        "qtdPublicacoesRecebidas deve ser herdado de Estatistica, e não redeclarado")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() throws ReflectiveOperationException {
            Field campo = EstatisticaJornalista.class.getDeclaredField("jornalista");

            assertTrue(Modifier.isFinal(campo.getModifiers()), "jornalista deve ser final");
        }
    }

    @Nested
    @DisplayName("EstatisticaJornalista 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;

            assertAll(
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("receberPublicacao", Noticia.class),
                        "Deve sobrescrever o método receberPublicacao"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getPercentual"),
                        "Deve sobrescrever o método getPercentual")
            );
        }
    }

    @Nested
    @DisplayName("EstatisticaJornalista 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;
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
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;
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

        @ParameterizedTest(name = "{index}. EstatisticaJornalista(\"{0}\")")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String jornalista) throws ReflectiveOperationException {
            Class<EstatisticaJornalista> clazz = EstatisticaJornalista.class;

            assertDoesNotThrow(() -> clazz.getDeclaredConstructor(String.class),
                  "Construtor com parâmetros (String jornalista) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(jornalista);

            ObjectFieldMapping<EstatisticaJornalista> mapping = new ObjectFieldMapping<>(
                  EstatisticaJornalista.class);

            assertAll(
                  () -> assertEquals(jornalista, mapping.get("jornalista").get(obj),
                        "jornalista deve ser inicializado corretamente"),
                  () -> assertEquals(0, mapping.get("qtdPublicacoesJornalista").get(obj),
                        "qtdPublicacoesJornalista deve ser inicializado com zero"),
                  () -> assertEquals(0, mapping.get("qtdPublicacoesRecebidas").get(obj),
                        "qtdPublicacoesRecebidas deve ser inicializado com zero")
            );
        }

        @Test
        @DisplayName("5. Deve ser uma subclasse de Estatistica")
        void test5() {
            assertEquals(Estatistica.class, EstatisticaJornalista.class.getSuperclass(),
                  "EstatisticaJornalista deve ser uma subclasse de Estatistica");
        }
    }

    @Nested
    @DisplayName("EstatisticaJornalista 4. Método - receberPublicacao")
    class ReceberPublicacaoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(ReceberPublicacaoProvider.class)
        @DisplayName("Deve incrementar as contagens corretamente ao receber uma publicação")
        void test1(Object estatisticaJornalista, Object noticia, Integer recebidasEsperadas,
              Integer jornalistaEsperadas, String cenario) throws ReflectiveOperationException {
            Method metodo = EstatisticaJornalista.class.getDeclaredMethod("receberPublicacao",
                  Noticia.class);

            metodo.invoke(estatisticaJornalista, noticia);

            ObjectFieldMapping<EstatisticaJornalista> mapping = new ObjectFieldMapping<>(
                  EstatisticaJornalista.class);

            assertAll(
                  () -> assertEquals(recebidasEsperadas,
                        mapping.get("qtdPublicacoesRecebidas").get(estatisticaJornalista),
                        "qtdPublicacoesRecebidas deve ser incrementado em 1"),
                  () -> assertEquals(jornalistaEsperadas,
                        mapping.get("qtdPublicacoesJornalista").get(estatisticaJornalista),
                        "qtdPublicacoesJornalista deve ser incrementado apenas se o autor for o jornalista")
            );
        }
    }

    @Nested
    @DisplayName("EstatisticaJornalista 5. Método - getPercentual")
    class GetPercentualTest {

        @ParameterizedTest(name = "{index}. {2} -> {1}%")
        @ArgumentsSource(GetPercentualProvider.class)
        @DisplayName("Deve calcular o percentual de notícias do jornalista")
        void test1(Object estatisticaJornalista, Double expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = EstatisticaJornalista.class.getDeclaredMethod("getPercentual");

            Double percentual = (Double) metodo.invoke(estatisticaJornalista);

            assertEquals(expected, percentual, 0.01,
                  "getPercentual deve calcular corretamente o percentual");
        }
    }
}
