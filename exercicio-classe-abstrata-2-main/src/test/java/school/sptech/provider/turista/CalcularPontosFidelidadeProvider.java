package school.sptech.provider.turista;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularPontosFidelidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, guiaTuristico, altaTemporada, esperado
              Arguments.of(3, 300.0, true, false, 63),
              Arguments.of(1, 50.0, false, false, 15),
              Arguments.of(8, 100.0, true, true, 193)
        );
    }
}
