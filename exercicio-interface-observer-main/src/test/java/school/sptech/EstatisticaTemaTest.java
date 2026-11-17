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
import school.sptech.provider.estatisticatema.ConstructorProvider;
import school.sptech.provider.estatisticatema.GetPercentualProvider;
import school.sptech.provider.estatisticatema.ReceberPublicacaoProvider;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("EstatisticaTema")
public class EstatisticaTemaTest {

    @Nested
    @DisplayName("EstatisticaTema 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<EstatisticaTema> clazz = EstatisticaTema.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("tema"),
                        "Deve possuir o atributo tema"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("qtdPublicacoesTema"),
                        "Deve possuir o atributo qtdPublicacoesTema"),
                  () -> assertThrows(NoSuchFieldException.class,
                        () -> clazz.getDeclaredField("qtdPublicacoesRecebidas"),
                        "qtdPublicacoesRecebidas deve ser herdado de Estatistica, e não redeclarado")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() throws ReflectiveOperationException {
            Field campo = EstatisticaTema.class.getDeclaredField("tema");

            assertTrue(Modifier.isFinal(campo.getModifiers()), "tema deve ser final");
        }
    }

    @Nested
    @DisplayName("EstatisticaTema 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<EstatisticaTema> clazz = EstatisticaTema.class;

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
    @DisplayName("EstatisticaTema 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<EstatisticaTema> clazz = EstatisticaTema.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<EstatisticaTema> clazz = EstatisticaTema.class;
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
            Class<EstatisticaTema> clazz = EstatisticaTema.class;
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

        @ParameterizedTest(name = "{index}. EstatisticaTema({0})")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String tema) throws ReflectiveOperationException {
            Class<EstatisticaTema> clazz = EstatisticaTema.class;

            assertDoesNotThrow(() -> clazz.getDeclaredConstructor(Tema.class),
                  "Construtor com parâmetros (Tema tema) deve existir");

            Tema temaEnum = EnumConstantMapping.getEnumConstant(Tema.class, tema);
            Object obj = clazz.getDeclaredConstructor(Tema.class).newInstance(temaEnum);

            ObjectFieldMapping<EstatisticaTema> mapping = new ObjectFieldMapping<>(
                  EstatisticaTema.class);

            assertAll(
                  () -> assertEquals(temaEnum, mapping.get("tema").get(obj),
                        "tema deve ser inicializado corretamente"),
                  () -> assertEquals(0, mapping.get("qtdPublicacoesTema").get(obj),
                        "qtdPublicacoesTema deve ser inicializado com zero"),
                  () -> assertEquals(0, mapping.get("qtdPublicacoesRecebidas").get(obj),
                        "qtdPublicacoesRecebidas deve ser inicializado com zero")
            );
        }

        @Test
        @DisplayName("5. Deve ser uma subclasse de Estatistica")
        void test5() {
            assertEquals(Estatistica.class, EstatisticaTema.class.getSuperclass(),
                  "EstatisticaTema deve ser uma subclasse de Estatistica");
        }
    }

    @Nested
    @DisplayName("EstatisticaTema 4. Método - receberPublicacao")
    class ReceberPublicacaoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(ReceberPublicacaoProvider.class)
        @DisplayName("Deve incrementar as contagens corretamente ao receber uma publicação")
        void test1(Object estatisticaTema, Object noticia, Integer recebidasEsperadas,
              Integer temaEsperadas, String cenario) throws ReflectiveOperationException {
            Method metodo = EstatisticaTema.class.getDeclaredMethod("receberPublicacao",
                  Noticia.class);

            metodo.invoke(estatisticaTema, noticia);

            ObjectFieldMapping<EstatisticaTema> mapping = new ObjectFieldMapping<>(
                  EstatisticaTema.class);

            assertAll(
                  () -> assertEquals(recebidasEsperadas,
                        mapping.get("qtdPublicacoesRecebidas").get(estatisticaTema),
                        "qtdPublicacoesRecebidas deve ser incrementado em 1"),
                  () -> assertEquals(temaEsperadas,
                        mapping.get("qtdPublicacoesTema").get(estatisticaTema),
                        "qtdPublicacoesTema deve ser incrementado apenas se a notícia for do tema")
            );
        }
    }

    @Nested
    @DisplayName("EstatisticaTema 5. Método - getPercentual")
    class GetPercentualTest {

        @ParameterizedTest(name = "{index}. {2} -> {1}%")
        @ArgumentsSource(GetPercentualProvider.class)
        @DisplayName("Deve calcular o percentual de notícias do tema")
        void test1(Object estatisticaTema, Double expected, String cenario) throws ReflectiveOperationException {
            Method metodo = EstatisticaTema.class.getDeclaredMethod("getPercentual");

            Double percentual = (Double) metodo.invoke(estatisticaTema);

            assertEquals(expected, percentual, 0.01,
                  "getPercentual deve calcular corretamente o percentual");
        }
    }
}
