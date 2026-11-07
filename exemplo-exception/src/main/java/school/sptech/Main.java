package school.sptech;

import school.sptech.exceptions.AlunoInvalidoException;
import school.sptech.exceptions.NotaInvalidaException;

public class Main {

    public static void main(String[] args) {
        try {
            Aluno aluno1 = new Aluno("Bob", "123123123");
            aluno1.calcularNotaFinal();
            System.out.println(aluno1);
        } catch (AlunoInvalidoException e) {
            System.out.println("Aluno invalido");
            System.out.println("Mensagem: " + e.getMessage());
        } catch (NotaInvalidaException e) {
            System.out.println("Nota invalida");
        }


        System.out.println("continuando..");
    }
}
