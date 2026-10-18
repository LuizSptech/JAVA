package school.sptech.provider.aluno;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularMediaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("notas 8.0 e 6.0", 8.0, 6.0, 7.0),
        Arguments.of("notas máximas", 10.0, 10.0, 10.0),
        Arguments.of("notas zeradas", 0.0, 0.0, 0.0),
        Arguments.of("notas com casas decimais", 5.5, 7.25, 6.375)
    );
  }
}
