package school.sptech.provider.turma;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AlunoFactory;

public class AdicionarAlunoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("turma vazia recebe um aluno", List.of(), ana(), List.of("001")),
        Arguments.of("turma com um aluno recebe outro", List.of(ana()), bruno(), List.of("001", "002")),
        Arguments.of("aluno nulo não deve ser adicionado", List.of(ana()), null, List.of("001")),
        Arguments.of("aluno com RA nulo não deve ser adicionado", List.of(ana()), AlunoFactory.build(null, "Sem RA", 5.0, 5.0, 80), List.of("001")),
        Arguments.of("aluno com RA em branco não deve ser adicionado", List.of(ana()), AlunoFactory.build("   ", "RA vazio", 5.0, 5.0, 80), List.of("001")),
        Arguments.of("aluno com RA já cadastrado não deve ser adicionado", List.of(ana(), bruno()), AlunoFactory.build("001", "Ana Duplicada", 1.0, 1.0, 100), List.of("001", "002"))
    );
  }
}
