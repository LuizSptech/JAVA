package school.sptech.ex01.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class BloquearProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // bloqueadoIni, bloqueadoEsp
        return Stream.of(
              Arguments.of(false, true),   // nao bloqueado -> passa a bloqueado
              Arguments.of(true, true)     // ja bloqueado -> continua bloqueado
        );
    }
}
