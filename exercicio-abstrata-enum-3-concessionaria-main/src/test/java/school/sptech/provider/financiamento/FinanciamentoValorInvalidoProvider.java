package school.sptech.provider.financiamento;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class FinanciamentoValorInvalidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
              Arguments.of(10, 3, null),
              Arguments.of(10, 3, -1000.0),
              Arguments.of(10, 3, 0.0),
              Arguments.of(10, 3, Double.MAX_VALUE),
              Arguments.of(10, 10, 1000.0),
              Arguments.of(10, 10, 0.0),
              Arguments.of(10, 10, null)
        );
    }
}
