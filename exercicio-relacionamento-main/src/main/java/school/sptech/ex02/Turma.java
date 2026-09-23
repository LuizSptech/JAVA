package school.sptech.ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Turma {

    private String nome;
    private List<Aluno> alunos = new ArrayList<>();

    public void matricular(Aluno aluno) {

        if (aluno == null) {
            return;
        }

        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            return;
        }

        if (aluno.getIdade() == null || aluno.getIdade() < 0) {
            return;
        }

        if (aluno.getNotaProva() == null || aluno.getNotaProva() < 0 || aluno.getNotaProva() > 10) {
            return;
        }

        if (aluno.getNotaAtividade() == null || aluno.getNotaAtividade() < 0 || aluno.getNotaAtividade() > 10) {
            return;
        }

        this.alunos.add(aluno);

    }

    public List<Aluno> buscarPorParteDoNome(String nome) {

        List<Aluno> resultado = new ArrayList<>();

        if (nome == null) {
            return new ArrayList<>();
        }

        for (int i = 0; i < alunos.size(); i++) {

            if (alunos.get(i).getNome().toLowerCase().contains(nome.toLowerCase())) {

                resultado.add(alunos.get(i));
            }
        }

        return resultado;
    }

    public List<Aluno> buscarAprovados(Double notaMinima) {
        List<Aluno> aprovados = new ArrayList<>();
        if (notaMinima == null) {
            return aprovados;
        }

        for (Aluno aluno : alunos) {
            if (aluno.calcularNotaFinal() >= notaMinima) {
                aprovados.add(aluno);
            }
        }

        return aprovados;
    }

    public Double calcularMediaTurma() {
        if (alunos.isEmpty()) {
            return 0.0;
        }

        Double soma = 0.0;
        for (Aluno aluno : alunos) {
            soma += aluno.calcularNotaFinal();
        }

        return soma / alunos.size();
    }

    public List<Aluno> buscarAcimaDaMedia() {
        List<Aluno> acimaDaMedia = new ArrayList<>();
        Double mediaTurma = calcularMediaTurma();

        for (Aluno aluno : alunos) {
            if (aluno.calcularNotaFinal() > mediaTurma) {
                acimaDaMedia.add(aluno);
            }
        }

        return acimaDaMedia;
    }

    public Aluno buscarMenorNota() {
        if (alunos.isEmpty()) {
            return null;
        }

        Aluno alunoMenorNota = alunos.get(0);
        for (Aluno aluno : alunos) {
            if (aluno.calcularNotaFinal() < alunoMenorNota.calcularNotaFinal()) {
                alunoMenorNota = aluno;
            }
        }

        return alunoMenorNota;
    }

    public Double calcularIdadeMedia() {
        if (alunos.isEmpty()) {
            return 0.0;
        }

        double somaIdades = 0.0;
        for (Aluno aluno : alunos) {
            somaIdades += aluno.getIdade();
        }

        return somaIdades / alunos.size();
    }

    public Double calcularAmplitudeNotas() {
        if (alunos.isEmpty()) {
            return 0.0;
        }

        Double maiorNota = alunos.get(0).calcularNotaFinal();
        Double menorNota = alunos.get(0).calcularNotaFinal();

        for (Aluno aluno : alunos) {
            Double notaFinal = aluno.calcularNotaFinal();
            if (notaFinal > maiorNota) {
                maiorNota = notaFinal;
            }
            if (notaFinal < menorNota) {
                menorNota = notaFinal;
            }
        }

        return maiorNota - menorNota;
    }

  /*  public List<Aluno> encontrarAlunosComMesmaNota() {
        List<Aluno> alunosComMesmaNota = new ArrayList<>();

        for (int i = 0; i < alunos.size(); i++) {
            Aluno atual = alunos.get(i);
            boolean temNotaIgual = false;

            for (int j = 0; j < alunos.size(); j++) {
                if (i != j) {
                    Aluno outro = alunos.get(j);
                    if (Double.compare(atual.calcularNotaFinal(), outro.calcularNotaFinal()) == 0) {
                        temNotaIgual = true;
                        break;
                    }
                }
            }

            if (temNotaIgual) {
                alunosComMesmaNota.add(atual);
            }
        }

        return alunosComMesmaNota;
    }*/
   public List<Aluno> encontrarAlunosComMesmaNota() {
       List<Aluno> filtrado = new ArrayList<>();
       for (Aluno aluno : alunos) {
           for (Aluno outro : alunos) {
               if (aluno != outro
                       && aluno.calcularNotaFinal().equals(outro.calcularNotaFinal())
                       && !filtrado.contains(aluno)) {

                   filtrado.add(aluno);
               }
           }
       }
    return filtrado;

   }
}
