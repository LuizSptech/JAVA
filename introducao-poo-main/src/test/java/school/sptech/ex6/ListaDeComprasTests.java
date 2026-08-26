package school.sptech.ex6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex6.provider.AdicionarItemProvider;
import school.sptech.ex6.provider.CalcularVagasRestantesProvider;
import school.sptech.ex6.provider.ObterItemProvider;
import school.sptech.ex6.provider.RemoverItemNaPosicaoProvider;
import school.sptech.ex6.provider.RemoverItemProvider;
import school.sptech.ex6.provider.RemoverItensDuplicadosProvider;
import school.sptech.ex6.provider.SubstituirItemProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 6 - Lista de Compras")
class ListaDeComprasTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<ListaDeCompras> clazz = ListaDeCompras.class;

            assertField(clazz, "nomeLista", String.class);
            assertField(clazz, "capacidadeMaxima", Integer.class);
            assertField(clazz, "itens", List.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<ListaDeCompras> clazz = ListaDeCompras.class;

            assertMethod(clazz, "adicionarItem", String.class);
            assertMethod(clazz, "removerItem", String.class);
            assertMethod(clazz, "obterItem", Integer.class);
            assertMethod(clazz, "substituirItem", Integer.class, String.class);
            assertMethod(clazz, "calcularVagasRestantes");
            assertMethod(clazz, "removerItemNaPosicao", Integer.class);
            assertMethod(clazz, "removerItensDuplicados");
        }
    }

    @Nested
    @DisplayName("adicionarItem()")
    class AdicionarItemTest {

        @ParameterizedTest
        @DisplayName("Deve adicionar o item respeitando a capacidade máxima e evitando duplicados")
        @ArgumentsSource(AdicionarItemProvider.class)
        void testAdicionarItem(Integer capacidadeMaxima, List<String> itensIniciais, String item,
              List<String> itensEsperados) throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("adicionarItem", String.class);

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("capacidadeMaxima", capacidadeMaxima)
                  .with("itens", new ArrayList<>(itensIniciais))
                  .build();

            method.invoke(obj, item);

            assertEquals(itensEsperados, getField(obj, "itens"),
                  "adicionarItem(" + item + ") não atualizou a lista itens corretamente");
        }
    }

    @Nested
    @DisplayName("removerItem()")
    class RemoverItemTest {

        @ParameterizedTest
        @DisplayName("Deve remover o item da lista e retornar o resultado corretamente")
        @ArgumentsSource(RemoverItemProvider.class)
        void testRemoverItem(List<String> itensIniciais, String item, Boolean sucessoEsperado,
              List<String> itensEsperados) throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("removerItem", String.class);

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("itens", new ArrayList<>(itensIniciais))
                  .build();

            Boolean sucesso = (Boolean) method.invoke(obj, item);

            assertEquals(sucessoEsperado, sucesso,
                  "removerItem(" + item + ") deveria retornar " + sucessoEsperado + ", mas retornou " + sucesso);
            assertEquals(itensEsperados, getField(obj, "itens"),
                  "removerItem(" + item + ") não atualizou a lista itens corretamente");
        }
    }

    @Nested
    @DisplayName("obterItem()")
    class ObterItemTest {

        @ParameterizedTest
        @DisplayName("Deve retornar o item na posição informada, ou null se a posição for inválida")
        @ArgumentsSource(ObterItemProvider.class)
        void testObterItem(List<String> itens, Integer posicao, String valorEsperado)
              throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("obterItem", Integer.class);

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("itens", new ArrayList<>(itens))
                  .build();

            String item = (String) method.invoke(obj, posicao);

            assertEquals(valorEsperado, item,
                  "obterItem(" + posicao + ") deveria retornar \"" + valorEsperado + "\", mas retornou \""
                        + item + "\"");
        }
    }

    @Nested
    @DisplayName("substituirItem()")
    class SubstituirItemTest {

        @ParameterizedTest
        @DisplayName("Deve substituir o item na posição informada, validando posição e duplicados")
        @ArgumentsSource(SubstituirItemProvider.class)
        void testSubstituirItem(List<String> itensIniciais, Integer posicao, String novoItem,
              Boolean sucessoEsperado, List<String> itensEsperados) throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("substituirItem", Integer.class, String.class);

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("itens", new ArrayList<>(itensIniciais))
                  .build();

            Boolean sucesso = (Boolean) method.invoke(obj, posicao, novoItem);

            assertEquals(sucessoEsperado, sucesso,
                  "substituirItem(" + posicao + ", " + novoItem + ") deveria retornar " + sucessoEsperado
                        + ", mas retornou " + sucesso);
            assertEquals(itensEsperados, getField(obj, "itens"),
                  "substituirItem(" + posicao + ", " + novoItem + ") não atualizou a lista itens corretamente");
        }
    }

    @Nested
    @DisplayName("calcularVagasRestantes()")
    class CalcularVagasRestantesTest {

        @ParameterizedTest
        @DisplayName("Deve calcular a quantidade de vagas restantes corretamente")
        @ArgumentsSource(CalcularVagasRestantesProvider.class)
        void testCalcularVagasRestantes(Integer capacidadeMaxima, List<String> itens, Integer valorEsperado)
              throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("calcularVagasRestantes");

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("capacidadeMaxima", capacidadeMaxima)
                  .with("itens", new ArrayList<>(itens))
                  .build();

            Integer vagas = (Integer) method.invoke(obj);

            assertEquals(valorEsperado, vagas,
                  "calcularVagasRestantes() deveria retornar " + valorEsperado + ", mas retornou " + vagas);
        }
    }

    @Nested
    @DisplayName("removerItemNaPosicao()")
    class RemoverItemNaPosicaoTest {

        @ParameterizedTest
        @DisplayName("Deve remover e retornar o item da posição informada, ou null se a posição for inválida")
        @ArgumentsSource(RemoverItemNaPosicaoProvider.class)
        void testRemoverItemNaPosicao(List<String> itensIniciais, Integer posicao, String itemRemovidoEsperado,
              List<String> itensEsperados) throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("removerItemNaPosicao", Integer.class);

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("itens", new ArrayList<>(itensIniciais))
                  .build();

            String itemRemovido = (String) method.invoke(obj, posicao);

            assertEquals(itemRemovidoEsperado, itemRemovido,
                  "removerItemNaPosicao(" + posicao + ") deveria retornar \"" + itemRemovidoEsperado
                        + "\", mas retornou \"" + itemRemovido + "\"");
            assertEquals(itensEsperados, getField(obj, "itens"),
                  "removerItemNaPosicao(" + posicao + ") não atualizou a lista itens corretamente");
        }
    }

    @Nested
    @DisplayName("removerItensDuplicados()")
    class RemoverItensDuplicadosTest {

        @ParameterizedTest
        @DisplayName("Deve remover as ocorrências duplicadas, mantendo a primeira de cada item")
        @ArgumentsSource(RemoverItensDuplicadosProvider.class)
        void testRemoverItensDuplicados(List<String> itensIniciais, Integer quantidadeRemovidaEsperada,
              List<String> itensEsperados) throws ReflectiveOperationException {

            Class<ListaDeCompras> clazz = ListaDeCompras.class;
            Method method = clazz.getDeclaredMethod("removerItensDuplicados");

            method.setAccessible(true);

            ListaDeCompras obj = new ObjectFieldBuilder<>(ListaDeCompras.class)
                  .with("itens", new ArrayList<>(itensIniciais))
                  .build();

            Integer quantidadeRemovida = (Integer) method.invoke(obj);

            assertEquals(quantidadeRemovidaEsperada, quantidadeRemovida,
                  "removerItensDuplicados() deveria retornar " + quantidadeRemovidaEsperada + ", mas retornou "
                        + quantidadeRemovida);
            assertEquals(itensEsperados, getField(obj, "itens"),
                  "removerItensDuplicados() não atualizou a lista itens corretamente");
        }
    }
}
