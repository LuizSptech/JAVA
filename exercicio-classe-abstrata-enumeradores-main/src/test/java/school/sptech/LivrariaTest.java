package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static school.sptech.util.ReflectionUtil.getCampo;

import java.lang.reflect.Constructor;
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
import school.sptech.factory.LivrariaFactory;
import school.sptech.provider.livraria.AdicionarProvider;
import school.sptech.provider.livraria.BuscarLivrosPorFormatoProvider;
import school.sptech.provider.livraria.BuscarPorQtdPecasMaiorProvider;
import school.sptech.provider.livraria.BuscarPorRaridadeProvider;
import school.sptech.provider.livraria.CalcularTotalLivrosProvider;
import school.sptech.provider.livraria.ExistePorNomeProvider;
import school.sptech.provider.livraria.RemoverProvider;

@DisplayName("Livraria")
public class LivrariaTest {

    static Method metodo(String nome, Class<?>... parametros) throws NoSuchMethodException {
        return Livraria.class.getDeclaredMethod(nome, parametros);
    }

    static List<?> getProdutos(Object livraria) throws ReflectiveOperationException {
        return (List<?>) getCampo(livraria, "produtos");
    }

    static List<Method> metodosEsperados() {
        List<Method> metodos = new ArrayList<>();
        Object[][] assinaturas = {
              {"adicionar", new Class<?>[]{Produto.class}},
              {"remover", new Class<?>[]{String.class}},
              {"calcularTotalLivros", new Class<?>[]{}},
              {"buscarLivrosPorFormato", new Class<?>[]{Formato.class}},
              {"buscarPorRaridade", new Class<?>[]{Raridade.class}},
              {"existePorNome", new Class<?>[]{String.class}},
              {"buscarPorQtdPecasMaior", new Class<?>[]{Integer.class}},
              {"getNome", new Class<?>[]{}},
              {"getProdutos", new Class<?>[]{}}
        };

        for (Object[] assinatura : assinaturas) {
            try {
                metodos.add(metodo((String) assinatura[0], (Class<?>[]) assinatura[1]));
            } catch (NoSuchMethodException ignored) {
            }
        }
        return metodos;
    }

    @Nested
    @DisplayName("Livraria 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Livraria> clazz = Livraria.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("produtos"),
                        "Deve possuir o atributo produtos")
            );
        }
    }

    @Nested
    @DisplayName("Livraria 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertAll(
                  () -> assertDoesNotThrow(() -> metodo("adicionar", Produto.class),
                        "Deve possuir o método adicionar(Produto)"),
                  () -> assertDoesNotThrow(() -> metodo("remover", String.class),
                        "Deve possuir o método remover(String)"),
                  () -> assertDoesNotThrow(() -> metodo("calcularTotalLivros"),
                        "Deve possuir o método calcularTotalLivros()"),
                  () -> assertDoesNotThrow(() -> metodo("buscarLivrosPorFormato", Formato.class),
                        "Deve possuir o método buscarLivrosPorFormato(Formato)"),
                  () -> assertDoesNotThrow(() -> metodo("buscarPorRaridade", Raridade.class),
                        "Deve possuir o método buscarPorRaridade(Raridade)"),
                  () -> assertDoesNotThrow(() -> metodo("existePorNome", String.class),
                        "Deve possuir o método existePorNome(String)"),
                  () -> assertDoesNotThrow(() -> metodo("buscarPorQtdPecasMaior", Integer.class),
                        "Deve possuir o método buscarPorQtdPecasMaior(Integer)")
            );
        }

        @Test
        @DisplayName("2. Validar tipos de retorno")
        void test2() {
            assertAll(
                  () -> assertEquals(void.class, metodo("adicionar", Produto.class).getReturnType(),
                        "adicionar deve ser void"),
                  () -> assertEquals(void.class, metodo("remover", String.class).getReturnType(),
                        "remover deve ser void"),
                  () -> assertEquals(Double.class, metodo("calcularTotalLivros").getReturnType(),
                        "calcularTotalLivros deve retornar Double"),
                  () -> assertEquals(List.class,
                        metodo("buscarLivrosPorFormato", Formato.class).getReturnType(),
                        "buscarLivrosPorFormato deve retornar List"),
                  () -> assertEquals(List.class,
                        metodo("buscarPorRaridade", Raridade.class).getReturnType(),
                        "buscarPorRaridade deve retornar List"),
                  () -> assertEquals(Boolean.class,
                        metodo("existePorNome", String.class).getReturnType(),
                        "existePorNome deve retornar Boolean"),
                  () -> assertEquals(List.class,
                        metodo("buscarPorQtdPecasMaior", Integer.class).getReturnType(),
                        "buscarPorQtdPecasMaior deve retornar List")
            );
        }
    }

    @Nested
    @DisplayName("Livraria 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Stream<Executable> validacoes = Arrays.stream(Livraria.class.getDeclaredFields())
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Stream<Executable> validacoes = metodosEsperados().stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Livraria> clazz = Livraria.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
                      assertThrows(NoSuchMethodException.class,
                            () -> clazz.getDeclaredMethod(setName, campo.getType()),
                            String.format("Não deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }
    }

    @Nested
    @DisplayName("Livraria 4. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve receber apenas o nome e iniciar a lista de produtos vazia")
        void test1() throws ReflectiveOperationException {
            Constructor<Livraria> construtor = assertDoesNotThrow(
                  () -> Livraria.class.getDeclaredConstructor(String.class),
                  "Deve possuir o construtor Livraria(String)");

            Livraria livraria = construtor.newInstance("Livraria SPTech");

            assertAll(
                  () -> assertEquals("Livraria SPTech", getCampo(livraria, "nome")),
                  () -> assertNotNull(getProdutos(livraria), "A lista de produtos deve ser iniciada"),
                  () -> assertTrue(getProdutos(livraria).isEmpty(),
                        "A lista de produtos deve começar vazia")
            );
        }
    }

    @Nested
    @DisplayName("Livraria 5. Método - adicionar")
    class MetodoAdicionarTest {

        @ParameterizedTest
        @ArgumentsSource(AdicionarProvider.class)
        @DisplayName("Deve adicionar o produto no fim da lista")
        void test1(String nome, List<?> produtos, Object produto, List<?> expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            metodo("adicionar", Produto.class).invoke(obj, produto);

            assertIterableEquals(expected, getProdutos(obj));
        }
    }

    @Nested
    @DisplayName("Livraria 6. Método - remover")
    class MetodoRemoverTest {

        @ParameterizedTest
        @ArgumentsSource(RemoverProvider.class)
        @DisplayName("Deve remover o produto com o nome informado")
        void test1(String nome, List<?> produtos, String nomeProduto, List<?> expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            metodo("remover", String.class).invoke(obj, nomeProduto);

            assertIterableEquals(expected, getProdutos(obj));
        }
    }

    @Nested
    @DisplayName("Livraria 7. Método - calcularTotalLivros")
    class MetodoCalcularTotalLivrosTest {

        @ParameterizedTest
        @ArgumentsSource(CalcularTotalLivrosProvider.class)
        @DisplayName("Deve somar o preço apenas dos livros")
        void test1(String nome, List<?> produtos, Double expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            Object resposta = metodo("calcularTotalLivros").invoke(obj);

            assertEquals(expected, (Double) resposta, 0.1);
        }
    }

    @Nested
    @DisplayName("Livraria 8. Método - buscarLivrosPorFormato")
    class MetodoBuscarLivrosPorFormatoTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarLivrosPorFormatoProvider.class)
        @DisplayName("Deve retornar os livros do formato informado")
        void test1(String nome, List<?> produtos, String formato, List<?> expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            Object resposta = metodo("buscarLivrosPorFormato", Formato.class)
                  .invoke(obj, Formato.valueOf(formato));

            assertIterableEquals(expected, (List<?>) resposta);
        }
    }

    @Nested
    @DisplayName("Livraria 9. Método - buscarPorRaridade")
    class MetodoBuscarPorRaridadeTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarPorRaridadeProvider.class)
        @DisplayName("Deve retornar os produtos da raridade informada")
        void test1(String nome, List<?> produtos, String raridade, List<?> expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            Object resposta = metodo("buscarPorRaridade", Raridade.class)
                  .invoke(obj, Raridade.valueOf(raridade));

            assertIterableEquals(expected, (List<?>) resposta);
        }
    }

    @Nested
    @DisplayName("Livraria 10. Método - existePorNome")
    class MetodoExistePorNomeTest {

        @ParameterizedTest
        @ArgumentsSource(ExistePorNomeProvider.class)
        @DisplayName("Deve informar se existe produto com o nome informado")
        void test1(String nome, List<?> produtos, String nomeProduto, Boolean expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            Object resposta = metodo("existePorNome", String.class).invoke(obj, nomeProduto);

            assertEquals(expected, resposta);
        }
    }

    @Nested
    @DisplayName("Livraria 11. Método - buscarPorQtdPecasMaior")
    class MetodoBuscarPorQtdPecasMaiorTest {

        @ParameterizedTest
        @ArgumentsSource(BuscarPorQtdPecasMaiorProvider.class)
        @DisplayName("Deve retornar os jogos com quantidade de peças maior que a informada")
        void test1(String nome, List<?> produtos, Integer qtdPecas, List<?> expected)
              throws ReflectiveOperationException {
            Object obj = LivrariaFactory.build(nome, produtos);

            Object resposta = metodo("buscarPorQtdPecasMaior", Integer.class).invoke(obj, qtdPecas);

            assertIterableEquals(expected, (List<?>) resposta);
        }
    }
}
