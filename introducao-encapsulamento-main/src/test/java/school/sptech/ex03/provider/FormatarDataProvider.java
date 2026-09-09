package school.sptech.ex03.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class FormatarDataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // dia, mes, ano, textoEsperado (formato DD/MM/AAAA, com zeros a esquerda)
        return Stream.of(
              Arguments.of(5, 6, 2023, "05/06/2023"),
              Arguments.of(25, 12, 2000, "25/12/2000"),
              Arguments.of(1, 1, 1, "01/01/0001"),
              Arguments.of(31, 10, 999, "31/10/0999"),
              Arguments.of(9, 9, 2099, "09/09/2099"),
              Arguments.of(10, 11, 12, "10/11/0012")
        );
    }
}
