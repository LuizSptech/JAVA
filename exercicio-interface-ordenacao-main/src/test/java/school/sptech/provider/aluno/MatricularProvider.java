package school.sptech.provider.aluno;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.factory.AlunoFactory;
import school.sptech.factory.PlanoFactory;

public class MatricularProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Object musculacao = PlanoFactory.musculacao("Musculação", 100.0, false);
        Object personal = PlanoFactory.personal("Personal", 200.0, "Ana", 2, 0);

        return Stream.of(
              Arguments.of(AlunoFactory.build("Ana", null), musculacao, musculacao, null,
                    "Aluno sem plano: deve passar a ter o plano informado"),
              Arguments.of(AlunoFactory.build("Bruno", musculacao), personal, personal, null,
                    "Aluno já matriculado: deve substituir o plano anterior pelo novo"),
              Arguments.of(AlunoFactory.build("Carla", musculacao), null, musculacao, DadoInvalidoException.class,
                    "Plano nulo: deve lançar DadoInvalidoException e manter o plano anterior")
        );
    }
}
