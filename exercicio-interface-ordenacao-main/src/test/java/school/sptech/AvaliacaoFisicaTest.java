package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.avaliacao.ConstructorProvider;
import school.sptech.provider.avaliacao.GetCapacidadeProvider;
import school.sptech.provider.avaliacao.GetVagasDisponiveisProvider;
import school.sptech.provider.avaliacao.ReservarVagaProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("AvaliacaoFisica")
public class AvaliacaoFisicaTest {

    static Object criar(String nome, String profissional, Double preco, Integer limiteDiario)
          throws ReflectiveOperationException {
        Constructor<AvaliacaoFisica> construtor;
        try {
            construtor = AvaliacaoFisica.class.getDeclaredConstructor(String.class, String.class, Double.class,
                  Integer.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Deve possuir um construtor com os parâmetros (String, String, Double, Integer)");
        }
        return construtor.newInstance(nome, profissional, preco, limiteDiario);
    }

    @Nested
    @DisplayName("AvaliacaoFisica 1. Interface e Encapsulamento")
    class EstruturaTest {

        @Test
        @DisplayName("1. Deve implementar Agendavel e não deve ser um Plano")
        void test1() {
            assertAll(
                  () -> assertTrue(Agendavel.class.isAssignableFrom(AvaliacaoFisica.class),
                        "AvaliacaoFisica deve implementar Agendavel"),
                  () -> assertFalse(Plano.class.isAssignableFrom(AvaliacaoFisica.class),
                        "AvaliacaoFisica não deve herdar de Plano")
            );
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiAtributos(AvaliacaoFisica.class, "nome", "profissional",
                        "preco", "limiteDiario", "agendadas"),
                  () -> EncapsulamentoUtil.assertAtributosPrivados(AvaliacaoFisica.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(AvaliacaoFisica.class)
            );
        }
    }

    @Nested
    @DisplayName("AvaliacaoFisica 2. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("1. Construtor deve inicializar os atributos e começar sem agendamentos")
        void test1() throws ReflectiveOperationException {
            Object avaliacao = criar("Bioimpedância", "Dra. Lia", 80.0, 3);

            assertAll(
                  () -> assertEquals("Bioimpedância", CampoUtil.campo(avaliacao, "nome"), "nome incorreto"),
                  () -> assertEquals("Dra. Lia", CampoUtil.campo(avaliacao, "profissional"), "profissional incorreto"),
                  () -> assertEquals(80.0, CampoUtil.campo(avaliacao, "preco"), "preco incorreto"),
                  () -> assertEquals(3, CampoUtil.campo(avaliacao, "limiteDiario"), "limiteDiario incorreto"),
                  () -> assertEquals(0, CampoUtil.campo(avaliacao, "agendadas"), "agendadas deve começar com zero")
            );
        }

        @ParameterizedTest(name = "{index}. {5}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("2. Deve validar os dados no construtor")
        void test2(String nome, String profissional, Double preco, Integer limiteDiario,
              Class<? extends Exception> excecao, String cenario) {
            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> criar(nome, profissional, preco, limiteDiario));
                return;
            }
            ExceptionUtil.assertLanca(excecao, () -> criar(nome, profissional, preco, limiteDiario));
        }
    }

    @Nested
    @DisplayName("AvaliacaoFisica 3. Método - getVagasDisponiveis")
    class GetVagasDisponiveisTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetVagasDisponiveisProvider.class)
        @DisplayName("Deve retornar limiteDiario - agendadas")
        void test1(Object avaliacao, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = AvaliacaoFisica.class.getDeclaredMethod("getVagasDisponiveis");

            assertEquals(expected, metodo.invoke(avaliacao), "Quantidade de vagas disponíveis incorreta");
        }
    }

    @Nested
    @DisplayName("AvaliacaoFisica 4. Método - getCapacidade")
    class GetCapacidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetCapacidadeProvider.class)
        @DisplayName("Deve retornar a capacidade total (limiteDiario)")
        void test1(Object objeto, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = AvaliacaoFisica.class.getDeclaredMethod("getCapacidade");

            assertEquals(expected, metodo.invoke(objeto), "Capacidade incorreta");
        }
    }

    @Nested
    @DisplayName("AvaliacaoFisica 5. Método - reservarVaga")
    class ReservarVagaTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(ReservarVagaProvider.class)
        @DisplayName("Deve ocupar uma vaga ou lançar VagaIndisponivelException quando não houver vagas")
        void test1(Object objeto, Integer agendadasEsperado, Class<? extends Exception> excecao, String cenario)
              throws ReflectiveOperationException {
            Method metodo = AvaliacaoFisica.class.getDeclaredMethod("reservarVaga");

            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> metodo.invoke(objeto));
            } else {
                ExceptionUtil.assertLanca(excecao, () -> metodo.invoke(objeto));
            }

            assertEquals(agendadasEsperado, CampoUtil.campo(objeto, "agendadas"),
                  "Valor de agendadas incorreto após reservarVaga");
        }
    }
}
