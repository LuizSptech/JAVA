package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.AcademiaFixture;

public class CadastrarAlunoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        Object eva = AlunoFactory.build("Eva", null);
        AcademiaFixture f2 = new AcademiaFixture();
        AcademiaFixture f3 = new AcademiaFixture();

        return Stream.of(
              Arguments.of(f1.academia, eva, null, List.of(f1.carla, f1.bruno, f1.ana, f1.diego, eva),
                    "Aluno com nome novo: deve ser adicionado ao final da lista"),
              Arguments.of(f2.academia, null, DadoInvalidoException.class, f2.alunos(),
                    "Aluno nulo: deve lançar DadoInvalidoException e a lista não deve mudar"),
              Arguments.of(f3.academia, AlunoFactory.build("CARLA", null), DadoInvalidoException.class, f3.alunos(),
                    "Nome já cadastrado, em outra caixa (\"CARLA\"): deve lançar DadoInvalidoException e a lista não deve mudar")
        );
    }
}
