package school.sptech.ex1.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class TotalVendidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // valor, quantidadeVendida, valorEsperado
        return Stream.of(
                Arguments.of(45.0, 5, 225.0),
                Arguments.of(65.0, 57, 3705.0),
                Arguments.of(22.99, 15, 344.85),
                Arguments.of(49.90, 26, 1297.4),
                Arguments.of(10.0, 0, 0.0)
        );
    }
}
