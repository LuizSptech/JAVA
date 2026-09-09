package school.sptech.ex01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import school.sptech.ex01.provider.CobrarPassagemBilheteProvider;
import school.sptech.ex01.provider.CobrarPassagemDinheiroProvider;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Onibus")
class OnibusTest {

    private final Class<Onibus> clazz = Onibus.class;

    private Onibus novoOnibus(Integer qtdPassageiros, Double valorPassagem)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Onibus.class)
              .with("qtdPassageiros", qtdPassageiros)
              .with("valorPassagem", valorPassagem)
              .build();
    }

    private BilheteUnico novoBilhete(Boolean estudante, Double saldo, Boolean bloqueado)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(BilheteUnico.class)
              .with("titular", "Fulano de Tal")
              .with("ano", 2020)
              .with("estudante", estudante)
              .with("saldo", saldo)
              .with("bloqueado", bloqueado)
              .build();
    }

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            assertPrivateField(clazz, "qtdPassageiros", Integer.class);
            assertPrivateField(clazz, "valorPassagem", Double.class);
        }
    }

    @Nested
    @DisplayName("Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("Todos os atributos devem ser privados")
        void atributosPrivados() {
            assertAllFieldsPrivate(clazz);
        }

        @Test
        @DisplayName("Deve possuir apenas getters publicos (sem setters)")
        void gettersSemSetters() {
            assertGetter(clazz, "qtdPassageiros", Integer.class);
            assertGetter(clazz, "valorPassagem", Double.class);
            assertNoSetter(clazz, "qtdPassageiros", Integer.class);
            assertNoSetter(clazz, "valorPassagem", Double.class);
        }
    }

    @Nested
    @DisplayName("Metodos")
    class MetodosTest {

        @Test
        @DisplayName("Deve conter os metodos publicos com as assinaturas corretas")
        void validarMetodos() {
            assertPublicMethod(clazz, "cobrarPassagem", BilheteUnico.class);
            assertPublicMethod(clazz, "cobrarPassagem", Double.class);
        }
    }

    @Nested
    @DisplayName("cobrarPassagem(BilheteUnico)")
    class CobrarPassagemBilheteTest {

        @ParameterizedTest(name = "qtdIni={0}, valorPassagem={1}, estudante={2}, saldoIni={3}, bloqueado={4}"
              + " -> saldo={5}, qtd={6}")
        @ArgumentsSource(CobrarPassagemBilheteProvider.class)
        @DisplayName("Deve cobrar a passagem do bilhete e atualizar saldo e qtdPassageiros")
        void deveCobrarPassagem(Integer qtdIni, Double valorPassagem, Boolean estudante, Double saldoIni,
              Boolean bloqueado, Double saldoEsp, Integer qtdEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "cobrarPassagem", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("cobrarPassagem", BilheteUnico.class);
            metodo.setAccessible(true);

            Onibus onibus = novoOnibus(qtdIni, valorPassagem);
            BilheteUnico bilhete = novoBilhete(estudante, saldoIni, bloqueado);

            invocar(metodo, onibus, bilhete);

            assertEquals(saldoEsp, (Double) getField(bilhete, "saldo"), 0.01,
                  "cobrarPassagem(BilheteUnico) nao atualizou o saldo do bilhete corretamente");
            assertEquals(qtdEsp, getField(onibus, "qtdPassageiros"),
                  "cobrarPassagem(BilheteUnico) nao atualizou qtdPassageiros corretamente");
        }

        @Test
        @DisplayName("Com bilhete null nao deve lancar excecao nem alterar qtdPassageiros")
        void comBilheteNull() throws ReflectiveOperationException {
            assertPublicMethod(clazz, "cobrarPassagem", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("cobrarPassagem", BilheteUnico.class);
            metodo.setAccessible(true);

            Onibus onibus = novoOnibus(10, 5.0);

            invocar(metodo, onibus, (BilheteUnico) null);

            assertEquals(10, getField(onibus, "qtdPassageiros"),
                  "cobrarPassagem(null) nao deveria alterar qtdPassageiros");
        }
    }

    @Nested
    @DisplayName("cobrarPassagem(Double)")
    class CobrarPassagemDinheiroTest {

        @ParameterizedTest(name = "qtdIni={0}, valorPassagem={1}, dinheiro={2} -> qtd={3}")
        @ArgumentsSource(CobrarPassagemDinheiroProvider.class)
        @DisplayName("Deve cobrar a passagem em dinheiro e atualizar qtdPassageiros")
        void deveCobrarPassagem(Integer qtdIni, Double valorPassagem, Double dinheiro, Integer qtdEsp)
              throws ReflectiveOperationException {

            assertPublicMethod(clazz, "cobrarPassagem", Double.class);
            Method metodo = clazz.getDeclaredMethod("cobrarPassagem", Double.class);
            metodo.setAccessible(true);

            Onibus onibus = novoOnibus(qtdIni, valorPassagem);

            invocar(metodo, onibus, dinheiro);

            assertEquals(qtdEsp, getField(onibus, "qtdPassageiros"),
                  "cobrarPassagem(Double) nao atualizou qtdPassageiros corretamente");
        }
    }

    private static void invocar(Method metodo, Object alvo, Object arg) {
        try {
            metodo.invoke(alvo, new Object[]{ arg });
        } catch (InvocationTargetException e) {
            fail("O metodo '" + metodo.getName() + "' lancou " + e.getCause()
                  + " para os dados do cenario.");
        } catch (IllegalAccessException e) {
            fail("Nao foi possivel invocar o metodo '" + metodo.getName() + "': " + e.getMessage());
        }
    }
}
