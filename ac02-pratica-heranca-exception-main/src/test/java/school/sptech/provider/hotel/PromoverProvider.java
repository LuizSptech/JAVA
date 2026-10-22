package school.sptech.provider.hotel;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class PromoverProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("Ana com 15% de desconto", "Ana", "111", 15.0),
              argumentSet("Carla com 0% de desconto", "Carla", "333", 0.0),
              argumentSet("Diego com 50% de desconto", "Diego", "444", 50.0)
        );
    }
}
