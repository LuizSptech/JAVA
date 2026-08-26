package school.sptech.ex4.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ContarAprovadosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // notas, valorEsperado
        return Stream.of(
                Arguments.of(new Double[]{5.0, 6.0, 7.0, 8.0}, 3),
                Arguments.of(new Double[]{10.0, 10.0, 10.0}, 3),
                Arguments.of(new Double[]{3.0, 4.0, 5.9}, 0),
                Arguments.of(new Double[]{6.0, 5.9, 9.9, 2.0}, 2)
        );
    }
}
