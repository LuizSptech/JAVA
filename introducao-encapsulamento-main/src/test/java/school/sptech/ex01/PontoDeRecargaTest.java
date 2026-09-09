package school.sptech.ex01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import school.sptech.ex01.provider.BloquearProvider;
import school.sptech.ex01.provider.ConsultarSaldoProvider;
import school.sptech.ex01.provider.RecarregarProvider;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertMethodReturnType;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("PontoDeRecarga")
class PontoDeRecargaTest {

    private final Class<PontoDeRecarga> clazz = PontoDeRecarga.class;

    private PontoDeRecarga novoPonto(Integer qtdConsultas, Integer qtdRecargas)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(PontoDeRecarga.class)
              .with("qtdConsultasRealizadas", qtdConsultas)
              .with("qtdRecargasRealizadas", qtdRecargas)
              .build();
    }

    private BilheteUnico novoBilhete(Double saldo, Boolean bloqueado)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(BilheteUnico.class)
              .with("titular", "Fulano de Tal")
              .with("ano", 2020)
              .with("estudante", false)
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
            assertPrivateField(clazz, "qtdConsultasRealizadas", Integer.class);
            assertPrivateField(clazz, "qtdRecargasRealizadas", Integer.class);
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
            assertGetter(clazz, "qtdConsultasRealizadas", Integer.class);
            assertGetter(clazz, "qtdRecargasRealizadas", Integer.class);
            assertNoSetter(clazz, "qtdConsultasRealizadas", Integer.class);
            assertNoSetter(clazz, "qtdRecargasRealizadas", Integer.class);
        }
    }

    @Nested
    @DisplayName("Metodos")
    class MetodosTest {

        @Test
        @DisplayName("Deve conter os metodos publicos com as assinaturas e retornos corretos")
        void validarMetodos() {
            assertPublicMethod(clazz, "consultarSaldo", BilheteUnico.class);
            assertMethodReturnType(clazz, "consultarSaldo", Double.class, BilheteUnico.class);
            assertPublicMethod(clazz, "recarregar", BilheteUnico.class, Double.class);
            assertMethodReturnType(clazz, "recarregar", void.class, BilheteUnico.class, Double.class);
            assertPublicMethod(clazz, "bloquear", BilheteUnico.class);
            assertMethodReturnType(clazz, "bloquear", void.class, BilheteUnico.class);
        }
    }

    @Nested
    @DisplayName("consultarSaldo(BilheteUnico)")
    class ConsultarSaldoTest {

        @ParameterizedTest(name = "consultasIni={0}, recargasIni={1}, saldo={2}, bloqueado={3}"
              + " -> retorno={4}, consultas={5}")
        @ArgumentsSource(ConsultarSaldoProvider.class)
        @DisplayName("Deve retornar o saldo do bilhete e atualizar qtdConsultasRealizadas")
        void deveConsultarSaldo(Integer consultasIni, Integer recargasIni, Double saldo, Boolean bloqueado,
              Double retornoEsp, Integer consultasEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "consultarSaldo", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("consultarSaldo", BilheteUnico.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(consultasIni, recargasIni);
            BilheteUnico bilhete = novoBilhete(saldo, bloqueado);

            Object retorno = invocar(metodo, ponto, bilhete);

            assertEquals(retornoEsp, (Double) retorno, 0.01,
                  "consultarSaldo(BilheteUnico) nao retornou o valor esperado");
            assertEquals(consultasEsp, getField(ponto, "qtdConsultasRealizadas"),
                  "consultarSaldo(BilheteUnico) nao atualizou qtdConsultasRealizadas corretamente");
            assertEquals(recargasIni, getField(ponto, "qtdRecargasRealizadas"),
                  "consultarSaldo(BilheteUnico) nao deveria alterar qtdRecargasRealizadas");
        }

        @Test
        @DisplayName("Com bilhete null deve retornar 0.0 e nao alterar os contadores")
        void comBilheteNull() throws ReflectiveOperationException {
            assertPublicMethod(clazz, "consultarSaldo", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("consultarSaldo", BilheteUnico.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(5, 3);

            Object retorno = invocar(metodo, ponto, (BilheteUnico) null);

            assertEquals(0.0, (Double) retorno, 0.01,
                  "consultarSaldo(null) deveria retornar 0.0");
            assertEquals(5, getField(ponto, "qtdConsultasRealizadas"),
                  "consultarSaldo(null) nao deveria alterar qtdConsultasRealizadas");
            assertEquals(3, getField(ponto, "qtdRecargasRealizadas"),
                  "consultarSaldo(null) nao deveria alterar qtdRecargasRealizadas");
        }
    }

    @Nested
    @DisplayName("recarregar(BilheteUnico, Double)")
    class RecarregarTest {

        @ParameterizedTest(name = "consultasIni={0}, recargasIni={1}, saldoIni={2}, bloqueado={3},"
              + " valor={4} -> saldo={5}, recargas={6}")
        @ArgumentsSource(RecarregarProvider.class)
        @DisplayName("Deve recarregar o bilhete e atualizar qtdRecargasRealizadas")
        void deveRecarregar(Integer consultasIni, Integer recargasIni, Double saldoIni, Boolean bloqueado,
              Double valorRecarga, Double saldoEsp, Integer recargasEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "recarregar", BilheteUnico.class, Double.class);
            Method metodo = clazz.getDeclaredMethod("recarregar", BilheteUnico.class, Double.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(consultasIni, recargasIni);
            BilheteUnico bilhete = novoBilhete(saldoIni, bloqueado);

            invocar(metodo, ponto, bilhete, valorRecarga);

            assertEquals(saldoEsp, (Double) getField(bilhete, "saldo"), 0.01,
                  "recarregar(BilheteUnico, Double) nao atualizou o saldo do bilhete corretamente");
            assertEquals(recargasEsp, getField(ponto, "qtdRecargasRealizadas"),
                  "recarregar(BilheteUnico, Double) nao atualizou qtdRecargasRealizadas corretamente");
            assertEquals(consultasIni, getField(ponto, "qtdConsultasRealizadas"),
                  "recarregar(BilheteUnico, Double) nao deveria alterar qtdConsultasRealizadas");
        }

        @Test
        @DisplayName("Com bilhete null nao deve lancar excecao nem alterar os contadores")
        void comBilheteNull() throws ReflectiveOperationException {
            assertPublicMethod(clazz, "recarregar", BilheteUnico.class, Double.class);
            Method metodo = clazz.getDeclaredMethod("recarregar", BilheteUnico.class, Double.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(5, 3);

            invocar(metodo, ponto, (BilheteUnico) null, 100.0);

            assertEquals(5, getField(ponto, "qtdConsultasRealizadas"),
                  "recarregar(null, ...) nao deveria alterar qtdConsultasRealizadas");
            assertEquals(3, getField(ponto, "qtdRecargasRealizadas"),
                  "recarregar(null, ...) nao deveria alterar qtdRecargasRealizadas");
        }
    }

    @Nested
    @DisplayName("bloquear(BilheteUnico)")
    class BloquearTest {

        @ParameterizedTest(name = "bloqueadoIni={0} -> bloqueado={1}")
        @ArgumentsSource(BloquearProvider.class)
        @DisplayName("Deve bloquear o bilhete unico")
        void deveBloquear(Boolean bloqueadoIni, Boolean bloqueadoEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "bloquear", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("bloquear", BilheteUnico.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(7, 4);
            BilheteUnico bilhete = novoBilhete(50.0, bloqueadoIni);

            invocar(metodo, ponto, bilhete);

            assertEquals(bloqueadoEsp, getField(bilhete, "bloqueado"),
                  "bloquear(BilheteUnico) nao atualizou o atributo bloqueado do bilhete");
            assertEquals(7, getField(ponto, "qtdConsultasRealizadas"),
                  "bloquear(BilheteUnico) nao deveria alterar qtdConsultasRealizadas");
            assertEquals(4, getField(ponto, "qtdRecargasRealizadas"),
                  "bloquear(BilheteUnico) nao deveria alterar qtdRecargasRealizadas");
        }

        @Test
        @DisplayName("Com bilhete null nao deve lancar excecao nem alterar os contadores")
        void comBilheteNull() throws ReflectiveOperationException {
            assertPublicMethod(clazz, "bloquear", BilheteUnico.class);
            Method metodo = clazz.getDeclaredMethod("bloquear", BilheteUnico.class);
            metodo.setAccessible(true);

            PontoDeRecarga ponto = novoPonto(7, 4);

            invocar(metodo, ponto, (BilheteUnico) null);

            assertEquals(7, getField(ponto, "qtdConsultasRealizadas"),
                  "bloquear(null) nao deveria alterar qtdConsultasRealizadas");
            assertEquals(4, getField(ponto, "qtdRecargasRealizadas"),
                  "bloquear(null) nao deveria alterar qtdRecargasRealizadas");
        }
    }

    private static Object invocar(Method metodo, Object alvo, Object... args) {
        try {
            return metodo.invoke(alvo, args);
        } catch (InvocationTargetException e) {
            fail("O metodo '" + metodo.getName() + "' lancou " + e.getCause()
                  + " para os dados do cenario.");
        } catch (IllegalAccessException e) {
            fail("Nao foi possivel invocar o metodo '" + metodo.getName() + "': " + e.getMessage());
        }
        return null;
    }
}
