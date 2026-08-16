package school.sptech;

public class Contador_Variado {
    public static void main(String[] args) {
        for (Double i = 0.15; i < 5.0; i+= 0.15) {
            System.out.println("%.2f".formatted(i));
        }
    }
}
