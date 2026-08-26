package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class RemoverItensDuplicadosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // itens (inicial), quantidadeRemovidaEsperada, itens (esperado)
        return Stream.of(
                Arguments.of(List.of("Arroz", "Feijão", "Arroz", "Leite", "Feijão", "Feijão"), 3,
                        List.of("Arroz", "Feijão", "Leite")),
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), 0, List.of("Arroz", "Feijão", "Leite")),
                Arguments.of(List.of("Arroz", "Arroz", "Arroz"), 2, List.of("Arroz")),
                Arguments.of(List.of(), 0, List.of())
        );
    }
}
