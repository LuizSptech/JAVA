package school.sptech.factory;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import school.sptech.Estatistica;
import school.sptech.util.ObjectFieldBuilder;

public class EstatisticaFactory {

  public static class GetPercentualDelegation {
    @RuntimeType
    public static Double getPercentual(@AllArguments Object[] args) {
      return 42.0;
    }
  }

  public static Object build(Integer qtdPublicacoesRecebidas) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Estatistica.class)
        .with("qtdPublicacoesRecebidas", qtdPublicacoesRecebidas)
        .withAbstractMethod("getPercentual", EstatisticaFactory.GetPercentualDelegation.class)
        .build();
  }
}
