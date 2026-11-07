package school.sptech.factory;

import school.sptech.Empresa;
import school.sptech.util.ObjectFieldBuilder;

public class EmpresaFactory {

    public static Object build(String razaoSocial, String cnpj, String tipoEmpresa)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Empresa.class)
              .with("razaoSocial", razaoSocial)
              .with("cnpj", cnpj)
              .withEnum("tipo", tipoEmpresa)
              .build();
    }
}
