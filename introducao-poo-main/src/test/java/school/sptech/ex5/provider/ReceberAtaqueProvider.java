package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ReceberAtaqueProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // vida (inicial), danoRecebido, vida (esperada)
        return Stream.of(
                Arguments.of(80, 20, 60),
                Arguments.of(50, 60, 0),
                Arguments.of(30, -10, 30),
                Arguments.of(10, 10, 0),
                Arguments.of(100, 0, 100)
        );
    }
}
