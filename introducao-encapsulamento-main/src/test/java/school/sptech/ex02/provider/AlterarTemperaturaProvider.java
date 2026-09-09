package school.sptech.ex02.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class AlterarTemperaturaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // atualIni, maxIni, minIni, nova, atualEsp, maxEsp, minEsp
        return Stream.of(
              Arguments.of(25.0, 28.0, 20.0, 30.0, 30.0, 30.0, 20.0),   // nova quente -> novo max
              Arguments.of(25.0, 28.0, 20.0, 10.0, 10.0, 28.0, 10.0),   // nova fria -> novo min
              Arguments.of(25.0, 28.0, 20.0, 26.0, 26.0, 28.0, 20.0),   // entre min e max -> so atual
              Arguments.of(25.0, 28.0, 20.0, 28.0, 28.0, 28.0, 20.0),   // nova == max -> max nao muda
              Arguments.of(25.0, 28.0, 20.0, 20.0, 20.0, 28.0, 20.0),   // nova == min -> min nao muda
              Arguments.of(25.0, 28.0, 20.0, null, 25.0, 28.0, 20.0),   // nova null -> nada muda
              Arguments.of(5.0, 10.0, 0.0, -5.0, -5.0, 10.0, -5.0),     // nova negativa -> novo min
              Arguments.of(25.0, 28.0, 20.0, 100.0, 100.0, 100.0, 20.0),// nova muito quente -> novo max
              Arguments.of(25.0, 28.0, 20.0, 25.0, 25.0, 28.0, 20.0)    // nova == atual -> sem novo extremo
        );
    }
}
