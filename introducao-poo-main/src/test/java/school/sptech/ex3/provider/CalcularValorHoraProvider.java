package school.sptech.ex3.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularValorHoraProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // salario, valorEsperado
        return Stream.of(
                Arguments.of(10_000.00, 45.454545455),
                Arguments.of(8_000.00, 36.363636364),
                Arguments.of(2_800.00, 12.727272727)
        );
    }
}
