package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Aluno;
import school.sptech.util.ObjectFieldBuilder;

public class AlunoFactory {

    public static Object build(String nome, Object plano, List<?> agendamentos)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Aluno.class)
              .with("nome", nome)
              .with("plano", plano)
              .with("agendamentos", new ArrayList<>(agendamentos))
              .build();
    }

    public static Object build(String nome, Object plano) throws ReflectiveOperationException {
        return build(nome, plano, List.of());
    }
}
