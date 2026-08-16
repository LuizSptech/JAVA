package school.sptech;
import java.util.concurrent.ThreadLocalRandom;

public class loteria {
    public static void main(String[] args) {
    Integer numero = 8;
    Integer numeroAleatorio = ThreadLocalRandom.current().nextInt(1,11);
    Integer contador = 0;
    while (numeroAleatorio != numero){
        contador++;
        numeroAleatorio = ThreadLocalRandom.current().nextInt(1,11);

        System.out.println(contador);
        System.out.println(numeroAleatorio);
    }

    if (contador < 3){
        System.out.println("Você e MUITO sortudo");
    }
    else if (contador < 10) {
        System.out.println("Você é sortudo");
    }else {
        System.out.println("É melhor você parar de apostar e ir trabalhar");
    }
    }

    }

