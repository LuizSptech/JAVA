package school.sptech.ex6.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

public class CalcularVagasRestantesProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // capacidadeMaxima, itens, valorEsperado
        return Stream.of(
                Arguments.of(5, List.of("Arroz", "Feijão"), 3),
                Arguments.of(3, List.of(), 3),
                Arguments.of(2, List.of("Arroz", "Feijão"), 0)
        );
    }
}
