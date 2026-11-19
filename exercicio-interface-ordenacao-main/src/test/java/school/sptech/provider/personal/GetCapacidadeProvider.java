package school.sptech.provider.personal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class GetCapacidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.personal("A", 200.0, "Ana", 3, 0), 3, "3 sessões por semana e nenhuma agendada: deve retornar 3"),
              Arguments.of(PlanoFactory.personal("B", 200.0, "Ana", 5, 4), 5,
                    "5 sessões por semana e 4 agendadas: deve retornar 5, sem descontar as agendadas")
        );
    }
}
