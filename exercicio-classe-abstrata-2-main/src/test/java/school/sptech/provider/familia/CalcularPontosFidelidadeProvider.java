package school.sptech.provider.familia;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularPontosFidelidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, qtdCriancas, esperado
              Arguments.of(5, 100.0, 3, 49),
              Arguments.of(6, 0.0, 4, 62),
              Arguments.of(2, 0.0, null, 12),
              Arguments.of(1, 0.0, -2, 6)
        );
    }
}
