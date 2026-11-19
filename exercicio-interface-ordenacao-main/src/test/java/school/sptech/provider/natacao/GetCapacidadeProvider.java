package school.sptech.provider.natacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class GetCapacidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.natacao("A", 150.0, "TARDE", 2, 3, 0), 6, "2 raias x 3 alunos por raia: deve retornar 6"),
              Arguments.of(PlanoFactory.natacao("B", 150.0, "TARDE", 4, 5, 7), 20,
                    "4 raias x 5 alunos por raia, com inscritos: deve retornar 20, sem descontar os inscritos"),
              Arguments.of(PlanoFactory.natacao("C", 150.0, "TARDE", 1, 1, 1), 1, "1 raia x 1 aluno por raia: deve retornar 1")
        );
    }
}
