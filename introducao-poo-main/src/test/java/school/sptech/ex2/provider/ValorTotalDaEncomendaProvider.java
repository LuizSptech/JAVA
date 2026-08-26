package school.sptech.ex2.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class ValorTotalDaEncomendaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // tamanho, distancia, valorProduto, valorEsperado
        return Stream.of(
                Arguments.of("P", 330.0, 100.0, 108.0),
                Arguments.of("M", 35.0, 50.0, 54.5),
                Arguments.of("G", 100.0, 200.0, 215.0),
                Arguments.of("P", 50.0, 100.0, 104.0)
        );
    }
}
