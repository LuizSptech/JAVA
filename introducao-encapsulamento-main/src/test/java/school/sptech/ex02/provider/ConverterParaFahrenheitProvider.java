package school.sptech.ex02.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConverterParaFahrenheitProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // temperaturaAtual (Celsius), esperado (Fahrenheit = C * 1.8 + 32)
        return Stream.of(
              Arguments.of(0.0, 32.0),
              Arguments.of(100.0, 212.0),
              Arguments.of(-40.0, -40.0),
              Arguments.of(37.0, 98.6),
              Arguments.of(25.0, 77.0),
              Arguments.of(-273.15, -459.67)
        );
    }
}
