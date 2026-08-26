package school.sptech.ex4.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class EncontrarNotaMaisProximaDaMediaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // notas, valorEsperado
        return Stream.of(
                Arguments.of(new Double[]{5.0, 7.0, 9.0}, 7.0), // valor exatamente na média
                Arguments.of(new Double[]{2.0, 4.0, 6.0, 8.0}, 4.0), // empate: primeira ocorrência
                Arguments.of(new Double[]{10.0}, 10.0), // um único elemento
                Arguments.of(new Double[]{1.0, 2.0, 10.0}, 2.0) // média não inteira
        );
    }
}
