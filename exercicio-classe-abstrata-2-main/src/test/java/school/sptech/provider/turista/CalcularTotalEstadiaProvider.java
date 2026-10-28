package school.sptech.provider.turista;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularTotalEstadiaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, guiaTuristico, altaTemporada, esperado
              Arguments.of(3, 300.0, true, false, 6330.0),
              Arguments.of(10, 0.0, false, false, 14325.0),
              Arguments.of(8, 100.0, true, true, 19342.5)
        );
    }
}
