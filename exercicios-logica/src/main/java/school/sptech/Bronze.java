package school.sptech;

public class Bronze {
    public static void main(String[] args) {

        // B1
        Integer numero = 8;
        Integer dobro = numero * numero;
        Integer triplo = numero * numero * numero;
        Integer quadrado =  numero * numero * numero * numero;
        System.out.println(quadrado);
        System.out.println(triplo);
        System.out.println(dobro);


        // B2

        Double n1 = 10.0;
        Double n2 = 8.0;
        Double n3 = 9.5;
        Double soma = n1 + n2 + n3;
        Double media = soma / 3.0;

        System.out.println(Math.round(media));


        //B3

        Double base = 12.0;
        Double altura = 5.0;
        Double area = base * altura;
        System.out.println(area);

        //B4

        Double raio = 4.0;
        Double areaCir = Math.PI * raio;
        System.out.println(Math.round(areaCir));


        //B5

        if (n1 > n2 && n1 > n3){
            System.out.println("N1 e maior");
        }else if (n2 > n1 && n2 > n3){
            System.out.println("N2 e maior");
        }else {
            System.out.println("N3 e maior");
        }

        //B6

        if ((n1 % 2 == 0) && (n2 % 2 == 0) && (n1 % 2 == 0)) {
            System.out.println("Par");
        } else {
            System.out.println("Impar");
        }

        //B7
        if (media > 6){
            System.out.println("Aprovado");
        }else if (media >= 4 ){
            System.out.println("Recuperação");
        }else {
            System.out.println("Reprovado");
        }


        /*
        Integer potencia = 4;
        for (Integer i = 0; i < potencia; i++){
            potencia *=  3;
        }
        System.out.println(potencia);*/


    }
}
