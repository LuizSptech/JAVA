package school.sptech.ex1.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AumentarEstoqueProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeEmEstoque (inicial), qtd (parametro), quantidadeEmEstoque (esperado)
        return Stream.of(
                Arguments.of(10, 10, 20),
                Arguments.of(20, 24, 44),
                Arguments.of(20, -9, 20),
                Arguments.of(0, 0, 0),
                Arguments.of(100, 1, 101)
        );
    }
}
