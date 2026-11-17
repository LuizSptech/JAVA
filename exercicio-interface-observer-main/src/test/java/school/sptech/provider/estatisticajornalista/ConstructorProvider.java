package school.sptech.provider.estatisticajornalista;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConstructorProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("João Silva"),
        Arguments.of("Maria Oliveira"),
        Arguments.of("Carlos Pereira"),
        Arguments.of("Ana Souza"),
        Arguments.of("Pedro Santos"),
        Arguments.of("Mariana Lima"),
        Arguments.of("Lucas Fernandes"),
        Arguments.of("Beatriz Almeida"),
        Arguments.of("Rafael Costa"),
        Arguments.of("Juliana Ribeiro")
    );
  }
}
