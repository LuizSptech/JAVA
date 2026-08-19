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
        numero[3] = 67;
        numero[4] = 12;
        //numero[5] = 42;

        System.out.println(Arrays.toString(numero));


        // let frutas = ["banana","melancia","maça"]

        //String[] frutas = new String[]{"banana","melancia","maça"};
        String[] frutas = {"banana", "melancia", "maçã"};
        System.out.println(Arrays.toString(frutas));
        System.out.println(frutas.length);
        System.out.println(numero.length);

        for (int i = 0; i < numero.length; i++) {

            if (numero[i] != null) {
                Integer numeroDaVez = numero[i];
                System.out.println(numeroDaVez);
            }else {
                break;
            }}

            //enhacend for
            //for aprimorado sempre varre a array inteiro!
            //não da para ir de trás para frente
            for (Integer numeroDaVez: numero) {
                System.out.println("numero da vez: " + numeroDaVez);
            }

         //10.fori
        //numero.fori os cara inventa muito
        //numero.for
        //numero.forr

        for (int i = numero.length -1 ; i >= 0; --i){
            System.out.println(numero[i]);
        }

        //Boolean[] likes = new Boolean[5];
        Boolean[] likes = {true,false,false,true,true};

        for (Boolean likeDaVez : likes){
            String mensagem = likeDaVez ? "Deu like :) " : "Deslike :( ";
            System.out.println(mensagem);

        }


        String[] nomes = new String[3];
        int indexDigitado = 5;
        if(indexDigitado >= 0 && indexDigitado < nomes.length){
            nomes[indexDigitado] = "Lucas";
        } else{
            System.out.println("Index invalido");
        }

    }
}