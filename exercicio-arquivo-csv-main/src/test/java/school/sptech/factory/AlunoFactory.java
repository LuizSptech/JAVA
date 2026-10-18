package school.sptech.factory;

import school.sptech.Aluno;
import school.sptech.util.ObjectFieldBuilder;

public class AlunoFactory {

    public static Object build(String ra, String nome, Double nota1, Double nota2, Integer frequencia) throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Aluno.class)
              .with("ra", ra)
              .with("nome", nome)
              .with("nota1", nota1)
              .with("nota2", nota2)
              .with("frequencia", frequencia)
              .build();
    }
}
