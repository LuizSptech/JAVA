package school.sptech.ex2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex2.provider.AplicarCupomDeDescontoProvider;
import school.sptech.ex2.provider.CalcularFreteProvider;
import school.sptech.ex2.provider.ValorTotalDaEncomendaProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 2 - Encomenda")
class EncomendaTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Encomenda> clazz = Encomenda.class;

            assertField(clazz, "tamanho", String.class);
            assertField(clazz, "enderecoRemetente", String.class);
            assertField(clazz, "enderecoDestinatario", String.class);
            assertField(clazz, "distancia", Double.class);
            assertField(clazz, "valorProduto", Double.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<Encomenda> clazz = Encomenda.class;

            assertMethod(clazz, "calcularFrete");
            assertMethod(clazz, "aplicarCupomDeDesconto", Integer.class);
            assertMethod(clazz, "valorTotalDaEncomenda");
        }
    }

    @Nested
    @DisplayName("calcularFrete()")
    class CalcularFreteTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o frete corretamente, respeitando as tabelas de tamanho e distância")
        @ArgumentsSource(CalcularFreteProvider.class)
        void testCalcularFrete(String tamanho, Double distancia, Double valorProduto, Double valorEsperado)
              throws ReflectiveOperationException {

            Class<Encomenda> clazz = Encomenda.class;
            Method method = clazz.getDeclaredMethod("calcularFrete");

            method.setAccessible(true);

            Encomenda obj = new ObjectFieldBuilder<>(Encomenda.class)
                  .with("tamanho", tamanho)
                  .with("distancia", distancia)
                  .with("valorProduto", valorProduto)
                  .build();

            Double frete = (Double) method.invoke(obj);

            assertEquals(valorEsperado, frete, 0.01,
                  "calcularFrete() com tamanho=" + tamanho + " e distancia=" + distancia
                        + " deveria retornar " + valorEsperado + ", mas retornou " + frete);
        }
    }

    @Nested
    @DisplayName("aplicarCupomDeDesconto()")
    class AplicarCupomDeDescontoTest {

        @ParameterizedTest
        @DisplayName("Deve aplicar o desconto e atualizar valorProduto corretamente")
        @ArgumentsSource(AplicarCupomDeDescontoProvider.class)
        void testAplicarCupomDeDesconto(Double valorProduto, Integer percentual, Double valorProdutoEsperado)
              throws ReflectiveOperationException {

            Class<Encomenda> clazz = Encomenda.class;
            Method method = clazz.getDeclaredMethod("aplicarCupomDeDesconto", Integer.class);

            method.setAccessible(true);

            Encomenda obj = new ObjectFieldBuilder<>(Encomenda.class)
                  .with("valorProduto", valorProduto)
                  .build();

            method.invoke(obj, percentual);

            assertEquals(valorProdutoEsperado, (Double) getField(obj, "valorProduto"), 0.01,
                  "aplicarCupomDeDesconto(" + percentual + ") não atualizou valorProduto corretamente");
        }
    }

    @Nested
    @DisplayName("valorTotalDaEncomenda()")
    class ValorTotalDaEncomendaTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o valor total da encomenda corretamente")
        @ArgumentsSource(ValorTotalDaEncomendaProvider.class)
        void testValorTotalDaEncomenda(String tamanho, Double distancia, Double valorProduto, Double valorEsperado)
              throws ReflectiveOperationException {

            Class<Encomenda> clazz = Encomenda.class;
            Method method = clazz.getDeclaredMethod("valorTotalDaEncomenda");

            method.setAccessible(true);

            Encomenda obj = new ObjectFieldBuilder<>(Encomenda.class)
                  .with("tamanho", tamanho)
                  .with("distancia", distancia)
                  .with("valorProduto", valorProduto)
                  .build();

            Double total = (Double) method.invoke(obj);

            assertEquals(valorEsperado, total, 0.01,
                  "valorTotalDaEncomenda() deveria retornar " + valorEsperado + ", mas retornou " + total);
        }
    }
}
