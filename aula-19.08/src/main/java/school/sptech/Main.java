package school.sptech;



public class Main {
    public static void main(String[] args) {
        //function dizerBomDia()
        //(...)

        Auxiliar alan = new Auxiliar();
        alan.dizerBomDia("Luiz ");
        alan.dizerBomDia();
        alan.dizerBomDia(10);

        Calculadora teste = new Calculadora();
        //Double resultado = teste.Somar(5.0,4.5);
       // teste.Somar(resultado , 20.0);
       // teste.verificarMaiorIdade(20);

        Integer[] lista = {10,10,10,10,10};
        Integer[] listaA = {10,10,10,10,10};
        teste.Somar(lista);

        teste.media(listaA);


    }
}

