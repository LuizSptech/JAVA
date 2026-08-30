package school.sptech;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logs teste = new Logs();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira True or False");
        Boolean lets = scanner.nextBoolean();
        Thread jar = new Thread(() -> {



       teste.Login(lets);
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e){
                System.out.println("Erro");
            }


            teste.capturaDados(lets);
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e){
                System.out.println("Erro");
            }

            teste.tratamento(lets);



        });
        jar.start();
    }
}
