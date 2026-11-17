package school.sptech.factory;

import school.sptech.EstatisticaJornalista;
import school.sptech.util.ObjectFieldBuilder;

public class EstatisticaJornalistaFactory {

  public static Object build(Integer qtdPublicacoesRecebidas, String jornalista, Integer qtdPublicacoesJornalista) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(EstatisticaJornalista.class)
        .with("qtdPublicacoesRecebidas", qtdPublicacoesRecebidas)
        .with("jornalista", jornalista)
        .with("qtdPublicacoesJornalista", qtdPublicacoesJornalista)
        .build();
  }
}
