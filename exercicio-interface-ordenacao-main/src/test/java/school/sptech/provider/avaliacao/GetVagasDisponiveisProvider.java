package school.sptech.provider.avaliacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AvaliacaoFisicaFactory;

public class GetVagasDisponiveisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(AvaliacaoFisicaFactory.build("A", "Dra. Lia", 80.0, 5, 0), 5, "Limite 5 e nenhuma agendada: deve retornar 5"),
              Arguments.of(AvaliacaoFisicaFactory.build("B", "Dra. Lia", 80.0, 3, 2), 1, "Limite 3 e 2 agendadas: deve retornar 1"),
              Arguments.of(AvaliacaoFisicaFactory.build("C", "Dra. Lia", 80.0, 3, 3), 0, "Limite 3 e 3 agendadas: deve retornar 0")
        );
    }
}
