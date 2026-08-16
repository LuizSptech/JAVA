package school.sptech;
import java.util.concurrent.ThreadLocalRandom;
public class Votacao {
    public static void main(String[] args) {
        Integer mussarela = 0;
        Integer calabresa = 0;
        Integer quatroqueijo = 0;
        Integer voto = 0;

        for (int i = 0; i < 10; i++) {
            voto = ThreadLocalRandom.current().nextInt(1,4);
            if (voto == 1){
                mussarela++;
            }
            else if (voto == 3){
                calabresa++;
            }else {
                quatroqueijo++;
            }

        }

        if ((mussarela > calabresa) && mussarela > quatroqueijo){
            System.out.println("Mussarela ganhou ");
        }else if ((calabresa > mussarela) && calabresa > quatroqueijo){
            System.out.println("Calabresa ganhou ");
        } else {
            System.out.println("Quartro Queijos ganhou");
        }
        System.out.println("Mussarela teve um total de " + mussarela + " votos");
        System.out.println("Calabresa teve um total de " + calabresa + " votos");
        System.out.println("Quatro Queijos teve um total de " + quatroqueijo + " votos");

    }
}
