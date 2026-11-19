package school.sptech.provider.natacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class CalcularMensalidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.natacao("A", 150.0, "MANHA", 2, 3, 0), 135.0,
                    "Turno MANHA: deve dar 10% de desconto, 150.00 - 15.00 = 135.00"),
              Arguments.of(PlanoFactory.natacao("B", 150.0, "TARDE", 2, 3, 0), 150.0,
                    "Turno TARDE: deve manter o preço base de 150.00"),
              Arguments.of(PlanoFactory.natacao("C", 150.0, "NOITE", 2, 3, 0), 172.5,
                    "Turno NOITE: deve somar 15%, 150.00 + 22.50 = 172.50"),
              Arguments.of(PlanoFactory.natacao("D", 200.0, "NOITE", 2, 3, 0), 230.0,
                    "Turno NOITE: deve somar 15%, 200.00 + 30.00 = 230.00"),
              Arguments.of(PlanoFactory.natacao("E", 80.0, "MANHA", 2, 3, 0), 72.0,
                    "Turno MANHA: deve dar 10% de desconto, 80.00 - 8.00 = 72.00")
        );
    }
}
