package school.sptech;

public class Auxiliar {
    //metodos
    // geralmente nome do método é uma ação, um verbo
    void dizerBomDia(String nome){
        System.out.println("Bom dia! " + nome);
    }

    //polimorfismo

    void dizerBomDia(){}

    //Void -> o método não retorna nada

    void dizerBomDia(Integer quantidade){
        for (Integer i = 0; i < quantidade; i++) {
            System.out.println("Bom dia");
        }
    }

    //Não se pode repetir o mesmo tipo de argumento
    // A quantidade de argumentos tambem conta!
}
