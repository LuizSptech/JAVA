package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class RemoverItemProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // itens (inicial), item (parametro), sucessoEsperado, itens (esperado)
        return Stream.of(
                Arguments.of(List.of("Arroz", "Feijão", "Leite"), "Feijão", true, List.of("Arroz", "Leite")),
                Arguments.of(List.of("Arroz", "Feijão"), "Leite", false, List.of("Arroz", "Feijão"))
        );
    }
}
