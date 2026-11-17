package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.noticia.NoticiaConstructorProvider;
import school.sptech.util.ExceptionUtil;

@DisplayName("Noticia")
public class NoticiaTest {

    @Nested
    @DisplayName("Noticia 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Noticia> clazz = Noticia.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("titulo"),
                        "Deve possuir o atributo titulo"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("tema"),
                        "Deve possuir o atributo tema"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("autor"),
                        "Deve possuir o atributo autor"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("minutosLeitura"),
                        "Deve possuir o atributo minutosLeitura")
            );
        }
    }

    @Nested
    @DisplayName("Noticia 2. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Noticia> clazz = Noticia.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Atributos devem possuir apenas getters")
        void test2() {
            Class<Noticia> clazz = Noticia.class;
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
    }

    @Nested
    @DisplayName("Noticia 3. Validação - Construtor")
    class ValidacaoTest {

        private static Noticia criar(String titulo, Tema tema, String autor,
              Integer minutosLeitura) throws ReflectiveOperationException {
            Constructor<Noticia> construtor;
            try {
                construtor = Noticia.class.getDeclaredConstructor(String.class, Tema.class,
                      String.class, Integer.class);
            } catch (NoSuchMethodException e) {
                throw new AssertionError(
                      "Deve possuir um construtor com os parâmetros (String, Tema, String, Integer)");
            }
            return construtor.newInstance(titulo, tema, autor, minutosLeitura);
        }

        @ParameterizedTest(name = "{index}. {5}")
        @ArgumentsSource(NoticiaConstructorProvider.class)
        @DisplayName("Deve validar os dados da notícia no construtor")
        void test1(String titulo, Tema tema, String autor, Integer minutosLeitura,
              Class<? extends Exception> excecao, String cenario) {
            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> criar(titulo, tema, autor, minutosLeitura));
                return;
            }

            ExceptionUtil.assertLanca(excecao, () -> criar(titulo, tema, autor, minutosLeitura));
        }
    }
}
