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
import school.sptech.factory.JogoTabuleiroFactory;
import school.sptech.provider.jogotabuleiro.CalcularPrecoProvider;

@DisplayName("JogoTabuleiro")
public class JogoTabuleiroTest {

    @Nested
    @DisplayName("JogoTabuleiro 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<JogoTabuleiro> clazz = JogoTabuleiro.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("qtdPecas"),
                        "Deve possuir o atributo qtdPecas"),
                  () -> assertEquals(Integer.class, clazz.getDeclaredField("qtdPecas").getType(),
                        "O atributo qtdPecas deve ser do tipo Integer")
            );
        }
    }

    @Nested
    @DisplayName("JogoTabuleiro 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Deve sobrescrever calcularPreco retornando Double")
        void test1() {
            Method metodo = assertDoesNotThrow(() -> JogoTabuleiro.class.getDeclaredMethod("calcularPreco"),
                  "Deve sobrescrever o método calcularPreco()");

            assertEquals(Double.class, metodo.getReturnType(), "calcularPreco deve retornar Double");
        }
    }

    @Nested
    @DisplayName("JogoTabuleiro 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Stream<Executable> validacoes = Arrays.stream(JogoTabuleiro.class.getDeclaredFields())
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() throws ReflectiveOperationException {
            Method metodo = JogoTabuleiro.class.getDeclaredMethod("calcularPreco");

            assertTrue(Modifier.isPublic(metodo.getModifiers()), "calcularPreco deve ser público");
        }

        @Test
        @DisplayName("3. Atributos devem possuir getters e setters")
        void test3() {
            Class<JogoTabuleiro> clazz = JogoTabuleiro.class;
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
            assertEquals(Produto.class, JogoTabuleiro.class.getSuperclass(),
                  "JogoTabuleiro deve ser uma subclasse de Produto");
        }
    }

    @Nested
    @DisplayName("JogoTabuleiro 4. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve iniciar todos os atributos, inclusive os herdados de Produto")
        void test1() throws ReflectiveOperationException {
            Constructor<JogoTabuleiro> construtor = assertDoesNotThrow(
                  () -> JogoTabuleiro.class.getDeclaredConstructor(String.class, Raridade.class,
                        Integer.class),
                  "Deve possuir o construtor JogoTabuleiro(String, Raridade, Integer)");

            JogoTabuleiro jogo = construtor.newInstance("War", Raridade.valueOf("MUITO_RARO"), 100);

            assertAll(
                  () -> assertEquals("War", getCampo(jogo, "nome")),
                  () -> assertEquals(Raridade.valueOf("MUITO_RARO"), getCampo(jogo, "raridade")),
                  () -> assertEquals(100, getCampo(jogo, "qtdPecas"))
            );
        }
    }

    @Nested
    @DisplayName("JogoTabuleiro 5. Método - calcularPreco")
    class MetodoCalcularPrecoTest {

        @ParameterizedTest(name = "{index}. {0}: {2} peças, raridade {1} = {3}")
        @ArgumentsSource(CalcularPrecoProvider.class)
        @DisplayName("Deve calcular o preço pelas peças e aplicar o adicional da raridade")
        void test1(String nome, String raridade, Integer qtdPecas, Double expected)
              throws ReflectiveOperationException {
            Method metodo = JogoTabuleiro.class.getDeclaredMethod("calcularPreco");

            Object obj = JogoTabuleiroFactory.build(nome, raridade, qtdPecas);
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "calcularPreco deve calcular corretamente o preço do jogo de tabuleiro");
        }
    }
}
