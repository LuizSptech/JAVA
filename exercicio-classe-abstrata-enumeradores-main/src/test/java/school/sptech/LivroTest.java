package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static school.sptech.util.ReflectionUtil.getCampo;

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
import school.sptech.factory.LivroFactory;
import school.sptech.provider.livro.CalcularPrecoProvider;

@DisplayName("Livro")
public class LivroTest {

    @Nested
    @DisplayName("Livro 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Livro> clazz = Livro.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("editora"),
                        "Deve possuir o atributo editora"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("autor"),
                        "Deve possuir o atributo autor"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("qtdPaginas"),
                        "Deve possuir o atributo qtdPaginas"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("formato"),
                        "Deve possuir o atributo formato"),
                  () -> assertEquals(Formato.class, clazz.getDeclaredField("formato").getType(),
                        "O atributo formato deve ser do tipo Formato")
            );
        }
    }

    @Nested
    @DisplayName("Livro 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Deve sobrescrever calcularPreco retornando Double")
        void test1() {
            Method metodo = assertDoesNotThrow(() -> Livro.class.getDeclaredMethod("calcularPreco"),
                  "Deve sobrescrever o método calcularPreco()");

            assertEquals(Double.class, metodo.getReturnType(), "calcularPreco deve retornar Double");
        }
    }

    @Nested
    @DisplayName("Livro 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Stream<Executable> validacoes = Arrays.stream(Livro.class.getDeclaredFields())
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() throws ReflectiveOperationException {
            Method metodo = Livro.class.getDeclaredMethod("calcularPreco");

            assertTrue(Modifier.isPublic(metodo.getModifiers()), "calcularPreco deve ser público");
        }

        @Test
        @DisplayName("3. Atributos devem possuir getters e setters")
        void test3() {
            Class<Livro> clazz = Livro.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()),
                                String.format("Getter %s deve ser público", getName));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method setter = clazz.getDeclaredMethod(setName, campo.getType());
                          assertTrue(Modifier.isPublic(setter.getModifiers()),
                                String.format("Setter %s deve ser público", setName));
                      }, String.format("Deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @Test
        @DisplayName("4. Deve ser uma subclasse de Produto")
        void test4() {
            assertEquals(Produto.class, Livro.class.getSuperclass(),
                  "Livro deve ser uma subclasse de Produto");
        }
    }

    @Nested
    @DisplayName("Livro 4. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve iniciar todos os atributos, inclusive os herdados de Produto")
        void test1() throws ReflectiveOperationException {
            Constructor<Livro> construtor = assertDoesNotThrow(
                  () -> Livro.class.getDeclaredConstructor(String.class, Raridade.class,
                        String.class, String.class, Integer.class, Formato.class),
                  "Deve possuir o construtor Livro(String, Raridade, String, String, Integer, Formato)");

            Livro livro = construtor.newInstance("O Hobbit", Raridade.valueOf("RARO"),
                  "HarperCollins", "J. R. R. Tolkien", 300, Formato.valueOf("CAPA_DURA"));

            assertAll(
                  () -> assertEquals("O Hobbit", getCampo(livro, "nome")),
                  () -> assertEquals(Raridade.valueOf("RARO"), getCampo(livro, "raridade")),
                  () -> assertEquals("HarperCollins", getCampo(livro, "editora")),
                  () -> assertEquals("J. R. R. Tolkien", getCampo(livro, "autor")),
                  () -> assertEquals(300, getCampo(livro, "qtdPaginas")),
                  () -> assertEquals(Formato.valueOf("CAPA_DURA"), getCampo(livro, "formato"))
            );
        }
    }

    @Nested
    @DisplayName("Livro 5. Método - calcularPreco")
    class MetodoCalcularPrecoTest {

        @ParameterizedTest(name = "{index}. {0}: {4} páginas, {5}, raridade {1} = {6}")
        @ArgumentsSource(CalcularPrecoProvider.class)
        @DisplayName("Deve calcular o preço pelo formato e aplicar o adicional da raridade")
        void test1(String nome, String raridade, String editora, String autor, Integer qtdPaginas,
              String formato, Double expected) throws ReflectiveOperationException {
            Method metodo = Livro.class.getDeclaredMethod("calcularPreco");

            Object obj = LivroFactory.build(nome, raridade, editora, autor, qtdPaginas, formato);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularPreco deve calcular corretamente o preço do livro");
        }
    }
}
