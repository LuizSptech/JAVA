package school.sptech.factory;

import school.sptech.financiamento.FinanciamentoPJ;
import school.sptech.util.ObjectFieldBuilder;

public class FinanciamentoPJFactory {

    public static Object build(Integer parcelas, Integer parcelasPagas, Object veiculo,
          Object empresa) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(FinanciamentoPJ.class)
              .with("parcelas", parcelas)
              .with("parcelasPagas", parcelasPagas)
              .with("veiculo", veiculo)
              .with("empresa", empresa)
              .build();
    }

    public static Object build(Integer parcelas, Integer parcelasPagas, Object veiculo,
          String tipoEmpresa) throws ReflectiveOperationException {
        Object empresa = EmpresaFactory.build("Empresa teste", "12312312312323", tipoEmpresa);
        return build(parcelas, parcelasPagas, veiculo, empresa);
    }
}
