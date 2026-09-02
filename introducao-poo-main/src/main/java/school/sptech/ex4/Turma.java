package school.sptech.ex4;

public class Turma {
    String turma;
    Integer capacidadeMaxima;
    Integer quantidadeAlunosMatriculados;


    Integer matricularAluno(Integer alunos){
        Integer atualizado = 0;
        if (alunos <= 0){
            return 0;
        }
        Integer validacao = quantidadeAlunosMatriculados + alunos;
        if (validacao > capacidadeMaxima){
             atualizado = validacao - capacidadeMaxima;
        }else {
            quantidadeAlunosMatriculados += alunos;
            return quantidadeAlunosMatriculados;
        }

        return atualizado;
    }
    Double encontrarMaiorNota(Double[] notas){
        Double maiorNota = notas[0];
        for (int i = 0; i < notas.length;) {
            if (maiorNota < notas[i]){
                maiorNota = notas[i];
            }
            i++;
        }
        return maiorNota;
    }


    Double calcularMediaTurma(Double[] av){
        Double Total = 0.0;
        Double Medias = 0.0;
        for (int i = 0; i < av.length; i++){
            Total += av[i];
            Medias = Total / av.length;
        }
        return Medias;
    }

    Integer contarAprovados(Double[] aprov){
        Integer aprovados = 0;
        for (int i = 0; i < aprov.length; i++) {
            if (aprov[i] >= 6.0){
                aprovados++;
            }
        }
        return aprovados;
    }


    Boolean validarQuantidadeNotas(Double[] QTN){
        if (QTN.length > quantidadeAlunosMatriculados || QTN.length < quantidadeAlunosMatriculados){
            return false;

        }
        return true;
    }


}