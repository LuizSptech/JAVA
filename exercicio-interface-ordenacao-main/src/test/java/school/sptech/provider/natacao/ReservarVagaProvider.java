package school.sptech.provider.natacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.VagaIndisponivelException;
import school.sptech.factory.PlanoFactory;

public class ReservarVagaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Class<VagaIndisponivelException> lotada = VagaIndisponivelException.class;

        return Stream.of(
              Arguments.of(PlanoFactory.natacao("A", 150.0, "TARDE", 2, 3, 0), 1, null,
                    "Turma vazia (0 de 6): deve aumentar inscritos de 0 para 1"),
              Arguments.of(PlanoFactory.natacao("B", 150.0, "TARDE", 2, 3, 5), 6, null,
                    "Última vaga (5 de 6): deve aumentar inscritos de 5 para 6"),
              Arguments.of(PlanoFactory.natacao("C", 150.0, "TARDE", 2, 3, 6), 6, lotada,
                    "Turma lotada (6 de 6): deve lançar VagaIndisponivelException e manter inscritos em 6")
        );
    }
}
