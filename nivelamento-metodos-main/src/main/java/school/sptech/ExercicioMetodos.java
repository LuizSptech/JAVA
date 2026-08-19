package school.sptech;

public class ExercicioMetodos {
    Boolean verificarMaioridade(Integer idade){
        if (idade >= 18){
            return true;
        }
        return false;
    }
    Double calcularMedia(Double valor1, Double valor2, Double valor3){
        Double soma = valor1 + valor2 + valor3;
        Double divisão = soma / 3;
        return divisão;
    }

    Integer maiorNumero(Integer valor1, Integer valor2, Integer valor3) {
        if (valor1 >= valor2 && valor1 > valor3) {
            return valor1;
        } else if (valor2 >= valor1 && valor2 > valor3) {
            return valor2;

        } return valor3;

    }
    Integer calcularFatorial(Integer valor){
        Integer fatorar = valor;
        for (int i = 1; i < valor; i++) {
             fatorar *= i;

        }
        return  fatorar;
    }

    Boolean verificarPrimo(Integer valor){
        Integer qtdDivisor = 0;
        for (int i = 1; i <= valor; i++) {
            if (valor % i == 0){
                qtdDivisor++;
            }
        }
        if (qtdDivisor.equals(2)){
            return true;
        }
        return false;
    }

    Integer calcularPotencia(Integer base, Integer expoente){
        Integer output = base;
        for (int i = 1; i < expoente; i++) {
            output *= base;
        }
        return output;
    }







}



