package school.sptech.provider.hospede;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularTotalEstadiaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // nome, documento, quarto, dias, consumo, diaria, esperado
              Arguments.of("Ana", "111", 101, 3, 450.0, 2500.0, 7995.0),
              Arguments.of("Bia", "222", 102, 1, 0.0, 1500.0, 1500.0),
              Arguments.of("Caio", "333", null, 7, 100.0, 1000.0, 7110.0),
              Arguments.of("Duda", "444", 104, 8, 0.0, 2000.0, 15700.0),
              Arguments.of("Enzo", "555", 105, 10, 50.0, 1000.0, 9605.0)
        );
    }
}
