package school.sptech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ContaCorrente conta01 = new ContaCorrente("Luiz","luiz@.com.br");
        ContaCorrente conta02 = new ContaCorrente("Bob","bob@.com.br","11 95321 9567");


        conta01.setTitular("Nando");
        conta01.setTelefone("12345678911");
        System.out.println("Telefone alterado " + conta01.getTelefone());
        System.out.println(conta01);
        //Se colocar o objeto de um print automaticamente ele chama o toString
        System.out.println(conta02);
}
}


//Getters e Setters
//Pegar atributo "GET"
//Mudar atributo "SET"
//metodos publicos com regra de negocio ou não
// que vão alterar atributos privados

//Construtor cheio -> todos atributos
//Construtor vazio -> sem nenhum atributo