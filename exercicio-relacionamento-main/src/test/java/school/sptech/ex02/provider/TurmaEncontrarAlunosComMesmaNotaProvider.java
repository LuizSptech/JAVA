package school.sptech.ex02.provider;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex02.Aluno;
import school.sptech.ex02.util.TurmaFixtures;

public class TurmaEncontrarAlunosComMesmaNotaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
    Aluno ana = TurmaFixtures.novoAluno("Ana Silva", 16, "M001", 8.5, 8.5);
    Aluno bruno = TurmaFixtures.novoAluno("Bruno Costa", 17, "M002", 6.0, 6.0);
    Aluno carlos = TurmaFixtures.novoAluno("Carlos Lima", 15, "M003", 4.0, 4.0);
    Aluno diana = TurmaFixtures.novoAluno("Diana Reis", 18, "M004", 6.0, 6.0);
    Aluno elias = TurmaFixtures.novoAluno("Elias Rocha", 16, "M005", 7.0, 7.0);

    Aluno fabio = TurmaFixtures.novoAluno("Fabio Melo", 15, "M006", 5.0, 5.0);
    Aluno gina = TurmaFixtures.novoAluno("Gina Alves", 16, "M007", 5.0, 5.0);
    Aluno hugo = TurmaFixtures.novoAluno("Hugo Dias", 17, "M008", 5.0, 5.0);

    Aluno ines = TurmaFixtures.novoAluno("Ines Souza", 15, "M009", 9.0, 9.0);
    Aluno julia = TurmaFixtures.novoAluno("Julia Prado", 16, "M010", 9.0, 9.0);

    return Stream.of(
          Arguments.of(List.of(ana, bruno, carlos, diana, elias), List.of(bruno, diana)),
          Arguments.of(List.of(fabio, gina, hugo), List.of(fabio, gina, hugo)),
          Arguments.of(List.of(bruno, diana, ines, julia, carlos), List.of(bruno, diana, ines,
                julia)),
          Arguments.of(List.of(ana, bruno, carlos), List.of()),
          Arguments.of(List.of(), List.of())
    );
  }
}
