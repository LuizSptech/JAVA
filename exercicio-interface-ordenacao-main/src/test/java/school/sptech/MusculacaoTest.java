package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.musculacao.CalcularMensalidadeProvider;
import school.sptech.provider.musculacao.ConstructorProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Musculacao")
public class MusculacaoTest {

    static Object criar(String nome, Double precoBase, Boolean vinteQuatroHoras)
          throws ReflectiveOperationException {
        Constructor<Musculacao> construtor;
        try {
            construtor = Musculacao.class.getDeclaredConstructor(String.class, Double.class, Boolean.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Deve possuir um construtor com os parâmetros (String, Double, Boolean)");
        }
        return construtor.newInstance(nome, precoBase, vinteQuatroHoras);
    }

    @Nested
    @DisplayName("Musculacao 1. Herança e Encapsulamento")
    class EstruturaTest {

        @Test
        @DisplayName("1. Deve herdar de Plano e não deve ser Agendavel")
        void test1() {
            assertAll(
                  () -> assertEquals(Plano.class, Musculacao.class.getSuperclass(), "Musculacao deve herdar de Plano"),
                  () -> assertFalse(Agendavel.class.isAssignableFrom(Musculacao.class),
                        "Musculacao não deve implementar Agendavel")
            );
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiAtributos(Musculacao.class, "vinteQuatroHoras"),
                  () -> EncapsulamentoUtil.assertAtributosPrivados(Musculacao.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(Musculacao.class)
            );
        }
    }

    @Nested
    @DisplayName("Musculacao 2. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("1. Construtor deve inicializar os atributos corretamente")
        void test1() throws ReflectiveOperationException {
            Object musculacao = criar("Musculação Livre", 100.0, true);

            assertAll(
                  () -> assertEquals("Musculação Livre", CampoUtil.campo(musculacao, "nome"), "nome incorreto"),
                  () -> assertEquals(100.0, CampoUtil.campo(musculacao, "precoBase"), "precoBase incorreto"),
                  () -> assertEquals(true, CampoUtil.campo(musculacao, "vinteQuatroHoras"), "vinteQuatroHoras incorreto")
            );
        }

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("2. Deve validar os dados no construtor (inclusive os de Plano)")
        void test2(String nome, Double precoBase, Boolean vinteQuatroHoras, Class<? extends Exception> excecao,
              String cenario) {
            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> criar(nome, precoBase, vinteQuatroHoras));
                return;
            }
            ExceptionUtil.assertLanca(excecao, () -> criar(nome, precoBase, vinteQuatroHoras));
        }
    }

    @Nested
    @DisplayName("Musculacao 3. Método - calcularMensalidade")
    class CalcularMensalidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(CalcularMensalidadeProvider.class)
        @DisplayName("Planos 24 horas possuem acréscimo de 20%")
        void test1(Object musculacao, Double expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Musculacao.class.getDeclaredMethod("calcularMensalidade");

            assertEquals(expected, (Double) metodo.invoke(musculacao), 0.01, "Mensalidade incorreta");
        }
    }
}
