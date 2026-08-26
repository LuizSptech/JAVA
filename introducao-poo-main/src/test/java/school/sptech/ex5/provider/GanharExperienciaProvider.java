package school.sptech.ex5.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class GanharExperienciaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // experiencia (inicial), experienciaRecebida, experiencia (esperada)
        return Stream.of(
                Arguments.of(0, 50, 50),
                Arguments.of(150, 200, 350),
                Arguments.of(100, -20, 100),
                Arguments.of(0, 0, 0)
        );
    }
}
