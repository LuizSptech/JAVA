package school.sptech.provider.familia;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularDiariaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // qtdCriancas, esperado
              Arguments.of(0, 1200.0),
              Arguments.of(2, 1200.0),
              Arguments.of(3, 1350.0),
              Arguments.of(4, 1500.0),
              Arguments.of(6, 1500.0),
              Arguments.of(null, 1200.0),
              Arguments.of(-1, 1200.0)
        );
    }
}
