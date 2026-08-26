package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularPoderDeCombateProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // ataque, vida, experiencia, poderEsperado
        return Stream.of(
                Arguments.of(50, 80, 250, 150), // nivel 2
                Arguments.of(40, 60, 150, 110), // nivel 1
                Arguments.of(30, 50, 50, 80), // nivel 0
                Arguments.of(0, 0, 0, 0)
        );
    }
}
