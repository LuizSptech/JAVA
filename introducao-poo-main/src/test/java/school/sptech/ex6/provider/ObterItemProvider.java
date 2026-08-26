package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class ObterItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // itens, posicao, valorEsperado
        return Stream.of(
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 0, "Arroz"),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 1, "Feijão"),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 2, "Leite"),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), -1, null),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 3, null),
                Arguments.of(List.of(), 0, null)
        );
    }
}
