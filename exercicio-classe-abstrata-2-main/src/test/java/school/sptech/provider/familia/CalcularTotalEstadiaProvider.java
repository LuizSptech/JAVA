package school.sptech.provider.familia;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularTotalEstadiaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, qtdCriancas, esperado
              Arguments.of(5, 100.0, 3, 6860.0),
              Arguments.of(6, 0.0, 4, 8550.0),
              Arguments.of(10, 200.0, 0, 11096.0)
        );
    }
}
