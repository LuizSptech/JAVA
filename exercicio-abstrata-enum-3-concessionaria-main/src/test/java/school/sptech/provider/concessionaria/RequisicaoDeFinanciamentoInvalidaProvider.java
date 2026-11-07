package school.sptech.provider.concessionaria;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class RequisicaoDeFinanciamentoInvalidaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
              Arguments.of(-10),
              Arguments.of(-1),
              Arguments.of(0),
              Arguments.of(601)
        );
    }
}
