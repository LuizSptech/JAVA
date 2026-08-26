package school.sptech.ex4.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ValidarQuantidadeNotasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeAlunosMatriculados, notas, valorEsperado
        return Stream.of(
                Arguments.of(3, new Double[]{7.0, 8.0, 9.0}, true),
                Arguments.of(4, new Double[]{7.0, 8.0, 9.0}, false),
                Arguments.of(1, new Double[]{5.0}, true),
                Arguments.of(2, new Double[]{6.0, 7.0, 8.0, 9.0, 10.0}, false)
        );
    }
}
