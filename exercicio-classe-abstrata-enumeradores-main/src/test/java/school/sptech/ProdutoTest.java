package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@DisplayName("Produto")
public class ProdutoTest {

    @Nested
    @DisplayName("Produto 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Produto> clazz = Produto.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("raridade"),
                        "Deve possuir o atributo raridade"),
                  () -> assertEquals(Raridade.class, clazz.getDeclaredField("raridade").getType(),
                        "O atributo raridade deve ser do tipo Raridade")
            );
        }
    }

    @Nested
    @DisplayName("Produto 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertDoesNotThrow(() -> Produto.class.getDeclaredMethod("calcularPreco"),
                  "Deve possuir o método calcularPreco()");
        }

        @Test
        @DisplayName("2. calcularPreco deve ser abstrato e retornar Double")
        void test2() throws ReflectiveOperationException {
            Method metodo = Produto.class.getDeclaredMethod("calcularPreco");

            assertAll(
                  () -> assertTrue(Modifier.isAbstract(metodo.getModifiers()),
                        "O método calcularPreco deve ser abstrato"),
                  () -> assertEquals(Double.class, metodo.getReturnType(),
                        "calcularPreco deve retornar Double")
            );
        }
    }

    @Nested
    @DisplayName("Produto 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Stream<Executable> validacoes = Arrays.stream(Produto.class.getDeclaredFields())
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() throws ReflectiveOperationException {
            Method metodo = Produto.class.getDeclaredMethod("calcularPreco");

            assertTrue(Modifier.isPublic(metodo.getModifiers()), "calcularPreco deve ser público");
        }

        @Test
        @DisplayName("3. Atributos devem possuir getters e setters")
        void test3() {
            Class<Produto> clazz = Produto.class;
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
        @DisplayName("4. Classe deve ser abstrata")
        void test4() {
            assertTrue(Modifier.isAbstract(Produto.class.getModifiers()),
                  "A classe Produto deve ser abstrata");
        }
    }

    @Nested
    @DisplayName("Produto 4. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve possuir o construtor Produto(String nome, Raridade raridade)")
        void test1() {
            Constructor<?> construtor = assertDoesNotThrow(
                  () -> Produto.class.getDeclaredConstructor(String.class, Raridade.class),
                  "Deve possuir o construtor Produto(String, Raridade)");

            assertTrue(Modifier.isPublic(construtor.getModifiers()),
                  "O construtor de Produto deve ser público");
        }
    }
}
