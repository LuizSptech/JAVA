package school.sptech.provider.personal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class GetVagasDisponiveisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.personal("A", 200.0, "Ana", 3, 0), 3, "3 sessões e nenhuma agendada: deve retornar 3"),
              Arguments.of(PlanoFactory.personal("B", 200.0, "Ana", 3, 1), 2, "3 sessões e 1 agendada: deve retornar 2"),
              Arguments.of(PlanoFactory.personal("C", 200.0, "Ana", 2, 2), 0, "2 sessões e 2 agendadas: deve retornar 0")
        );
    }
}
