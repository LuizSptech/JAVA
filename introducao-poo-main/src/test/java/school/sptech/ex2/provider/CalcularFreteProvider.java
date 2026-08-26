package school.sptech.ex2.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularFreteProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // tamanho, distancia, valorProduto, valorEsperado
        return Stream.of(
                // Tamanho P
                Arguments.of("P", 30.0, 200.0, 5.0),
                Arguments.of("P", 100.0, 200.0, 7.0),
                Arguments.of("P", 300.0, 200.0, 9.0),
                // Tamanho M
                Arguments.of("M", 30.0, 200.0, 9.0),
                Arguments.of("M", 100.0, 200.0, 11.0),
                Arguments.of("M", 300.0, 200.0, 13.0),
                // Tamanho G
                Arguments.of("G", 30.0, 200.0, 13.0),
                Arguments.of("G", 100.0, 200.0, 15.0),
                Arguments.of("G", 300.0, 200.0, 17.0),
                // Limites de distância
                Arguments.of("P", 50.0, 100.0, 4.0),
                Arguments.of("P", 200.0, 100.0, 6.0),
                Arguments.of("P", 200.01, 100.0, 8.0),
                // Cenários originais do enunciado
                Arguments.of("M", 35.0, 50.0, 4.5),
                Arguments.of("G", 90.0, 100.0, 10.0),
                Arguments.of("P", 330.0, 100.0, 8.0)
        );
    }
}
