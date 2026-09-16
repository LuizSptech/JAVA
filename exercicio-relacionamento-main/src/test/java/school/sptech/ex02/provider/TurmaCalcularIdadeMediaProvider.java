package school.sptech.ex02.provider;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex02.Aluno;
import school.sptech.ex02.util.TurmaFixtures;

public class TurmaCalcularIdadeMediaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
    Aluno ana = TurmaFixtures.novoAluno("Ana Silva", 16, "M001", 8.5, 8.5);
    Aluno bruno = TurmaFixtures.novoAluno("Bruno Costa", 17, "M002", 6.0, 6.0);
    Aluno carlos = TurmaFixtures.novoAluno("Carlos Lima", 15, "M003", 4.0, 4.0);
    Aluno diana = TurmaFixtures.novoAluno("Diana Reis", 18, "M004", 10.0, 10.0);
    Aluno elias = TurmaFixtures.novoAluno("Elias Rocha", 16, "M005", 6.0, 6.0);
    Aluno unico = TurmaFixtures.novoAluno("Fabio Melo", 15, "M006", 7.0, 7.0);

    return Stream.of(
          Arguments.of(List.of(ana, bruno, carlos, diana, elias), 16.4),
          Arguments.of(List.of(unico), 15.0),
          Arguments.of(List.of(), 0.0)
    );
  }
}
