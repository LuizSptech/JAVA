package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class EncontrarMaiorSequenciaDeVitoriasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // resultados, valorEsperado
        return Stream.of(
                Arguments.of(new Boolean[]{}, 0),
                Arguments.of(new Boolean[]{true}, 1),
                Arguments.of(new Boolean[]{false}, 0),
                Arguments.of(new Boolean[]{true, true, true}, 3),
                Arguments.of(new Boolean[]{true, false, true, true}, 2),
                Arguments.of(new Boolean[]{true, true, false, true, true, true}, 3),
                Arguments.of(new Boolean[]{false, false, false}, 0),
                Arguments.of(new Boolean[]{true, true, false, false, true}, 2),
                Arguments.of(new Boolean[]{true, false, true, false, true}, 1)
        );
    }
}
