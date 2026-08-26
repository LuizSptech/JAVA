package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class RemoverItemNaPosicaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // itens (inicial), posicao, itemRemovidoEsperado, itens (esperado)
        return Stream.of(
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 1, "Feijão", List.of("Arroz", "Leite")),
                Arguments.of(List.of("Arroz"), 0, "Arroz", List.of()),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 2, "Leite", List.of("Arroz", "Feijão")),
                Arguments.of(List.of("Arroz", "Feijão"), 5, null, List.of("Arroz", "Feijão")),
                Arguments.of(List.of("Arroz", "Feijão"), -1, null, List.of("Arroz", "Feijão"))
        );
    }
}
