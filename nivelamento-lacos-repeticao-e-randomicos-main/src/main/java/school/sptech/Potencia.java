package school.sptech;

public class Potencia {
    public static void main(String[] args) {
        Integer B = 2;
        Integer E = 5;
        Integer R = 1;
        for (int i = 0; i < E; i++){
            R *= B;
        }
        System.out.println(R);
    }
}
