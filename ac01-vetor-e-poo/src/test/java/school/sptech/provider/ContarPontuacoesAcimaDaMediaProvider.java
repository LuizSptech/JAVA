package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ContarPontuacoesAcimaDaMediaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // pontuacoes, valorEsperado
        return Stream.of(
                Arguments.of(new Integer[]{}, 0), // vazio
                Arguments.of(new Integer[]{5}, 0), // media 5, nenhuma acima
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, 2), // media 3, acima: 4 e 5
                Arguments.of(new Integer[]{-5, 20, -10}, 1), // media 1.67, acima: 20
                Arguments.of(new Integer[]{10, 20, 30}, 1), // media 20, acima: 30
                Arguments.of(new Integer[]{5, 5, 5}, 0), // media 5, nenhuma acima
                Arguments.of(new Integer[]{100, -100}, 1), // media 0, acima: 100
                Arguments.of(new Integer[]{1, 1, 1, 10}, 1), // media 3.25, acima: 10
                Arguments.of(new Integer[]{7, 8, 9, 10, 11, 12}, 3), // media 9.5, acima: 10, 11 e 12
                Arguments.of(new Integer[]{0, 0, 0, 0}, 0) // media 0, nenhuma acima
        );
    }
}
