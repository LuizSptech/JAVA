package school.sptech;

public class Calculadora {

     void Somar(Integer[] lista) {
         Integer montante = 0;
         for (int i = 0; i < lista.length; i++) {
             montante += lista[i];

         }
         System.out.println(montante);

     }

     void media(Integer[] listaA){
         Integer soma = 0;
         Integer dividir = 0;
             for (int i = 0; i < listaA.length; i++) {
                 soma += listaA[i];
                 dividir = soma / listaA.length;

             }
         System.out.println(dividir);
         }

         //System.out.println("Soma " + resultado);
         /*return resultado;}

         // o retorn para o método
         void verificarMaiorIdade(Integer idade){
             if (idade >= 18){
                 System.out.println("maior de idade");
                 return;
             }*/



     }
