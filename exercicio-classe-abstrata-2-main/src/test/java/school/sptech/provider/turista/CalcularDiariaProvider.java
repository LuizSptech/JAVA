package school.sptech.provider.turista;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularDiariaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // guiaTuristico, altaTemporada, esperado
              Arguments.of(false, false, 1500.0),
              Arguments.of(true, false, 2000.0),
              Arguments.of(false, true, 1950.0),
              Arguments.of(true, true, 2450.0)
        );
    }
}
