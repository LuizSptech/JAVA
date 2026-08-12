package school.sptech;

public class ComparadorIgual {
    public static void main(String[] args) {
        /*Integer numeroA = 100;
        Integer numeroB = 100;
        String

        if(numeroA == numeroB){
            System.out.println("Numeros iguais");
        } else{
            System.out.println("Numeros diferentes");
        }*/
        //Para comparar tipos Wrapper usamos .equals
    //Tipos primitivos e nulos usamos ==
    //== se compara o endereçamento de memoria não o conteudo
        String textoA = null;
        String textoB = null;

        if(textoA != null && textoA.equals(textoB)){
            System.out.println("Tex equals");
        }else {
            System.out.println("not equal");
        }

        String nome = "Lucas";
        String nome2 = "lucas";
        if (nome.equalsIgnoreCase(nome2)){
            System.out.println("Nomes iguais");
        } else {
            System.out.println("Nomes diferentes");
        }

        /*int a = 0;
        int b = 0;
        if (a == b){
            System.out.println("mister");
        }else {
            System.out.println("dammit");
        }*/
        //NullPointerException
    }
}
