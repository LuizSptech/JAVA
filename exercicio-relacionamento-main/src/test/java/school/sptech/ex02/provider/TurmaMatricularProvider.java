package school.sptech.ex02.provider;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex02.Aluno;
import school.sptech.ex02.util.TurmaFixtures;

public class TurmaMatricularProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
        throws Exception {
    Aluno valido1 = TurmaFixtures.novoAluno("Ana Silva", 16, "M001", 8.5, 8.5);
    Aluno valido2 = TurmaFixtures.novoAluno("Bruno Costa", 17, "M002", 6.0, 6.0);
    Aluno semNome = TurmaFixtures.novoAluno(null, 16, "M003", 7.0, 7.0);
    Aluno nomeBranco = TurmaFixtures.novoAluno("   ", 16, "M004", 7.0, 7.0);
    Aluno semIdade = TurmaFixtures.novoAluno("Carlos Lima", null, "M005", 7.0, 7.0);
    Aluno idadeNegativa = TurmaFixtures.novoAluno("Diana Reis", -1, "M006", 7.0, 7.0);
    Aluno semNotaProva = TurmaFixtures.novoAluno("Elias Rocha", 15, "M007", null, 7.0);
    Aluno semNotaAtividade = TurmaFixtures.novoAluno("Fabio Melo", 15, "M008", 7.0, null);
    Aluno notaProvaNegativa = TurmaFixtures.novoAluno("Gina Alves", 15, "M009", -0.5, 7.0);
    Aluno notaAtividadeNegativa = TurmaFixtures.novoAluno("Hugo Dias", 15, "M010", 7.0, -0.5);
    Aluno notaProvaAcimaDoLimite = TurmaFixtures.novoAluno("Ines Souza", 15, "M011", 10.1, 7.0);
    Aluno notaAtividadeAcimaDoLimite = TurmaFixtures.novoAluno("Julia Prado", 15, "M012", 7.0,
          10.1);
    Aluno notaLimiteSuperior = TurmaFixtures.novoAluno("Kevin Nunes", 15, "M013", 10.0, 10.0);
    Aluno notaLimiteInferior = TurmaFixtures.novoAluno("Livia Teixeira", 15, "M014", 0.0, 0.0);

    return Stream.of(
          Arguments.of(List.of(valido1, valido2), List.of(valido1, valido2)),
          Arguments.of(List.of(valido1, semNome), List.of(valido1)),
          Arguments.of(List.of(valido1, nomeBranco), List.of(valido1)),
          Arguments.of(List.of(valido1, semIdade), List.of(valido1)),
          Arguments.of(List.of(valido1, idadeNegativa), List.of(valido1)),
          Arguments.of(List.of(valido1, semNotaProva), List.of(valido1)),
          Arguments.of(List.of(valido1, semNotaAtividade), List.of(valido1)),
          Arguments.of(List.of(valido1, notaProvaNegativa), List.of(valido1)),
          Arguments.of(List.of(valido1, notaAtividadeNegativa), List.of(valido1)),
          Arguments.of(List.of(valido1, notaProvaAcimaDoLimite), List.of(valido1)),
          Arguments.of(List.of(valido1, notaAtividadeAcimaDoLimite), List.of(valido1)),
          Arguments.of(List.of(valido1, notaLimiteSuperior, notaLimiteInferior),
                List.of(valido1, notaLimiteSuperior, notaLimiteInferior)),
          Arguments.of(List.of(), List.of())
    );
  }
}
