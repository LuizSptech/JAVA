package school.sptech;

public class laces {
    public static void main(String[] args) {
        // while, for
        //fori
        //forr

        for (int j = 10; j >= 0; --j) {
            if(j > 5){
                break;
            }

            System.out.println("Numero da vez " + j);
        }
        for (int i = 0; i <= 10; i++){
            System.out.println("Numero da vez: " + i);
        }

        System.out.println("WHILE");
        int contador = 11;

        while (contador <= 10) {
            System.out.println("Number while da vez: " + contador);
            contador++;
        }

        int contadorDoWhile = 10;
        do {
            System.out.println("Contador do-while " + contadorDoWhile);
            contadorDoWhile++;
        } while (contadorDoWhile <= 10);
    }
}
