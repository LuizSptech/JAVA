package school.sptech.provider.roupa;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPrecoFinalProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Roupa A", 200.0, 1.0, "Vermelho", 42.0, "algodão", 202.0),
        Arguments.of("Roupa B", 300.0, 1.2, "Preto", 40.0, "couro", 257.04),
        Arguments.of("Roupa C", 150.0, 0.8, "Azul", 38.0, "jeans", 151.6),
        Arguments.of("Roupa D", 500.0, 1.5, "Marrom", 44.0, "couro", 427.55),
        Arguments.of("Roupa E", 100.0, 0.5, "Branco", 36.0, "seda", 101.0),
        Arguments.of("Roupa F", 300.0, 1.2, "Preto", 40.0, "Couro", 257.04),
        Arguments.of("Roupa G", 300.0, 1.2, "Preto", 40.0, "COURO", 257.04),
        Arguments.of("Roupa H", 200.0, 0.0, "Azul", 38.0, "couro", 170.0)
    );
  }
}
