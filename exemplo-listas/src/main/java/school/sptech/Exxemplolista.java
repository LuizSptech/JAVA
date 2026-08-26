package school.sptech;


import java.util.ArrayList;
import java.util.List;

public class Exxemplolista {
    public static void main(String[] args) {

        // no java, vetor é estatico
        // String[] nomes = new String[10];
        // List -> Arraylist que é dinamico


        List<String > nomes = new ArrayList<>();
        //nomes[0] = "Bob";
        nomes.add("Bob");
        nomes.add("Ana");
        nomes.add("Jacob");
        nomes.add("Bruno");
        nomes.add("Lucas");
        // removar um nome: pelo index

        nomes.remove(0);
        // removar um nome: pelo nome

        nomes.remove("Bruno");
        //pegar elemento especifico

        String nome1 = nomes.get(0);
        String ultimo_nome = nomes.get(nomes.size() -1);

        System.out.println("primeiro nome "+ nome1);
        System.out.println("ultimo nome " + ultimo_nome);

        //Atualizar um valor com, primeiro ele pega o index depois o valor
        nomes.set(1, "Kian");


        System.out.println(nomes);
        // .size != lenght
        System.out.println("tamanho vetor " + nomes.size());



        //LISTA COM VALORES
        // String[] frutas = {"Banana"...};

        List<String> frutas = new ArrayList<>(List.of("Banana", "Pera", "Maçã"));
        frutas.add("Melancia");
        System.out.println(frutas);


        // Quero iterar um ArrayList

        for (int i = 0; i < frutas.size(); i++) {

            String fruta_da_vez = frutas.get(i);
            System.out.println(fruta_da_vez);
        }
        //frutas.for
        //Enhanced for ou for aprimorado

        for (String fruta : frutas) {
            System.out.println("Fruta da vez " + fruta );
        }


        //Remover frutas que começam com "M"

        for (String fruta : frutas) {
            if (fruta.startsWith("M")){
                frutas.remove(fruta);
            }
            System.out.println(frutas);
        }

        for (int i = 0; i < frutas.size(); i++) {
            String fruta = frutas.get(i);
            if (fruta.startsWith("M")){
                frutas.remove(i);
                i--;
            }
        }
        System.out.println(frutas);

        //List não aceita primitivos
        //Apenas classes
        List<Integer> numeros = new ArrayList<>();
        numeros.add(10);
        numeros.add(2);
        numeros.add(3);
        System.out.println(numeros);

        //todo que e classe, e um object

        Integer paraRemover = 2;
        numeros.remove(paraRemover);
        
        System.out.println(numeros);
     }
}