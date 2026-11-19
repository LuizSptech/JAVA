package school.sptech.provider.musculacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class CalcularMensalidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.musculacao("Livre", 100.0, true), 120.0,
                    "Acesso 24h: deve somar 20%, 100.00 + 20.00 = 120.00"),
              Arguments.of(PlanoFactory.musculacao("Básica", 100.0, false), 100.0,
                    "Sem acesso 24h: deve manter o preço base de 100.00"),
              Arguments.of(PlanoFactory.musculacao("Premium", 250.0, true), 300.0,
                    "Acesso 24h: deve somar 20%, 250.00 + 50.00 = 300.00"),
              Arguments.of(PlanoFactory.musculacao("Gratuita", 0.0, true), 0.0,
                    "Acesso 24h com preço base zero: deve retornar 0.00")
        );
    }
}
