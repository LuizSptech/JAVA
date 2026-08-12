package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class AleatoryNumbers {
    public static void main(String[] args) {
        //let aleatorio = Math.random()
        // Para gerar um numero de 0 ate 10, colocar 11 (11 não e sorteado)
        Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(20);
        Integer numeroAleatorio2 = ThreadLocalRandom.current().nextInt(10,21);
        Double numeroAleatorio3 = ThreadLocalRandom.current().nextDouble(11);
        System.out.printf("Numero aleatorio: %.2f ", numeroAleatorio3);
    }
}
