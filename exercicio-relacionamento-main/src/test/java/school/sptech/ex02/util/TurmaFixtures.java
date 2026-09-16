package school.sptech.ex02.util;

import java.util.List;
import school.sptech.ex02.Aluno;
import school.sptech.ex02.Turma;
import school.sptech.util.ObjectFieldBuilder;

public class TurmaFixtures {

  public static Aluno novoAluno(String nome, Integer idade, String matricula, Double notaProva,
        Double notaAtividade) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Aluno.class)
          .with("nome", nome)
          .with("idade", idade)
          .with("matricula", matricula)
          .with("notaProva", notaProva)
          .with("notaAtividade", notaAtividade)
          .build();
  }

  public static Turma novaTurma(String nome, List<Aluno> alunos)
        throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Turma.class)
          .with("nome", nome)
          .with("alunos", alunos)
          .build();
  }
}
