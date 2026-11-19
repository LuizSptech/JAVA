package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.personal.CalcularMensalidadeProvider;
import school.sptech.provider.personal.ConstructorProvider;
import school.sptech.provider.personal.GetCapacidadeProvider;
import school.sptech.provider.personal.GetVagasDisponiveisProvider;
import school.sptech.provider.personal.ReservarVagaProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Personal")
public class PersonalTest {

    static Object criar(String nome, Double precoBase, String instrutor, Integer sessoesSemana)
          throws ReflectiveOperationException {
        Constructor<Personal> construtor;
        try {
            construtor = Personal.class.getDeclaredConstructor(String.class, Double.class, String.class,
                  Integer.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError("Deve possuir um construtor com os parâmetros (String, Double, String, Integer)");
        }
        return construtor.newInstance(nome, precoBase, instrutor, sessoesSemana);
    }

    @Nested
    @DisplayName("Personal 1. Herança e Encapsulamento")
    class EstruturaTest {

        @Test
        @DisplayName("1. Deve herdar de Plano e implementar Agendavel")
        void test1() {
            assertAll(
                  () -> assertEquals(Plano.class, Personal.class.getSuperclass(), "Personal deve herdar de Plano"),
                  () -> assertTrue(Agendavel.class.isAssignableFrom(Personal.class),
                        "Personal deve implementar Agendavel")
            );
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiAtributos(Personal.class, "instrutor", "sessoesSemana",
                        "sessoesAgendadas"),
                  () -> EncapsulamentoUtil.assertAtributosPrivados(Personal.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(Personal.class)
            );
        }
    }

    @Nested
    @DisplayName("Personal 2. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("1. Construtor deve inicializar os atributos e começar sem sessões agendadas")
        void test1() throws ReflectiveOperationException {
            Object personal = criar("Personal Ana", 200.0, "Ana", 3);

            assertAll(
                  () -> assertEquals("Personal Ana", CampoUtil.campo(personal, "nome"), "nome incorreto"),
                  () -> assertEquals(200.0, CampoUtil.campo(personal, "precoBase"), "precoBase incorreto"),
                  () -> assertEquals("Ana", CampoUtil.campo(personal, "instrutor"), "instrutor incorreto"),
                  () -> assertEquals(3, CampoUtil.campo(personal, "sessoesSemana"), "sessoesSemana incorreto"),
                  () -> assertEquals(0, CampoUtil.campo(personal, "sessoesAgendadas"),
                        "sessoesAgendadas deve começar com zero")
            );
        }

        @ParameterizedTest(name = "{index}. {5}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("2. Deve validar os dados no construtor")
        void test2(String nome, Double precoBase, String instrutor, Integer sessoesSemana,
              Class<? extends Exception> excecao, String cenario) {
            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> criar(nome, precoBase, instrutor, sessoesSemana));
                return;
            }
            ExceptionUtil.assertLanca(excecao, () -> criar(nome, precoBase, instrutor, sessoesSemana));
        }
    }

    @Nested
    @DisplayName("Personal 3. Método - calcularMensalidade")
    class CalcularMensalidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(CalcularMensalidadeProvider.class)
        @DisplayName("Preço base + R$ 50,00 por sessão semanal")
        void test1(Object personal, Double expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Personal.class.getDeclaredMethod("calcularMensalidade");

            assertEquals(expected, (Double) metodo.invoke(personal), 0.01, "Mensalidade incorreta");
        }
    }

    @Nested
    @DisplayName("Personal 4. Método - getVagasDisponiveis")
    class GetVagasDisponiveisTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetVagasDisponiveisProvider.class)
        @DisplayName("Deve retornar sessoesSemana - sessoesAgendadas")
        void test1(Object personal, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Personal.class.getDeclaredMethod("getVagasDisponiveis");

            assertEquals(expected, metodo.invoke(personal), "Quantidade de vagas disponíveis incorreta");
        }
    }

    @Nested
    @DisplayName("Personal 5. Método - getCapacidade")
    class GetCapacidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetCapacidadeProvider.class)
        @DisplayName("Deve retornar a capacidade total (sessoesSemana)")
        void test1(Object objeto, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Personal.class.getDeclaredMethod("getCapacidade");

            assertEquals(expected, metodo.invoke(objeto), "Capacidade incorreta");
        }
    }

    @Nested
    @DisplayName("Personal 6. Método - reservarVaga")
    class ReservarVagaTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(ReservarVagaProvider.class)
        @DisplayName("Deve ocupar uma vaga ou lançar VagaIndisponivelException quando não houver vagas")
        void test1(Object objeto, Integer sessoesAgendadasEsperado, Class<? extends Exception> excecao, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Personal.class.getDeclaredMethod("reservarVaga");

            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> metodo.invoke(objeto));
            } else {
                ExceptionUtil.assertLanca(excecao, () -> metodo.invoke(objeto));
            }

            assertEquals(sessoesAgendadasEsperado, CampoUtil.campo(objeto, "sessoesAgendadas"),
                  "Valor de sessoesAgendadas incorreto após reservarVaga");
        }
    }
}
