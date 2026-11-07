package school.sptech.provider.concessionaria;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CnpjInvalidoProvider implements ArgumentsProvider {

    String nula = null;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
              Arguments.of("12345678901abcde"),
              Arguments.of("12345678901abcd"),
              Arguments.of("123456789012345"),
              Arguments.of("12.345.678/0001-99"),
              Arguments.of(" 12345678901234"),
              Arguments.of("             "),
              Arguments.of("12345678901ab"),
              Arguments.of("12345678901a"),
              Arguments.of("1234567890"),
              Arguments.of(nula),
              Arguments.of("")
        );
    }
}
