package school.sptech.factory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.This;
import school.sptech.Veiculo;
import school.sptech.financiamento.Financiamento;

/**
 * Implementações falsas dos métodos de {@link Financiamento}, usadas como delegação pelo
 * ObjectFieldBuilder (via ByteBuddy) para criar um Financiamento concreto nos testes.
 */
public class FinanciamentoFake {

    public static final double PORCENTAGEM_FAKE = 1.1;

    /**
     * calcularValorFinanciamento(): valor final do veículo + 10%.
     */
    public static class CalcularValorFinanciamentoFake {

        @RuntimeType
        public static Double calcularValorFinanciamento(@This Object financiamento)
              throws ReflectiveOperationException {
            Field fieldVeiculo = Financiamento.class.getDeclaredField("veiculo");
            fieldVeiculo.trySetAccessible();
            Veiculo veiculo = (Veiculo) fieldVeiculo.get(financiamento);

            Method valorFinal = Veiculo.class.getDeclaredMethod("calcularValorFinal");
            return PORCENTAGEM_FAKE * (Double) valorFinal.invoke(veiculo);
        }
    }

    /**
     * pagarParcela(Double): apenas contabiliza quantas vezes foi chamado.
     */
    public static class PagarParcelaFake {

        public static final AtomicInteger CHAMADAS = new AtomicInteger();

        @RuntimeType
        public static void pagarParcela(Double valor) {
            CHAMADAS.incrementAndGet();
        }
    }
}
