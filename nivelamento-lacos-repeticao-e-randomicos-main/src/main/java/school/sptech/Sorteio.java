package school.sptech;
import java.util.concurrent.ThreadLocalRandom;
public class Sorteio {
    public static void main(String[] args) {
        Integer escolha = 1;
        Integer par = 0;
        Integer impar = 0;
        Integer num = ThreadLocalRandom.current().nextInt(1,101);
        for(int i = 0; i < 200; i++){
            num = ThreadLocalRandom.current().nextInt(1,101);
            if (num == escolha){
                System.out.println("Apareceu na posição " + i);
                break;
            }
            if (num % 2 == 0){
                par++;
            }
            if (num % 2 != 0){
                impar++;
            }

        }

        System.out.println( "total de numeros pares " + par );
        System.out.println("total de numeros impares " + impar);
    }
}
