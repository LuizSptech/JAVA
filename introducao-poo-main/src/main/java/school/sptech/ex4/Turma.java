package school.sptech.ex4;

public class Turma {
    String turma;
    Integer capacidadeMaxima;
    Integer quantidadeAlunosMatriculados;




    void matricularAluno(Integer quantidade) {
        if (quantidade < 0) {
            return;
        }

        if (quantidadeAlunosMatriculados + quantidade > capacidadeMaxima) {
            return;
        }

        quantidadeAlunosMatriculados += quantidade;
    }

    Double encontrarMaiorNota(Double[] notas) {
        Double maior = notas[0];

        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > maior) {
                maior = notas[i];
            }
        }

        return maior;
    }
    Double calcularMediaTurma(Double[] notas) {
        Double soma = 0.0;

        for (Double nota : notas) {
            soma += nota;
        }

        return soma / notas.length;
    }

    Integer contarAprovados(Double[] notas) {
        Integer aprovados = 0;

        for (Double nota : notas) {
            if (nota >= 6.0) {
                aprovados++;
            }
        }

        return aprovados;
    }

    Boolean validarQuantidadeNotas(Double[] notas) {
        return notas.length == quantidadeAlunosMatriculados;
    }

    Double encontrarNotaMaisProximaDaMedia(Double[] notas) {
        Double media = calcularMediaTurma(notas);

        Double notaMaisProxima = notas[0];
        Double menorDiferenca = Math.abs(notas[0] - media);

        for (int i = 1; i < notas.length; i++) {
            Double diferenca = Math.abs(notas[i] - media);

            if (diferenca < menorDiferenca) {
                menorDiferenca = diferenca;
                notaMaisProxima = notas[i];
            }
        }

        return notaMaisProxima;
    }
}
