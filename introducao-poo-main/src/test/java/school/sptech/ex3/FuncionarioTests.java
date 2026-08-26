package school.sptech.ex3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex3.provider.CalcularBonificacaoAnualProvider;
import school.sptech.ex3.provider.CalcularHoraExtraProvider;
import school.sptech.ex3.provider.CalcularValorHoraProvider;
import school.sptech.ex3.provider.ReajustarSalarioProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 3 - Empresa")
class FuncionarioTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Funcionario> clazz = Funcionario.class;

            assertField(clazz, "nome", String.class);
            assertField(clazz, "cargo", String.class);
            assertField(clazz, "salario", Double.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<Funcionario> clazz = Funcionario.class;

            assertMethod(clazz, "reajustarSalario", Integer.class);
            assertMethod(clazz, "calcularValorHora");
            assertMethod(clazz, "calcularHoraExtra", Integer.class, Integer.class);
            assertMethod(clazz, "calcularBonificacaoAnual");
        }
    }

    @Nested
    @DisplayName("reajustarSalario()")
    class ReajustarSalarioTest {

        @ParameterizedTest
        @DisplayName("Deve reajustar o salário corretamente")
        @ArgumentsSource(ReajustarSalarioProvider.class)
        void testReajustarSalario(Double salario, Integer percentual, Double salarioEsperado)
              throws ReflectiveOperationException {

            Class<Funcionario> clazz = Funcionario.class;
            Method method = clazz.getDeclaredMethod("reajustarSalario", Integer.class);

            method.setAccessible(true);

            Funcionario obj = new ObjectFieldBuilder<>(Funcionario.class)
                  .with("salario", salario)
                  .build();

            method.invoke(obj, percentual);

            assertEquals(salarioEsperado, (Double) getField(obj, "salario"), 0.01,
                  "reajustarSalario(" + percentual + ") não atualizou salario corretamente");
        }
    }

    @Nested
    @DisplayName("calcularValorHora()")
    class CalcularValorHoraTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o valor da hora corretamente")
        @ArgumentsSource(CalcularValorHoraProvider.class)
        void testCalcularValorHora(Double salario, Double valorEsperado) throws ReflectiveOperationException {

            Class<Funcionario> clazz = Funcionario.class;
            Method method = clazz.getDeclaredMethod("calcularValorHora");

            method.setAccessible(true);

            Funcionario obj = new ObjectFieldBuilder<>(Funcionario.class)
                  .with("salario", salario)
                  .build();

            Double valorHora = (Double) method.invoke(obj);

            assertEquals(valorEsperado, valorHora, 0.01,
                  "calcularValorHora() deveria retornar " + valorEsperado + ", mas retornou " + valorHora);
        }
    }

    @Nested
    @DisplayName("calcularHoraExtra()")
    class CalcularHoraExtraTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o valor da hora extra corretamente")
        @ArgumentsSource(CalcularHoraExtraProvider.class)
        void testCalcularHoraExtra(Double salario, Integer qtdHoraExtra, Integer percentualExtra,
              Double valorEsperado) throws ReflectiveOperationException {

            Class<Funcionario> clazz = Funcionario.class;
            Method method = clazz.getDeclaredMethod("calcularHoraExtra", Integer.class, Integer.class);

            method.setAccessible(true);

            Funcionario obj = new ObjectFieldBuilder<>(Funcionario.class)
                  .with("salario", salario)
                  .build();

            Double horaExtra = (Double) method.invoke(obj, qtdHoraExtra, percentualExtra);

            assertEquals(valorEsperado, horaExtra, 0.01,
                  "calcularHoraExtra(" + qtdHoraExtra + ", " + percentualExtra + ") deveria retornar "
                        + valorEsperado + ", mas retornou " + horaExtra);
        }
    }

    @Nested
    @DisplayName("calcularBonificacaoAnual()")
    class CalcularBonificacaoAnualTest {

        @ParameterizedTest
        @DisplayName("Deve calcular a bonificação anual corretamente, respeitando as faixas salariais")
        @ArgumentsSource(CalcularBonificacaoAnualProvider.class)
        void testCalcularBonificacaoAnual(Double salario, Double valorEsperado)
              throws ReflectiveOperationException {

            Class<Funcionario> clazz = Funcionario.class;
            Method method = clazz.getDeclaredMethod("calcularBonificacaoAnual");

            method.setAccessible(true);

            Funcionario obj = new ObjectFieldBuilder<>(Funcionario.class)
                  .with("salario", salario)
                  .build();

            Double bonificacao = (Double) method.invoke(obj);

            assertEquals(valorEsperado, bonificacao, 0.01,
                  "calcularBonificacaoAnual() com salario=" + salario + " deveria retornar " + valorEsperado
                        + ", mas retornou " + bonificacao);
        }
    }
}
