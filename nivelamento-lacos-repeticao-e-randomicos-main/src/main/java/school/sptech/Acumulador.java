package school.sptech;
import java.util.concurrent.ThreadLocalRandom;
public class Acumulador {
    public static void main(String[] args) {
        Integer numeroA = ThreadLocalRandom.current().nextInt(11);
        Integer soma = 0;

        while (numeroA  != 0){

            soma += numeroA;
            numeroA = ThreadLocalRandom.current().nextInt(11);
        }
        System.out.println("A soma dos números é " + soma);
        System.out.println(numeroA);

    }
}
