package school.sptech.provider.leitor;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              Arguments.of("Ana", "ana@email.com", "TECNOLOGIA"),
              Arguments.of("Bruno", "bruno@email.com", "ESPORTE"),
              Arguments.of("Carla", "carla@email.com", "POLITICA"),
              Arguments.of("Daniel", "daniel@email.com", "ENTRETENIMENTO")
        );
    }
}
