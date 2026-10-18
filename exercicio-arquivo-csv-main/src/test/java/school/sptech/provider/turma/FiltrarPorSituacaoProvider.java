package school.sptech.provider.turma;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class FiltrarPorSituacaoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    List<Object> todos = List.of(ana(), bruno(), carla(), diego(), elisa());

    return Stream.of(
        Arguments.of("filtrar APROVADO", todos, "APROVADO", List.of("001", "005")),
        Arguments.of("filtrar RECUPERACAO", todos, "RECUPERACAO", List.of("002")),
        Arguments.of("filtrar REPROVADO (por nota e por frequência)", todos, "REPROVADO", List.of("003", "004")),
        Arguments.of("nenhum aluno na situação deve retornar lista vazia", List.of(ana()), "REPROVADO", List.of()),
        Arguments.of("turma vazia deve retornar lista vazia", List.of(), "APROVADO", List.of())
    );
  }
}
