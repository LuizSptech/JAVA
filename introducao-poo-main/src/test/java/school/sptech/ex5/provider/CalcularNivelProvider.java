package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularNivelProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // experiencia, nivelEsperado
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(99, 0),
                Arguments.of(100, 1),
                Arguments.of(250, 2),
                Arguments.of(999, 9)
        );
    }
}
