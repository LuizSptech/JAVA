package school.sptech.factory;

import school.sptech.financiamento.FinanciamentoPF;
import school.sptech.util.ObjectFieldBuilder;

public class FinanciamentoPFFactory {

    public static Object build(Integer parcelas, Integer parcelasPagas, Object veiculo,
          Object pessoa) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(FinanciamentoPF.class)
              .with("parcelas", parcelas)
              .with("parcelasPagas", parcelasPagas)
              .with("veiculo", veiculo)
              .with("pessoa", pessoa)
              .build();
    }

    public static Object build(Object veiculo) throws ReflectiveOperationException {
        return build(1, 0, veiculo, null);
    }
}
