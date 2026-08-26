package school.sptech.ex3.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularBonificacaoAnualProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // salario, valorEsperado
        return Stream.of(
                Arguments.of(2_000.00, 300.00), // faixa inicial (15%)
                Arguments.of(2_500.00, 375.00), // limite da faixa inicial (15%)
                Arguments.of(2_500.01, 250.001), // logo acima do limite (10%)
                Arguments.of(4_000.00, 400.00), // faixa intermediária (10%)
                Arguments.of(6_000.00, 600.00), // limite da faixa intermediária (10%)
                Arguments.of(6_000.01, 300.0005), // logo acima do limite (5%)
                Arguments.of(10_000.00, 500.00) // faixa sênior (5%)
        );
    }
}
