package school.sptech.provider.estatisticajornalista;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EstatisticaJornalistaFactory;

import java.util.stream.Stream;

public class GetPercentualProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EstatisticaJornalistaFactory.build(0, "Ana", 0);
    Object e2 = EstatisticaJornalistaFactory.build(5, "Bruno", 2);
    Object e3 = EstatisticaJornalistaFactory.build(10, "Carla", 4);
    Object e4 = EstatisticaJornalistaFactory.build(3, "Daniel", 1);
    Object e5 = EstatisticaJornalistaFactory.build(8, "Eva", 3);
    Object e6 = EstatisticaJornalistaFactory.build(4, "Fábio", 0);
    Object e7 = EstatisticaJornalistaFactory.build(6, "Gina", 6);
    Object e8 = EstatisticaJornalistaFactory.build(7, "Hugo", 1);
    Object e9 = EstatisticaJornalistaFactory.build(2, "Irene", 2);
    Object e10 = EstatisticaJornalistaFactory.build(9, "João", 5);

    return Stream.of(
        Arguments.of(e1, 0.0, "Nenhuma publicação recebida (evita divisão por zero)"),
        Arguments.of(e2, 40.0, "2 de 5 publicações do jornalista"),
        Arguments.of(e3, 40.0, "4 de 10 publicações do jornalista"),
        Arguments.of(e4, 33.33, "1 de 3 publicações do jornalista"),
        Arguments.of(e5, 37.5, "3 de 8 publicações do jornalista"),
        Arguments.of(e6, 0.0, "0 de 4 publicações do jornalista"),
        Arguments.of(e7, 100.0, "6 de 6 publicações do jornalista"),
        Arguments.of(e8, 14.29, "1 de 7 publicações do jornalista"),
        Arguments.of(e9, 100.0, "2 de 2 publicações do jornalista"),
        Arguments.of(e10, 55.56, "5 de 9 publicações do jornalista")
    );
  }
}
