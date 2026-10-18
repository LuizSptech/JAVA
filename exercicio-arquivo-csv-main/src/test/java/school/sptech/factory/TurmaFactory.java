package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Turma;
import school.sptech.util.ObjectFieldBuilder;

public class TurmaFactory {

    public static Object build(String nome, List<?> alunos) throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Turma.class)
              .with("nome", nome)
              .with("alunos", new ArrayList<>(alunos))
              .build();
    }
}
