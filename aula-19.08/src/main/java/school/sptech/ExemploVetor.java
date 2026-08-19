package school.sptech;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ExemploVetor {
    public static void main(String[] args) {
        // let numeros = [20]
        // no JAVA o vetor e estatico
        // kebab-case
        // PascalCase
        //carnelCase

        Integer[] numero = new Integer[5];
        numero[0] = 10;
        numero[1] = 20;
        numero[2] = 30;

        System.out.println(Arrays.toString(numero));


        // let frutas = ["banana","melancia","maça"]

        //String[] frutas = new String[]{"banana","melancia","maça"};
        String[] frutas = {"banana", "melancia", "maçã"};
        System.out.println(Arrays.toString(frutas));
        System.out.println(frutas.length);
        System.out.println(numero.length);

        for (int i = 0; i < numero.length; i++) {

            if (numero[i] != null) {
                int numeroDaVez = numero[i];
                System.out.println(numeroDaVez);
            }else {
                break;
            }}

            //enhacend for
            //for aprimorado
            for (int numeroDaVez: numero) {
                System.out.println("numero da vez: " + numeroDaVez);
            }

         //10.fori
        //numero.fori os cara inventa muito
        //for(){}
    }
}