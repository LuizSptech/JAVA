package school.sptech.ex3.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ReajustarSalarioProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // salario (inicial), percentual, salario (esperado)
        return Stream.of(
                Arguments.of(10_000.00, 5, 10_500.00),
                Arguments.of(8_000.00, 5, 8_400.00),
                Arguments.of(2_800.00, 58, 4_424.00),
                Arguments.of(5_000.00, 0, 5_000.00)
        );
    }
}
