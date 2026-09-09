package school.sptech.ex02.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConverterParaKelvinProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // temperaturaAtual (Celsius), esperado (Kelvin = C + 273.15)
        return Stream.of(
              Arguments.of(0.0, 273.15),
              Arguments.of(25.0, 298.15),
              Arguments.of(-273.15, 0.0),
              Arguments.of(100.0, 373.15),
              Arguments.of(-40.0, 233.15),
              Arguments.of(37.5, 310.65)
        );
    }
}
