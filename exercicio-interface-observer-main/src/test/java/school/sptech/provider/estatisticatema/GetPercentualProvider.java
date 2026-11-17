package school.sptech.provider.estatisticatema;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EstatisticaTemaFactory;

import java.util.stream.Stream;

public class GetPercentualProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

    Object e1 = EstatisticaTemaFactory.build("POLITICA", 0, 0);
    Object e2 = EstatisticaTemaFactory.build("TECNOLOGIA", 2, 5);
    Object e3 = EstatisticaTemaFactory.build("ENTRETENIMENTO", 4, 10);
    Object e4 = EstatisticaTemaFactory.build("ESPORTE", 1, 3);
    Object e5 = EstatisticaTemaFactory.build("POLITICA", 3, 8);
    Object e6 = EstatisticaTemaFactory.build("TECNOLOGIA", 0, 4);
    Object e7 = EstatisticaTemaFactory.build("ENTRETENIMENTO", 6, 6);
    Object e8 = EstatisticaTemaFactory.build("ESPORTE", 1, 7);
    Object e9 = EstatisticaTemaFactory.build("POLITICA", 2, 2);
    Object e10 = EstatisticaTemaFactory.build("TECNOLOGIA", 5, 9);

    return Stream.of(
        Arguments.of(e1, 0.0, "Nenhuma publicação recebida (evita divisão por zero)"),
        Arguments.of(e2, 40.0, "2 de 5 publicações do tema"),
        Arguments.of(e3, 40.0, "4 de 10 publicações do tema"),
        Arguments.of(e4, 33.33, "1 de 3 publicações do tema"),
        Arguments.of(e5, 37.5, "3 de 8 publicações do tema"),
        Arguments.of(e6, 0.0, "0 de 4 publicações do tema"),
        Arguments.of(e7, 100.0, "6 de 6 publicações do tema"),
        Arguments.of(e8, 14.29, "1 de 7 publicações do tema"),
        Arguments.of(e9, 100.0, "2 de 2 publicações do tema"),
        Arguments.of(e10, 55.56, "5 de 9 publicações do tema")
    );
  }
}
