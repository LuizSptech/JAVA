package school.sptech;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        // semelhante a const do javascript
        //variavel que nçao pode ser alterarda
        final String nome = "Lucas";

        System.out.println(nome);




        ContaCorrente conta01 = new ContaCorrente("Bob", "123");

        conta01.depositar(100.0);
        conta01.sacar(50.0);
        System.out.println(conta01);

        ContaCorrente conta02 = new ContaCorrente("Ana", "124");
        ContaCorrente conta03 = new ContaCorrente("Luiz", "125");

        System.out.println("contas criadas " + ContaCorrente.getContador());
        dizerBomDia();
        //Exemplo Math.pow()
        Double potenciacao = Math.pow(2.0,2.0);
        Double pi = Math.PI;

        System.out.println(Calculadora.somar(10.0,20.0));
    }


    public static void dizerBomDia(){
        System.out.println("Bom dia!");
    }
}