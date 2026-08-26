package school.sptech.ex3.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularHoraExtraProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // salario, qtdHoraExtra, percentualExtra, valorEsperado
        return Stream.of(
                Arguments.of(10_000.00, 20, 15, 1045.454545465),
                Arguments.of(8_000.00, 30, 20, 1309.090909091),
                Arguments.of(2_800.00, 5, 10, 69.999999999)
        );
    }
}
