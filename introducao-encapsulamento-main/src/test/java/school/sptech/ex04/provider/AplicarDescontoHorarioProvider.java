package school.sptech.ex04.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * aplicarDescontoHorario: aplica 10% de desconto (valor * 0.9) se o filme for exibido antes
 * das 16h (hora < 16); caso contrario nada muda.
 */
public class AplicarDescontoHorarioProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // valorIni, hora, valorEsp
        return Stream.of(
              Arguments.of(20.0, 10, 18.0),
              Arguments.of(20.0, 15, 18.0),   // limite: 15h ainda e antes das 16h
              Arguments.of(20.0, 16, 20.0),   // 16h -> nao aplica
              Arguments.of(20.0, 20, 20.0),
              Arguments.of(20.0, 0, 18.0),
              Arguments.of(30.0, 9, 27.0),
              Arguments.of(20.0, 23, 20.0)
        );
    }
}
