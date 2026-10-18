package school.sptech.provider.turma;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class BuscarAlunoMaiorMediaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("maior média é o primeiro aluno", List.of(ana(), bruno()), "001"),
        Arguments.of("maior média no meio da lista", List.of(bruno(), diego(), carla(), elisa()), "003"),
        Arguments.of("turma com um aluno", List.of(diego()), "004"),
        Arguments.of("turma vazia deve retornar null", List.of(), null)
    );
  }
}
