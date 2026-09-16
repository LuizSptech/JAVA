package school.sptech.ex02.provider;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex02.Aluno;
import school.sptech.ex02.util.TurmaFixtures;

public class TurmaBuscarPorParteDoNomeProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
    Aluno joao = TurmaFixtures.novoAluno("João da Silva", 16, "M001", 8.5, 8.5);
    Aluno maria = TurmaFixtures.novoAluno("Maria da Costa", 17, "M002", 6.0, 6.0);
    Aluno bruno = TurmaFixtures.novoAluno("Bruno Souza", 15, "M003", 7.0, 7.0);
    List<Aluno> alunos = List.of(joao, maria, bruno);

    return Stream.of(
          Arguments.of(alunos, "João da Silva", List.of(joao)),
          Arguments.of(alunos, "joão da silva", List.of(joao)),
          Arguments.of(alunos, "da", List.of(joao, maria)),
          Arguments.of(alunos, "DA", List.of(joao, maria)),
          Arguments.of(alunos, "Souza", List.of(bruno)),
          Arguments.of(alunos, "Jo", List.of(joao)),
          Arguments.of(alunos, "ilv", List.of(joao)),
          Arguments.of(alunos, "Zeca", List.of()),
          Arguments.of(List.of(), "João", List.of())
    );
  }
}
