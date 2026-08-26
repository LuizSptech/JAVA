package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class SubstituirItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // itens (inicial), posicao, novoItem, sucessoEsperado, itens (esperado)
        return Stream.of(
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 1, "Macarrão", true,
                        List.of("Arroz", "Macarrão", "Leite")),
                Arguments.of(List.of("Arroz"), 0, "Feijão", true, List.of("Feijão")),
                Arguments.of(List.of("Arroz", "Feijão"), -1, "Leite", false, List.of("Arroz", "Feijão")),
                Arguments.of(List.of("Arroz", "Feijão"), 5, "Leite", false, List.of("Arroz", "Feijão")),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 0, "Feijão", false,
                        List.of("Arroz", "Feijão", "Leite"))
        );
    }
}
