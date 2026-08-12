package school.sptech;

public class OperacoesMatematicas {
    public static void main(String[] args) {
        // +, -, *, /, **

        Double numeroA = 10.0;
        Double numeroB = 5.0;
        System.out.println("Potenciação: " + (Math.pow(numeroA, numeroB)));
        System.out.println("Adição: " + (numeroA + numeroB));
        System.out.println("subtração: " + (numeroA - numeroB));
        System.out.println("multiplicação: " + (numeroA * numeroB));
        System.out.println("Divisão: " + (numeroA / numeroB));
        //Não temos ** para potenciação em java :(


        Integer n1 = 5;
        Integer n2 = 10;
        Double resultado = Double.valueOf(n1) / n2;
        System.out.println(resultado);

    }
}


