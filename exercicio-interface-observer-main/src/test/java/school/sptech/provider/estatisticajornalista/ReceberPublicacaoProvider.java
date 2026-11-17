package school.sptech.provider.estatisticajornalista;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EstatisticaJornalistaFactory;
import school.sptech.factory.NoticiaFactory;

import java.util.stream.Stream;

public class ReceberPublicacaoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EstatisticaJornalistaFactory.build(0, "Ana", 0);
    Object e2 = EstatisticaJornalistaFactory.build(5, "Bruno", 2);
    Object e3 = EstatisticaJornalistaFactory.build(10, "Carla", 4);
    Object e4 = EstatisticaJornalistaFactory.build(3, "Daniel", 1);
    Object e5 = EstatisticaJornalistaFactory.build(8, "Eva", 3);

    Object n1 = NoticiaFactory.build("Noticia 1", "POLITICA", "Ana", 5);
    Object n2 = NoticiaFactory.build("Noticia 2", "ENTRETENIMENTO", "Bruno", 3);
    Object n3 = NoticiaFactory.build("Noticia 3", "ESPORTE", "Carla", 4);
    Object n4 = NoticiaFactory.build("Noticia 4", "ESPORTE", "Daniel", 2);
    Object n5 = NoticiaFactory.build("Noticia 5", "TECNOLOGIA", "Eva", 6);

    return Stream.of(
        Arguments.of(e1, n1, 1, 1, "Ana sem publicações recebe notícia de Ana"),
        Arguments.of(e2, n5, 6, 2, "Bruno (2 de 5) recebe notícia de Eva"),
        Arguments.of(e3, n3, 11, 5, "Carla (4 de 10) recebe notícia de Carla"),
        Arguments.of(e4, n4, 4, 2, "Daniel (1 de 3) recebe notícia de Daniel"),
        Arguments.of(e5, n2, 9, 3, "Eva (3 de 8) recebe notícia de Bruno")
    );
  }
}
