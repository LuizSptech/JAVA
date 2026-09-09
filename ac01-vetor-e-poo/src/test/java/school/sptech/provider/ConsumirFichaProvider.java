package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ConsumirFichaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeFichas (inicial), quantidadeFichas (parametro), sucessoEsperado, quantidadeFichas (esperado)
        return Stream.of(
                Arguments.of(10, 5, true, 5),
                Arguments.of(10, 10, true, 0),
                Arguments.of(10, 11, false, 10),
                Arguments.of(10, -1, false, 10),
                Arguments.of(0, 0, true, 0),
                Arguments.of(5, 0, true, 5),
                Arguments.of(3, 3, true, 0),
                Arguments.of(0, 1, false, 0)
        );
    }
}
