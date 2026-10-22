package school.sptech.provider.hotel;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class DadosReservaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("quarto 101, 3 diarias de 200.0", 101, 3, 200.0),
              argumentSet("quarto 205, 12 diarias de 90.5", 205, 12, 90.5),
              argumentSet("quarto 310, 1 diaria de 1000.0", 310, 1, 1000.0)
        );
    }
}
