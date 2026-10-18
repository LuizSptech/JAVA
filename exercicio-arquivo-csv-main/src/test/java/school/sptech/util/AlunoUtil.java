package school.sptech.util;

import java.util.List;
import school.sptech.Aluno;

public class AlunoUtil {

    public static Object campo(Object aluno, String nomeCampo) throws ReflectiveOperationException {
        return new ObjectFieldMapping<>(Aluno.class).get(nomeCampo).get(aluno);
    }

    public static String ra(Object aluno) throws ReflectiveOperationException {
        return aluno == null ? null : (String) campo(aluno, "ra");
    }

    public static List<String> ras(List<?> alunos) throws ReflectiveOperationException {
        List<String> ras = new java.util.ArrayList<>();
        for (Object aluno : alunos) {
            ras.add(ra(aluno));
        }
        return ras;
    }
}
