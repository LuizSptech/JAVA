package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.exception.VagaIndisponivelException;
import school.sptech.provider.academia.AdicionarAvaliacaoProvider;
import school.sptech.provider.academia.AdicionarPlanoProvider;
import school.sptech.provider.academia.AgendarProvider;
import school.sptech.provider.academia.BuscarAgendaveisComVagasProvider;
import school.sptech.provider.academia.BuscarPlanosProvider;
import school.sptech.provider.academia.CadastrarAlunoProvider;
import school.sptech.provider.academia.CalcularFaturamentoMensalProvider;
import school.sptech.provider.academia.ConstructorProvider;
import school.sptech.provider.academia.OrdenarAgendaveisPorCapacidadeProvider;
import school.sptech.provider.academia.GetNomesAlunosOrdenadosProvider;
import school.sptech.provider.academia.MatricularProvider;
import school.sptech.provider.academia.OrdenarPlanosPorMensalidadeProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Academia")
public class AcademiaTest {

    static Method metodo(String nome, Class<?>... parametros) throws NoSuchMethodException {
        return Academia.class.getDeclaredMethod(nome, parametros);
    }

    static List<?> lista(Object objeto, String campo) throws ReflectiveOperationException {
        return (List<?>) CampoUtil.campo(objeto, campo);
    }

    static String nomeDoPlano(Object plano) throws ReflectiveOperationException {
        return plano == null ? "nenhum (null)" : "\"" + CampoUtil.campo(plano, "nome") + "\"";
    }

    static void executar(Class<? extends Exception> excecao, Executable acao) {
        if (excecao == null) {
            ExceptionUtil.assertNaoLanca(acao);
        } else {
            ExceptionUtil.assertLanca(excecao, acao);
        }
    }

    /**
     * Invoca um método de ordenação e confere o resultado e que a lista original não mudou.
     */
    static void assertOrdenacao(Object academia, String nomeMetodo, String campoOriginal, List<?> esperado)
          throws ReflectiveOperationException {
        List<?> original = new ArrayList<>(lista(academia, campoOriginal));

        List<?> resposta = (List<?>) metodo(nomeMetodo).invoke(academia);

        assertAll(
              () -> assertIterableEquals(esperado, resposta, nomeMetodo + " retornou a ordem errada"),
              () -> assertIterableEquals(original, lista(academia, campoOriginal),
                    nomeMetodo + " não pode alterar a ordem da lista original de " + campoOriginal)
        );
    }

    @Nested
    @DisplayName("Academia 1. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            EncapsulamentoUtil.assertPossuiAtributos(Academia.class, "nome", "planos", "alunos", "avaliacoes");
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertAtributosPrivados(Academia.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(Academia.class)
            );
        }

        @Test
        @DisplayName("3. Construtor deve inicializar o nome e as listas vazias")
        void test3() throws ReflectiveOperationException {
            assertDoesNotThrow(() -> Academia.class.getDeclaredConstructor(String.class),
                  "Academia deve possuir o construtor (String nome)");

            Object academia = Academia.class.getDeclaredConstructor(String.class).newInstance("SPTech Fit");

            assertAll(
                  () -> assertEquals("SPTech Fit", CampoUtil.campo(academia, "nome"), "nome incorreto"),
                  () -> assertIterableEquals(List.of(), lista(academia, "planos"), "planos deve começar vazia"),
                  () -> assertIterableEquals(List.of(), lista(academia, "alunos"), "alunos deve começar vazia"),
                  () -> assertIterableEquals(List.of(), lista(academia, "avaliacoes"), "avaliacoes deve começar vazia")
            );
        }

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Deve validar o nome no construtor")
        void test4(String nome, Class<? extends Exception> excecao, String cenario) {
            executar(excecao, () -> Academia.class.getDeclaredConstructor(String.class).newInstance(nome));
        }
    }

    @Nested
    @DisplayName("Academia 2. Método - adicionarPlano")
    class AdicionarPlanoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(AdicionarPlanoProvider.class)
        @DisplayName("Deve adicionar o plano ao final da lista, sem nomes repetidos")
        void test1(Object academia, Object objeto, Class<? extends Exception> excecao, List<?> esperado, String cenario) throws ReflectiveOperationException {
            executar(excecao, () -> metodo("adicionarPlano", Plano.class).invoke(academia, objeto));

            assertIterableEquals(esperado, lista(academia, "planos"), "Lista de planos incorreta");
        }
    }

    @Nested
    @DisplayName("Academia 3. Método - adicionarAvaliacao")
    class AdicionarAvaliacaoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(AdicionarAvaliacaoProvider.class)
        @DisplayName("Deve adicionar a avaliação ao final da lista, sem nomes repetidos")
        void test1(Object academia, Object objeto, Class<? extends Exception> excecao, List<?> esperado, String cenario) throws ReflectiveOperationException {
            executar(excecao, () -> metodo("adicionarAvaliacao", AvaliacaoFisica.class).invoke(academia, objeto));

            assertIterableEquals(esperado, lista(academia, "avaliacoes"), "Lista de avaliacoes incorreta");
        }
    }

    @Nested
    @DisplayName("Academia 4. Método - cadastrarAluno")
    class CadastrarAlunoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(CadastrarAlunoProvider.class)
        @DisplayName("Deve adicionar o aluno ao final da lista, sem nomes repetidos")
        void test1(Object academia, Object objeto, Class<? extends Exception> excecao, List<?> esperado, String cenario) throws ReflectiveOperationException {
            executar(excecao, () -> metodo("cadastrarAluno", Aluno.class).invoke(academia, objeto));

            assertIterableEquals(esperado, lista(academia, "alunos"), "Lista de alunos incorreta");
        }
    }

    @Nested
    @DisplayName("Academia 5. Método - matricular")
    class MatricularTest {

        @ParameterizedTest(name = "{index}. {6}")
        @ArgumentsSource(MatricularProvider.class)
        @DisplayName("Deve matricular o aluno no plano informado")
        void test1(Object academia, String nomeAluno, String nomePlano, Object aluno, Object planoEsperado, Class<? extends Exception> excecao, String cenario) throws ReflectiveOperationException {
            executar(excecao, () -> metodo("matricular", String.class, String.class).invoke(academia, nomeAluno, nomePlano));

            Object planoAtual = CampoUtil.campo(aluno, "plano");
            assertSame(planoEsperado, planoAtual, String.format(
                  "O aluno deveria estar no plano %s, mas está em %s",
                  nomeDoPlano(planoEsperado), nomeDoPlano(planoAtual)));
        }
    }

    @Nested
    @DisplayName("Academia 6. Método - agendar")
    class AgendarTest {

        @ParameterizedTest(name = "{index}. {8}")
        @ArgumentsSource(AgendarProvider.class)
        @DisplayName("Deve reservar a vaga e adicionar o agendável ao aluno")
        void test1(Object academia, String nomeAluno, Object agendavel, Class<? extends Exception> excecao, Object aluno, List<?> agendamentosEsperados, String contador, Integer valorEsperado, String cenario) throws ReflectiveOperationException {
            Executable agendar =
                  () -> metodo("agendar", String.class, Agendavel.class).invoke(academia, nomeAluno, agendavel);
            if (VagaIndisponivelException.class.equals(excecao)) {
                ExceptionUtil.assertLanca(excecao, agendar,
                      "o agendar deve reservar a vaga, e sem vagas a reserva lança essa exception");
            } else {
                executar(excecao, agendar);
            }

            assertIterableEquals(agendamentosEsperados, lista(aluno, "agendamentos"),
                  "Agendamentos do aluno incorretos (o agendável só deve ser adicionado se a vaga for reservada)");
            if (agendavel != null) {
                assertEquals(valorEsperado, CampoUtil.campo(agendavel, contador), String.format(
                      "%s deveria ser %d (o agendar deve reservar a vaga só quando os dados forem válidos)",
                      contador, valorEsperado));
            }
        }
    }

    @Nested
    @DisplayName("Academia 7. Método - buscarPlanos")
    class BuscarPlanosTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(BuscarPlanosProvider.class)
        @DisplayName("Deve retornar os planos cujo nome contém o texto (ignorando maiúsculas/minúsculas)")
        void test1(Object academia, String texto, List<?> esperado, String cenario) throws ReflectiveOperationException {
            List<?> resposta = (List<?>) metodo("buscarPlanos", String.class).invoke(academia, texto);

            assertIterableEquals(esperado, resposta, "Planos encontrados incorretos");
        }
    }

    @Nested
    @DisplayName("Academia 8. Método - calcularFaturamentoMensal")
    class CalcularFaturamentoMensalTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(CalcularFaturamentoMensalProvider.class)
        @DisplayName("Deve somar a mensalidade dos alunos matriculados")
        void test1(Object academia, Double esperado, String cenario) throws ReflectiveOperationException {
            Double resposta = (Double) metodo("calcularFaturamentoMensal").invoke(academia);

            assertEquals(esperado, resposta, 0.01, "Faturamento mensal incorreto");
        }
    }

    @Nested
    @DisplayName("Academia 9. Método - buscarAgendaveisComVagas")
    class BuscarAgendaveisComVagasTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(BuscarAgendaveisComVagasProvider.class)
        @DisplayName("Deve retornar os agendáveis que ainda possuem vagas")
        void test1(Object academia, List<?> esperado, String cenario) throws ReflectiveOperationException {
            List<?> resposta = (List<?>) metodo("buscarAgendaveisComVagas").invoke(academia);

            assertIterableEquals(esperado, resposta, "Agendáveis com vagas incorretos");
        }
    }

    @Nested
    @DisplayName("Academia 10. Método - getNomesAlunosOrdenados")
    class GetNomesAlunosOrdenadosTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetNomesAlunosOrdenadosProvider.class)
        @DisplayName("Deve ordenar os nomes de A a Z sem alterar a lista original")
        void test1(Object academia, List<?> esperado, String cenario) throws ReflectiveOperationException {
            assertOrdenacao(academia, "getNomesAlunosOrdenados", "alunos", esperado);
        }
    }

    @Nested
    @DisplayName("Academia 11. Método - ordenarPlanosPorMensalidade")
    class OrdenarPlanosPorMensalidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(OrdenarPlanosPorMensalidadeProvider.class)
        @DisplayName("Deve ordenar da menor para a maior mensalidade")
        void test1(Object academia, List<?> esperado, String cenario) throws ReflectiveOperationException {
            assertOrdenacao(academia, "ordenarPlanosPorMensalidade", "planos", esperado);
        }
    }

    @Nested
    @DisplayName("Academia 12. Método - ordenarAgendaveisPorCapacidade (Desafio)")
    class OrdenarAgendaveisPorCapacidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(OrdenarAgendaveisPorCapacidadeProvider.class)
        @DisplayName("Deve ordenar da maior para a menor capacidade (empate: nome A-Z)")
        void test1(Object academia, List<?> esperado, String cenario) throws ReflectiveOperationException {
            assertOrdenacao(academia, "ordenarAgendaveisPorCapacidade", "planos", esperado);
        }
    }
}
