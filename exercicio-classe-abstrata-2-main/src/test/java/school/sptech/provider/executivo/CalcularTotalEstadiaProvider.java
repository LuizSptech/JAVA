package school.sptech.provider.executivo;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularTotalEstadiaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, vip, internet, empresaConveniada, esperado
              Arguments.of(2, 100.0, true, true, false, 6610.0),
              Arguments.of(9, 0.0, false, false, true, 15660.0),
              Arguments.of(7, 1000.0, true, false, false, 22100.0)
        );
    }
}
