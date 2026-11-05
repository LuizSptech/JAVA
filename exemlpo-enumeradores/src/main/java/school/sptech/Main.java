package school.sptech;

public class Main {
    public static void main(String[] args) {
        Carta carta01 = new Carta(Naipe.ESPADAS, Simbolo.AS);
        Carta carta02 = new Carta(Naipe.COPAS,Simbolo.DEZ);
        Carta carta03 = new Carta(Naipe.PAUS, Simbolo.DEZ);


        System.out.println(carta01);
        System.out.println(carta02);
        //Printar somente o unicode da carta 1

        System.out.println("Naipe carta 1: " + carta01.getNaipe().getUnicode());

        //Printar unicode Espadas

        System.out.println("Unicode espadas: " + Naipe.ESPADAS.getUnicode());
        //Selecionar um naipe

        Naipe naipeAleatorio = Naipe.PAUS;
        System.out.println(naipeAleatorio);

        //Pegar o nome da constante

        String nomeNaipe = Naipe.ESPADAS.name();

        System.out.println(nomeNaipe);


        if(carta01.getNaipe().name().equals("ESPADAS")){
            System.out.println("Carta 01 é espadas");
        } else {
            System.out.println("Carta 01 não é espadas");
        }


        //comparar usando o compareTo

        if(carta02.getSimbolo().compareTo(carta03.getSimbolo()) > 0){
            System.out.println("maior que zero");
        }else if (carta02.getSimbolo().compareTo(carta03.getSimbolo()) == 0){
            System.out.println("deu zero");
        }else {
            System.out.println("menor que zero");
        }

        System.out.println(Simbolo.AS.ordinal());


        // valueOf -> transforma string em enum
        //lança exception se o valor não existir na constante
        Naipe naipeConvertido = Naipe.valueOf("OUROS");
        System.out.println(naipeConvertido);


        // .values
        //todoOssimblosos.for

    }
}