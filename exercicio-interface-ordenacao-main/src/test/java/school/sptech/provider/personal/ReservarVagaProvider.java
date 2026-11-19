package school.sptech.provider.personal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.VagaIndisponivelException;
import school.sptech.factory.PlanoFactory;

public class ReservarVagaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.personal("A", 200.0, "Ana", 3, 1), 2, null,
                    "Com sessão livre (1 de 3): deve aumentar sessoesAgendadas de 1 para 2"),
              Arguments.of(PlanoFactory.personal("B", 200.0, "Ana", 2, 2), 2, VagaIndisponivelException.class,
                    "Sem sessões livres (2 de 2): deve lançar VagaIndisponivelException e manter sessoesAgendadas em 2")
        );
    }
}
