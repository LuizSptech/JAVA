package school.sptech.provider.jornal;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("SP Noticias"),
        Arguments.of("Jornal da Cidade"),
        Arguments.of("Diário Global"),
        Arguments.of("Gazeta Online"),
        Arguments.of("Folha Digital")
    );
  }
}
