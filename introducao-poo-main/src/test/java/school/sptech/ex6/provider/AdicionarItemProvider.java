package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class AdicionarItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // capacidadeMaxima, itens (inicial), item (parametro), itens (esperado)
        return Stream.of(
                Arguments.of(5, List.of(), "Arroz", List.of("Arroz")),
                Arguments.of(2, List.of("Arroz", "Feijão"), "Leite", List.of("Arroz", "Feijão")),
                Arguments.of(3, List.of("Arroz", "Feijão"), "Leite", List.of("Arroz", "Feijão", "Leite")),
                Arguments.of(5, List.of("Arroz", "Feijão"), "Arroz", List.of("Arroz", "Feijão"))
        );
    }
}
