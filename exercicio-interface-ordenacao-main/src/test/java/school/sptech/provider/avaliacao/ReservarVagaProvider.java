package school.sptech.provider.avaliacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.VagaIndisponivelException;
import school.sptech.factory.AvaliacaoFisicaFactory;

public class ReservarVagaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(AvaliacaoFisicaFactory.build("A", "Dra. Lia", 80.0, 3, 2), 3, null,
                    "Com vaga (2 de 3): deve aumentar agendadas de 2 para 3"),
              Arguments.of(AvaliacaoFisicaFactory.build("B", "Dra. Lia", 80.0, 3, 3), 3, VagaIndisponivelException.class,
                    "Limite diário atingido (3 de 3): deve lançar VagaIndisponivelException e manter agendadas em 3")
        );
    }
}
