package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ComprarFichasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // saldo (inicial), quantidadeFichas (inicial), quantidadeFichas (parametro/compra), sucessoEsperado,
        // saldo (esperado), quantidadeFichas (esperado)
        return Stream.of(
                Arguments.of(25.0, 0, 5, true, 0.0, 5),
                Arguments.of(100.0, 0, 10, true, 60.0, 10),
                Arguments.of(100.0, 0, 11, true, 67.0, 11),
                Arguments.of(10.0, 0, 5, false, 10.0, 0),
                Arguments.of(100.0, 0, -1, false, 100.0, 0),
                Arguments.of(100.0, 2, 6, true, 76.0, 8),
                Arguments.of(30.0, 0, 6, true, 6.0, 6),
                Arguments.of(0.0, 0, 0, true, 0.0, 0),
                Arguments.of(1000.0, 0, 20, true, 940.0, 20)
        );
    }
}
