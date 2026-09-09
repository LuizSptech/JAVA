package school.sptech.ex02.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConstrutorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // temperaturaInicial (os 3 atributos devem ficar iguais a esse valor)
        return Stream.of(
              Arguments.of(25.0),
              Arguments.of(-10.0),
              Arguments.of(0.0),
              Arguments.of(100.5),
              Arguments.of(-273.15)
        );
    }
}
