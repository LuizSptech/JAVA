package school.sptech.provider.estatisticatema;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EstatisticaTemaFactory;
import school.sptech.factory.NoticiaFactory;

import java.util.stream.Stream;

public class ReceberPublicacaoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EstatisticaTemaFactory.build("POLITICA", 0, 0);
    Object e2 = EstatisticaTemaFactory.build("TECNOLOGIA", 2, 5);
    Object e3 = EstatisticaTemaFactory.build("ENTRETENIMENTO", 4, 10);
    Object e4 = EstatisticaTemaFactory.build("ESPORTE", 1, 3);
    Object e5 = EstatisticaTemaFactory.build("POLITICA", 3, 8);

    Object n1 = NoticiaFactory.build("Noticia 1", "POLITICA", "Ana", 5);
    Object n2 = NoticiaFactory.build("Noticia 2", "TECNOLOGIA", "Bruno", 3);
    Object n3 = NoticiaFactory.build("Noticia 3", "ENTRETENIMENTO", "Carla", 4);
    Object n4 = NoticiaFactory.build("Noticia 4", "ESPORTE", "Daniel", 2);
    Object n5 = NoticiaFactory.build("Noticia 5", "POLITICA", "Eva", 6);

    return Stream.of(
        Arguments.of(e1, n1, 1, 1, "POLITICA sem publicações recebe notícia de POLITICA"),
        Arguments.of(e2, n4, 6, 2, "TECNOLOGIA (2 de 5) recebe notícia de ESPORTE"),
        Arguments.of(e3, n3, 11, 5, "ENTRETENIMENTO (4 de 10) recebe notícia de ENTRETENIMENTO"),
        Arguments.of(e4, n2, 4, 1, "ESPORTE (1 de 3) recebe notícia de TECNOLOGIA"),
        Arguments.of(e5, n5, 9, 4, "POLITICA (3 de 8) recebe notícia de POLITICA")
    );
  }
}
