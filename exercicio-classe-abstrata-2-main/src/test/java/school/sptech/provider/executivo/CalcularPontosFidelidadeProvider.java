package school.sptech.provider.executivo;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularPontosFidelidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              // dias, consumo, vip, internet, empresaConveniada, esperado
              Arguments.of(2, 100.0, true, true, false, 264),
              Arguments.of(9, 0.0, false, false, true, 312),
              Arguments.of(1, 0.0, false, false, false, 40),
              Arguments.of(1, 50.0, true, false, false, 120)
        );
    }
}
