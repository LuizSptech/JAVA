package school.sptech;

public class Desafio {
    public static void main(String[] args) {


        String naipes = "ouros".toUpperCase();
        Integer sim = 0;



        Naipe converterString = Naipe.valueOf(naipes);
        Simbolo[] simbolos = Simbolo.values();

        Simbolo  converterSimbolo = null;

        for (Simbolo simbolo : simbolos) {
         if(simbolo.getIndice() == sim){
             converterSimbolo = simbolo;
         }
        }
        System.out.println(converterString);
        System.out.println(converterSimbolo);

    }


}
