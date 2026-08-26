package school.sptech.ex2.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AplicarCupomDeDescontoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // valorProduto (inicial), percentual, valorProduto (esperado)
        return Stream.of(
                Arguments.of(100.0, 10, 90.0),
                Arguments.of(50.0, 15, 42.5),
                Arguments.of(64.90, 30, 45.43),
                Arguments.of(4500.0, 20, 3600.0),
                Arguments.of(100.0, 0, 100.0),
                Arguments.of(100.0, 100, 0.0)
        );
    }
}
