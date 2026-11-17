package school.sptech.provider.estatisticatema;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("POLITICA"),
        Arguments.of("TECNOLOGIA"),
        Arguments.of("ENTRETENIMENTO"),
        Arguments.of("ESPORTE")
    );
  }
}
