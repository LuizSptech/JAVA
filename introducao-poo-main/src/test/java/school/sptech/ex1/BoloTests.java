package school.sptech.ex1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex1.provider.AumentarEstoqueProvider;
import school.sptech.ex1.provider.QuantidadeDisponivelProvider;
import school.sptech.ex1.provider.TotalVendidoProvider;
import school.sptech.ex1.provider.VenderBoloProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 1 - Confeitaria")
class BoloTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Bolo> clazz = Bolo.class;

            assertField(clazz, "sabor", String.class);
            assertField(clazz, "valor", Double.class);
            assertField(clazz, "quantidadeVendida", Integer.class);
            assertField(clazz, "quantidadeEmEstoque", Integer.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<Bolo> clazz = Bolo.class;

            assertMethod(clazz, "venderBolo", Integer.class);
            assertMethod(clazz, "aumentarEstoque", Integer.class);
            assertMethod(clazz, "quantidadeDisponivel");
            assertMethod(clazz, "totalVendido");
        }
    }

    @Nested
    @DisplayName("venderBolo()")
    class VenderBoloTest {

        @ParameterizedTest
        @DisplayName("Deve vender o bolo e atualizar quantidadeVendida e quantidadeEmEstoque corretamente")
        @ArgumentsSource(VenderBoloProvider.class)
        void testVenderBolo(Integer quantidadeVendida, Integer quantidadeEmEstoque, Integer qtd,
              Integer quantidadeVendidaEsperada, Integer quantidadeEmEstoqueEsperada)
              throws ReflectiveOperationException {

            Class<Bolo> clazz = Bolo.class;
            Method method = clazz.getDeclaredMethod("venderBolo", Integer.class);

            method.setAccessible(true);

            Bolo obj = new ObjectFieldBuilder<>(Bolo.class)
                  .with("quantidadeVendida", quantidadeVendida)
                  .with("quantidadeEmEstoque", quantidadeEmEstoque)
                  .build();

            method.invoke(obj, qtd);

            assertEquals(quantidadeVendidaEsperada, getField(obj, "quantidadeVendida"),
                  "venderBolo(" + qtd + ") não atualizou quantidadeVendida corretamente");
            assertEquals(quantidadeEmEstoqueEsperada, getField(obj, "quantidadeEmEstoque"),
                  "venderBolo(" + qtd + ") não atualizou quantidadeEmEstoque corretamente");
        }
    }

    @Nested
    @DisplayName("aumentarEstoque()")
    class AumentarEstoqueTest {

        @ParameterizedTest
        @DisplayName("Deve aumentar o estoque corretamente")
        @ArgumentsSource(AumentarEstoqueProvider.class)
        void testAumentarEstoque(Integer quantidadeEmEstoque, Integer qtd, Integer quantidadeEmEstoqueEsperada)
              throws ReflectiveOperationException {

            Class<Bolo> clazz = Bolo.class;
            Method method = clazz.getDeclaredMethod("aumentarEstoque", Integer.class);

            method.setAccessible(true);

            Bolo obj = new ObjectFieldBuilder<>(Bolo.class)
                  .with("quantidadeEmEstoque", quantidadeEmEstoque)
                  .build();

            method.invoke(obj, qtd);

            assertEquals(quantidadeEmEstoqueEsperada, getField(obj, "quantidadeEmEstoque"),
                  "aumentarEstoque(" + qtd + ") não atualizou quantidadeEmEstoque corretamente");
        }
    }

    @Nested
    @DisplayName("quantidadeDisponivel()")
    class QuantidadeDisponivelTest {

        @ParameterizedTest
        @DisplayName("Deve retornar a quantidade disponível corretamente")
        @ArgumentsSource(QuantidadeDisponivelProvider.class)
        void testQuantidadeDisponivel(Integer quantidadeEmEstoque, Integer valorEsperado)
              throws ReflectiveOperationException {

            Class<Bolo> clazz = Bolo.class;
            Method method = clazz.getDeclaredMethod("quantidadeDisponivel");

            method.setAccessible(true);

            Bolo obj = new ObjectFieldBuilder<>(Bolo.class)
                  .with("quantidadeEmEstoque", quantidadeEmEstoque)
                  .build();

            Integer disponivel = (Integer) method.invoke(obj);

            assertEquals(valorEsperado, disponivel,
                  "quantidadeDisponivel() deveria retornar " + valorEsperado + ", mas retornou " + disponivel);
        }
    }

    @Nested
    @DisplayName("totalVendido()")
    class TotalVendidoTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o total vendido corretamente")
        @ArgumentsSource(TotalVendidoProvider.class)
        void testTotalVendido(Double valor, Integer quantidadeVendida, Double valorEsperado)
              throws ReflectiveOperationException {

            Class<Bolo> clazz = Bolo.class;
            Method method = clazz.getDeclaredMethod("totalVendido");

            method.setAccessible(true);

            Bolo obj = new ObjectFieldBuilder<>(Bolo.class)
                  .with("valor", valor)
                  .with("quantidadeVendida", quantidadeVendida)
                  .build();

            Double total = (Double) method.invoke(obj);

            assertEquals(valorEsperado, total, 0.01,
                  "totalVendido() deveria retornar " + valorEsperado + ", mas retornou " + total);
        }
    }
}
