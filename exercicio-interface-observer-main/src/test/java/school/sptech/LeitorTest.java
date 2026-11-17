package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
import school.sptech.provider.leitor.ConstructorProvider;
import school.sptech.provider.leitor.ReceberPublicacaoProvider;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Leitor")
public class LeitorTest {

    @Nested
    @DisplayName("Leitor 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Leitor> clazz = Leitor.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("email"),
                        "Deve possuir o atributo email"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("publicacoesRecebidas"),
                        "Deve possuir o atributo publicacoesRecebidas"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("publicacoesFavoritas"),
                        "Deve possuir o atributo publicacoesFavoritas"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("temaFavorito"),
                        "Deve possuir o atributo temaFavorito")
            );
        }
    }

    @Nested
    @DisplayName("Leitor 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertDoesNotThrow(
                  () -> Leitor.class.getDeclaredMethod("receberPublicacao", Noticia.class),
                  "Deve possuir o método receberPublicacao");
        }
    }

    @Nested
    @DisplayName("Leitor 3. Implementar Observador")
    class ImplementsTest {

        @Test
        @DisplayName("1. Deve implementar a interface Observador")
        void test1() {
            List<Class<?>> interfaces = Arrays.asList(Leitor.class.getInterfaces());

            assertTrue(interfaces.contains(Observador.class),
                  "Leitor deve implementar a interface Observador");
        }
    }

    @Nested
    @DisplayName("Leitor 4. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Leitor> clazz = Leitor.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() throws ReflectiveOperationException {
            Method metodo = Leitor.class.getDeclaredMethod("receberPublicacao", Noticia.class);

            assertTrue(Modifier.isPublic(metodo.getModifiers()),
                  "receberPublicacao deve ser público");
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Leitor> clazz = Leitor.class;
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

        @ParameterizedTest(name = "{index}. Leitor({0}, {1}, {2})")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String nome, String email, String temaFavorito)
              throws ReflectiveOperationException {
            Class<Leitor> clazz = Leitor.class;

            assertDoesNotThrow(() -> clazz.getDeclaredConstructor(String.class, String.class,
                  Tema.class), "Construtor com parâmetros (String nome, String email, Tema temaFavorito) deve existir");

            Tema tema = EnumConstantMapping.getEnumConstant(Tema.class, temaFavorito);
            Object obj = clazz.getDeclaredConstructor(String.class, String.class, Tema.class)
                  .newInstance(nome, email, tema);

            ObjectFieldMapping<Leitor> mapping = new ObjectFieldMapping<>(Leitor.class);

            assertAll(
                  () -> assertEquals(nome, mapping.get("nome").get(obj),
                        "nome deve ser inicializado corretamente"),
                  () -> assertEquals(email, mapping.get("email").get(obj),
                        "email deve ser inicializado corretamente"),
                  () -> assertEquals(tema, mapping.get("temaFavorito").get(obj),
                        "temaFavorito deve ser inicializado corretamente"),
                  () -> assertIterableEquals(List.of(),
                        (List<?>) mapping.get("publicacoesRecebidas").get(obj),
                        "publicacoesRecebidas deve ser inicializada como uma lista vazia"),
                  () -> assertIterableEquals(List.of(),
                        (List<?>) mapping.get("publicacoesFavoritas").get(obj),
                        "publicacoesFavoritas deve ser inicializada como uma lista vazia")
            );
        }
    }

    @Nested
    @DisplayName("Leitor 5. Método - receberPublicacao")
    class MetodoReceberPublicacaoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(ReceberPublicacaoProvider.class)
        @DisplayName("Deve adicionar a notícia às recebidas e, se for do tema favorito, às favoritas")
        void test1(Object leitor, Object noticia, List<?> recebidasEsperadas,
              List<?> favoritasEsperadas, String cenario) throws ReflectiveOperationException {
            Method metodo = Leitor.class.getDeclaredMethod("receberPublicacao", Noticia.class);

            metodo.invoke(leitor, noticia);

            ObjectFieldMapping<Leitor> mapping = new ObjectFieldMapping<>(Leitor.class);

            assertAll(
                  () -> assertIterableEquals(recebidasEsperadas,
                        (List<?>) mapping.get("publicacoesRecebidas").get(leitor),
                        "publicacoesRecebidas deve conter a notícia recebida"),
                  () -> assertIterableEquals(favoritasEsperadas,
                        (List<?>) mapping.get("publicacoesFavoritas").get(leitor),
                        "publicacoesFavoritas deve conter apenas notícias do tema favorito")
            );
        }
    }
}
