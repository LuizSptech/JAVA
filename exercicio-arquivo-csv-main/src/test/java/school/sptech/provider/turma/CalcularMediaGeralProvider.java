package school.sptech.provider.turma;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularMediaGeralProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("turma com dois alunos", List.of(ana(), elisa()), 7.75),
        Arguments.of("turma com cinco alunos", List.of(ana(), bruno(), carla(), diego(), elisa()), 6.5),
        Arguments.of("turma com um aluno", List.of(diego()), 2.5),
        Arguments.of("turma vazia deve retornar 0.0", List.of(), 0.0)
    );
  }
}
