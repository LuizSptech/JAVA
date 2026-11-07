package school.sptech;

import java.util.ArrayList;
import java.util.List;

// Exception é quando tem uma regra de negócio quebrada
// Unchecked: são exceptions não checadas
// ou seja, não sou obrigado a tratar
// elas herdam de RuntimeExcepion
public class UncheckedException {

    public static void main(String[] args) {
        try {
            // NullPointerException
            String nome = "Bob";
            System.out.println("Nome lowerCase: " + nome.toLowerCase());

            // IndexOutOfBoundExcepion
            List<String> frutas = new ArrayList<>();
            frutas.get(10);
        } catch (RuntimeException e) {
            System.out.println("Houve um exception");
            System.out.println("Mensagem: " + e.getMessage());
        }

        System.out.println("Continuando código...");
    }
}
