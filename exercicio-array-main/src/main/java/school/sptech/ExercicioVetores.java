package school.sptech;

import javax.print.DocFlavor;

public class ExercicioVetores {
    Integer somar(Integer[] vetor){
        Integer soma = 0;
        for (Integer i = 0; i < vetor.length; i++){
            soma += vetor[i];
        }
        return soma;
    }


    Double calcularMedia(Double[] notas){
        Double total = 0.0;
        Double saida = 0.0;
        for (Integer Cont = 0; Cont < notas.length; Cont++){
            total += notas[Cont];
            saida = total / notas.length;

        }
        return saida;
    }


    Integer buscarMaiorNumero(Integer[] vetor){
        Integer maior = vetor[0];
        for (Integer i = 0; i < vetor.length; i++){
            for (Integer j = 1; j < vetor.length; j++){
               if (vetor[i] > maior){
                   maior = vetor[i];
               }
            }
        }
        return maior;
    }

    Integer calcularDecimal(Integer[] binario) {

        Integer valendo = 0;

        for (Integer i = 0; i < binario.length; i++) {

            if (binario[i].equals(1)) {
                valendo += (int) Math.pow(2, binario.length - 1 - i);
            }

        }

        return valendo;
    }

    Character[] inverter(Character[] vetor) {

        Character[] saida = new Character[vetor.length];

        for (Integer i = 0; i < vetor.length; i++) {
            saida[i] = vetor[vetor.length - 1 - i];
        }

        return saida;
    }

    Integer[] mesclar(Integer[] vetor1, Integer[] vetor2) {

        Integer[] saida = new Integer[vetor1.length + vetor2.length];

        Integer i = 0;
        Integer j = 0;
        Integer k = 0;

        while (i < vetor1.length && j < vetor2.length) {

            if (vetor1[i] < vetor2[j]) {
                saida[k] = vetor1[i];
                i++;
            } else {
                saida[k] = vetor2[j];
                j++;
            }

            k++;
        }

        while (i < vetor1.length) {
            saida[k] = vetor1[i];
            i++;
            k++;
        }

        while (j < vetor2.length) {
            saida[k] = vetor2[j];
            j++;
            k++;
        }

        return saida;
    }


}