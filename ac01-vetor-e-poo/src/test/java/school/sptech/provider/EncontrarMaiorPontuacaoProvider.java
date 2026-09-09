package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class EncontrarMaiorPontuacaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // pontuacoes, valorEsperado
        return Stream.of(
                Arguments.of(new Integer[]{10}, 10),
                Arguments.of(new Integer[]{5, 3, 9, 1}, 9),
                Arguments.of(new Integer[]{-5, -1, -10}, -1),
                Arguments.of(new Integer[]{7, 7, 7}, 7),
                Arguments.of(new Integer[]{100, 50, 99, 100}, 100),
                Arguments.of(new Integer[]{0}, 0),
                Arguments.of(new Integer[]{3, 10, 10, 2}, 10),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 5),
                Arguments.of(new Integer[]{}, null)
        );
    }
}
