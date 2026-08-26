package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RecuperarVidaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // vida (inicial), recuperacaoVida, vida (esperada)
        return Stream.of(
                Arguments.of(50, 20, 70),
                Arguments.of(90, 30, 100),
                Arguments.of(50, -10, 50),
                Arguments.of(100, 0, 100),
                Arguments.of(0, 100, 100)
        );
    }
}
