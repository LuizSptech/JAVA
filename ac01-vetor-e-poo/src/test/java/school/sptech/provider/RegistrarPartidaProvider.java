package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RegistrarPartidaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadePartidas (inicial), vitorias (inicial), vitoria,
        // quantidadePartidas (esperado), vitorias (esperado)
        return Stream.of(
                Arguments.of(0, 0, true, 1, 1),
                Arguments.of(0, 0, false, 1, 0),
                Arguments.of(5, 3, true, 6, 4),
                Arguments.of(5, 3, false, 6, 3),
                Arguments.of(10, 10, true, 11, 11),
                Arguments.of(1, 0, false, 2, 0)
        );
    }
}
