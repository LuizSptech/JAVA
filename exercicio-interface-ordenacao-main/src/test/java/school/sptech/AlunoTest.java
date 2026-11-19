package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.aluno.AdicionarAgendamentoProvider;
import school.sptech.provider.aluno.ConstructorProvider;
import school.sptech.provider.aluno.MatricularProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Aluno")
public class AlunoTest {

    static Object criar(String nome) throws ReflectiveOperationException {
        Constructor<Aluno> construtor;
        try {
            construtor = Aluno.class.getDeclaredConstructor(String.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Deve possuir um construtor com o parâmetro (String nome)");
        }
        return construtor.newInstance(nome);
    }

    static String nomeDoPlano(Object plano) throws ReflectiveOperationException {
        return plano == null ? "nenhum (null)" : "\"" + CampoUtil.campo(plano, "nome") + "\"";
    }

    static void executar(Class<? extends Exception> excecao, org.junit.jupiter.api.function.Executable acao) {
        if (excecao == null) {
            ExceptionUtil.assertNaoLanca(acao);
        } else {
            ExceptionUtil.assertLanca(excecao, acao);
        }
    }

    @Nested
    @DisplayName("Aluno 1. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            EncapsulamentoUtil.assertPossuiAtributos(Aluno.class, "nome", "plano", "agendamentos");
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertAtributosPrivados(Aluno.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(Aluno.class)
            );
        }

        @Test
        @DisplayName("3. Validar Métodos")
        void test3() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Aluno.class, "matricular", Plano.class),
                  () -> EncapsulamentoUtil.assertPossuiMetodo(Aluno.class, "adicionarAgendamento", Agendavel.class)
            );
        }
    }

    @Nested
    @DisplayName("Aluno 2. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("1. Deve começar sem plano e sem agendamentos")
        void test1() throws ReflectiveOperationException {
            Object aluno = criar("Ana");

            assertAll(
                  () -> assertEquals("Ana", CampoUtil.campo(aluno, "nome"), "nome incorreto"),
                  () -> assertNull(CampoUtil.campo(aluno, "plano"), "O aluno deve começar sem plano"),
                  () -> assertIterableEquals(List.of(), (List<?>) CampoUtil.campo(aluno, "agendamentos"),
                        "agendamentos deve começar como uma lista vazia")
            );
        }

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("2. Deve criar o aluno com o nome informado")
        void test2(String nome, Class<? extends Exception> excecao, String cenario) {
            executar(excecao, () -> criar(nome));
        }
    }

    @Nested
    @DisplayName("Aluno 3. Método - matricular")
    class MatricularTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(MatricularProvider.class)
        @DisplayName("Deve definir o plano do aluno")
        void test1(Object aluno, Object plano, Object planoEsperado, Class<? extends Exception> excecao,
              String cenario) throws ReflectiveOperationException {
            Method metodo = Aluno.class.getDeclaredMethod("matricular", Plano.class);

            executar(excecao, () -> metodo.invoke(aluno, plano));

            Object planoAtual = CampoUtil.campo(aluno, "plano");
            assertSame(planoEsperado, planoAtual, String.format("O aluno deveria estar no plano %s, mas está em %s",
                  nomeDoPlano(planoEsperado), nomeDoPlano(planoAtual)));
        }
    }

    @Nested
    @DisplayName("Aluno 4. Método - adicionarAgendamento")
    class AdicionarAgendamentoTest {

        @ParameterizedTest(name = "{index}. {4}")
        @ArgumentsSource(AdicionarAgendamentoProvider.class)
        @DisplayName("Deve adicionar o agendável ao final da lista de agendamentos")
        void test1(Object aluno, Object agendavel, List<?> esperado, Class<? extends Exception> excecao,
              String cenario) throws ReflectiveOperationException {
            Method metodo = Aluno.class.getDeclaredMethod("adicionarAgendamento", Agendavel.class);

            executar(excecao, () -> metodo.invoke(aluno, agendavel));

            assertIterableEquals(esperado, (List<?>) CampoUtil.campo(aluno, "agendamentos"),
                  "Lista de agendamentos incorreta");
        }
    }
}
