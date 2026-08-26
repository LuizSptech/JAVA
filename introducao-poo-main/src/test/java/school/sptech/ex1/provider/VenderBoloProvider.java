package school.sptech.ex1.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class VenderBoloProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeVendida (inicial), quantidadeEmEstoque (inicial), qtd (parametro),
        // quantidadeVendida (esperado), quantidadeEmEstoque (esperado)
        return Stream.of(
                Arguments.of(10, 60, 10, 20, 50),
                Arguments.of(15, 40, 25, 40, 15),
                Arguments.of(0, 40, 60, 0, 40),
                Arguments.of(90, 100, 11, 101, 89),
                Arguments.of(30, 100, -1, 30, 100),
                Arguments.of(0, 200, -99, 0, 200),
                Arguments.of(5, 10, 10, 15, 0),
                Arguments.of(5, 10, 0, 5, 10)
        );
    }
}
