package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Academia;
import school.sptech.util.ObjectFieldBuilder;

public class AcademiaFactory {

    public static Object build(String nome, List<?> planos, List<?> alunos, List<?> avaliacoes)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Academia.class)
              .with("nome", nome)
              .with("planos", new ArrayList<>(planos))
              .with("alunos", new ArrayList<>(alunos))
              .with("avaliacoes", new ArrayList<>(avaliacoes))
              .build();
    }

    public static Object vazia() throws ReflectiveOperationException {
        return build("SPTech Fit", List.of(), List.of(), List.of());
    }
}
