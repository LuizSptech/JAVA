package school.sptech.ex02.provider;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class AlunoCalcularNotaFinalProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
    return Stream.of(
          Arguments.of(10.0, 0.0, 7.0),
          Arguments.of(0.0, 10.0, 3.0),
          Arguments.of(8.0, 6.0, 7.4),
          Arguments.of(7.0, 7.0, 7.0),
          Arguments.of(0.0, 0.0, 0.0),
          Arguments.of(10.0, 10.0, 10.0)
    );
  }
}
