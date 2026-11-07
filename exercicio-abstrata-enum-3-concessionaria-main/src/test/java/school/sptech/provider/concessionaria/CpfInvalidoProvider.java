package school.sptech.provider.concessionaria;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CpfInvalidoProvider implements ArgumentsProvider {

    String nula = null;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
              Arguments.of("12345678901ab"),
              Arguments.of("12345678901a"),
              Arguments.of("123456789012"),
              Arguments.of("123.456.789-01"),
              Arguments.of(" 12345678901"),
              Arguments.of("          "),
              Arguments.of("1234567890"),
              Arguments.of("123456789"),
              Arguments.of("123456789"),
              Arguments.of(nula),
              Arguments.of("")
        );
    }
}
