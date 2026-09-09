package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ValidarJogadorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // nome, saldo, valorEsperado
        return Stream.of(
                Arguments.of("Ana", 10.0, true),
                Arguments.of("Ana", 0.0, true),
                Arguments.of("Bruno", 250.5, true),
                Arguments.of("Ana", -1.0, false),
                Arguments.of("Ana", -100.0, false),
                Arguments.of(null, 10.0, false),
                Arguments.of("Bruno", null, false),
                Arguments.of(null, null, false),
                Arguments.of("José", -1.0, false),
                Arguments.of(null, -1.0, false)
        );
    }
}
