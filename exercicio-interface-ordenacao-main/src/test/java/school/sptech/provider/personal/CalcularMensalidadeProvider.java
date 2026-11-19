package school.sptech.provider.personal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class CalcularMensalidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.personal("A", 200.0, "Ana", 1, 0), 250.0, "1 sessão por semana: deve somar 50.00 por sessão, 200.00 + 50.00 = 250.00"),
              Arguments.of(PlanoFactory.personal("B", 200.0, "Ana", 3, 0), 350.0, "3 sessões por semana: deve somar 50.00 por sessão, 200.00 + 150.00 = 350.00"),
              Arguments.of(PlanoFactory.personal("C", 0.0, "Ana", 2, 0), 100.0, "Preço base zero e 2 sessões: deve retornar 0.00 + 100.00 = 100.00"),
              Arguments.of(PlanoFactory.personal("D", 200.0, "Ana", 3, 3), 350.0,
                    "Com sessões já agendadas: a mensalidade não deve mudar, 200.00 + 150.00 = 350.00")
        );
    }
}
