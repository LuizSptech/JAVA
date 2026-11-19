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
import school.sptech.provider.natacao.CalcularMensalidadeProvider;
import school.sptech.provider.natacao.ConstructorProvider;
import school.sptech.provider.natacao.GetCapacidadeProvider;
import school.sptech.provider.natacao.GetVagasDisponiveisProvider;
import school.sptech.provider.natacao.ReservarVagaProvider;
import school.sptech.util.CampoUtil;
import school.sptech.util.EncapsulamentoUtil;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ExceptionUtil;

@DisplayName("Natacao")
public class NatacaoTest {

    static Object criar(String nome, Double precoBase, Turno turno, Integer raias, Integer alunosPorRaia)
          throws ReflectiveOperationException {
        Constructor<Natacao> construtor;
        try {
            construtor = Natacao.class.getDeclaredConstructor(String.class, Double.class, Turno.class,
                  Integer.class, Integer.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(
                  "Deve possuir um construtor com os parâmetros (String, Double, Turno, Integer, Integer)");
        }
        return construtor.newInstance(nome, precoBase, turno, raias, alunosPorRaia);
    }

    @Nested
    @DisplayName("Natacao 1. Herança e Encapsulamento")
    class EstruturaTest {

        @Test
        @DisplayName("1. Deve herdar de Plano e implementar Agendavel")
        void test1() {
            assertAll(
                  () -> assertEquals(Plano.class, Natacao.class.getSuperclass(), "Natacao deve herdar de Plano"),
                  () -> assertTrue(Agendavel.class.isAssignableFrom(Natacao.class),
                        "Natacao deve implementar Agendavel")
            );
        }

        @Test
        @DisplayName("2. Atributos privados e apenas getters")
        void test2() {
            assertAll(
                  () -> EncapsulamentoUtil.assertPossuiAtributos(Natacao.class, "turno", "raias", "alunosPorRaia",
                        "inscritos"),
                  () -> EncapsulamentoUtil.assertAtributosPrivados(Natacao.class),
                  () -> EncapsulamentoUtil.assertApenasGetters(Natacao.class)
            );
        }
    }

    @Nested
    @DisplayName("Natacao 2. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("1. Construtor deve inicializar os atributos e começar sem inscritos")
        void test1() throws ReflectiveOperationException {
            Turno manha = EnumConstantMapping.getEnumConstant(Turno.class, "MANHA");
            Object natacao = criar("Natação Manhã", 150.0, manha, 4, 5);

            assertAll(
                  () -> assertEquals("Natação Manhã", CampoUtil.campo(natacao, "nome"), "nome incorreto"),
                  () -> assertEquals(150.0, CampoUtil.campo(natacao, "precoBase"), "precoBase incorreto"),
                  () -> assertEquals(manha, CampoUtil.campo(natacao, "turno"), "turno incorreto"),
                  () -> assertEquals(4, CampoUtil.campo(natacao, "raias"), "raias incorreto"),
                  () -> assertEquals(5, CampoUtil.campo(natacao, "alunosPorRaia"), "alunosPorRaia incorreto"),
                  () -> assertEquals(0, CampoUtil.campo(natacao, "inscritos"), "inscritos deve começar com zero")
            );
        }

        @ParameterizedTest(name = "{index}. {6}")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("2. Deve validar os dados no construtor")
        void test2(String nome, Double precoBase, Turno turno, Integer raias, Integer alunosPorRaia,
              Class<? extends Exception> excecao, String cenario) {
            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> criar(nome, precoBase, turno, raias, alunosPorRaia));
                return;
            }
            ExceptionUtil.assertLanca(excecao, () -> criar(nome, precoBase, turno, raias, alunosPorRaia));
        }
    }

    @Nested
    @DisplayName("Natacao 3. Método - calcularMensalidade")
    class CalcularMensalidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(CalcularMensalidadeProvider.class)
        @DisplayName("Manhã: -10%, Noite: +15%, Tarde: preço base")
        void test1(Object natacao, Double expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Natacao.class.getDeclaredMethod("calcularMensalidade");

            assertEquals(expected, (Double) metodo.invoke(natacao), 0.01, "Mensalidade incorreta");
        }
    }

    @Nested
    @DisplayName("Natacao 4. Método - getVagasDisponiveis")
    class GetVagasDisponiveisTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetVagasDisponiveisProvider.class)
        @DisplayName("Deve retornar raias x alunosPorRaia - inscritos")
        void test1(Object natacao, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Natacao.class.getDeclaredMethod("getVagasDisponiveis");

            assertEquals(expected, metodo.invoke(natacao), "Quantidade de vagas disponíveis incorreta");
        }
    }

    @Nested
    @DisplayName("Natacao 5. Método - getCapacidade")
    class GetCapacidadeTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetCapacidadeProvider.class)
        @DisplayName("Deve retornar a capacidade total (raias x alunosPorRaia)")
        void test1(Object objeto, Integer expected, String cenario) throws ReflectiveOperationException {
            Method metodo = Natacao.class.getDeclaredMethod("getCapacidade");

            assertEquals(expected, metodo.invoke(objeto), "Capacidade incorreta");
        }
    }

    @Nested
    @DisplayName("Natacao 6. Método - reservarVaga")
    class ReservarVagaTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(ReservarVagaProvider.class)
        @DisplayName("Deve ocupar uma vaga ou lançar VagaIndisponivelException quando não houver vagas")
        void test1(Object objeto, Integer inscritosEsperado, Class<? extends Exception> excecao, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Natacao.class.getDeclaredMethod("reservarVaga");

            if (excecao == null) {
                ExceptionUtil.assertNaoLanca(() -> metodo.invoke(objeto));
            } else {
                ExceptionUtil.assertLanca(excecao, () -> metodo.invoke(objeto));
            }

            assertEquals(inscritosEsperado, CampoUtil.campo(objeto, "inscritos"),
                  "Valor de inscritos incorreto após reservarVaga");
        }
    }
}
