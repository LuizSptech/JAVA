package school.sptech.provider.executivo;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularDiariaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // vip, internet, empresaConveniada, esperado
              Arguments.of(false, false, false, 2000.0),
              Arguments.of(true, false, false, 3000.0),
              Arguments.of(false, true, false, 2500.0),
              Arguments.of(true, true, false, 3250.0),
              Arguments.of(false, false, true, 1800.0),
              Arguments.of(false, true, true, 2250.0),
              Arguments.of(true, true, true, 2925.0)
        );
    }
}
