package school.sptech.factory;

import school.sptech.financiamento.Financiamento;
import school.sptech.util.ObjectFieldBuilder;

public class FinanciamentoFactory {

    public static Object build(Integer parcelas, Integer parcelasPagas, Object veiculo)
          throws ReflectiveOperationException {
        return builder(parcelas, parcelasPagas, veiculo).build();
    }

    /**
     * Financiamento cujo pagarParcela apenas incrementa {@link FinanciamentoFake.PagarParcelaFake#CHAMADAS}.
     */
    public static Object buildComPagarParcelaFake(Integer parcelas, Integer parcelasPagas,
          Object veiculo) throws ReflectiveOperationException {
        return builder(parcelas, parcelasPagas, veiculo)
              .withAbstractMethod("pagarParcela", FinanciamentoFake.PagarParcelaFake.class)
              .build();
    }

    private static ObjectFieldBuilder<Financiamento> builder(Integer parcelas,
          Integer parcelasPagas, Object veiculo) {
        return new ObjectFieldBuilder<>(Financiamento.class)
              .with("parcelas", parcelas)
              .with("parcelasPagas", parcelasPagas)
              .with("veiculo", veiculo)
              .withAbstractMethod("calcularValorFinanciamento",
                    FinanciamentoFake.CalcularValorFinanciamentoFake.class);
    }
}
