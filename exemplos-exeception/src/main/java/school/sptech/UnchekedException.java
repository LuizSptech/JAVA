package school.sptech;


import java.util.ArrayList;
import java.util.List;

// Exception é quando tem uma regra de negocio quebrada
// Uncheked São execpetions não checadas
// ou seja, não sou obrigado a tratar
// elas herdam de RuntimeException

public class UnchekedException {
    public static void main(String[] args) {
        // NullPointerExecption
        try {
            String nome = "Bob";
            System.out.println("Nome lowerCase " + nome.toLowerCase());

            List<String> fruta = new ArrayList<>();
            fruta.get(10);

        }catch (RuntimeException e) {
            System.out.println("Houve um erro");
            e.printStackTrace();

        } /*catch (IndexOutOfBoundsException a) {

            System.out.println("Index invalido");
            String mensagemExeception = a.getMessage();
            System.out.println( "Mensagem: " + mensagemExeception);
            a.printStackTrace();
        }*/



        // IndexOutOfBoundException


        System.out.println("Continuando codigo....");

    }
}
