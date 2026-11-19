package school.sptech.provider.avaliacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AvaliacaoFisicaFactory;

public class GetCapacidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(AvaliacaoFisicaFactory.build("A", "Dra. Lia", 80.0, 3, 0), 3, "Limite diário 3 e nenhuma agendada: deve retornar 3"),
              Arguments.of(AvaliacaoFisicaFactory.build("B", "Dra. Lia", 80.0, 8, 5), 8,
                    "Limite diário 8 e 5 agendadas: deve retornar 8, sem descontar as agendadas")
        );
    }
}
