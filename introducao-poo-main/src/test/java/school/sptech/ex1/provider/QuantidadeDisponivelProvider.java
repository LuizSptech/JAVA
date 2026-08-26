package school.sptech.ex1.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class QuantidadeDisponivelProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeEmEstoque, valorEsperado
        return Stream.of(
                Arguments.of(20, 20),
                Arguments.of(25, 25),
                Arguments.of(87, 87),
                Arguments.of(4, 4),
                Arguments.of(0, 0)
        );
    }
}
