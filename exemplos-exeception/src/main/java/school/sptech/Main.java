package school.sptech;


import school.sptech.exceptions.AlunoInvalidoException;
import school.sptech.exceptions.NotaInvalidaException;

public class Main {
    public static void main(String[] args) {
        try {
            Aluno aluno01 = new Aluno(null, null);
            System.out.println(aluno01);
        } catch (AlunoInvalidoException aluno) {
            System.out.println("Aluno invalido");
            System.out.println("Mensagem: " + aluno.getMessage());
        }
        System.out.println("Continuando...");

        try {
            Aluno aluno02 = new Aluno("Lucas", "04261130");
            System.out.println(aluno02);
            System.out.println(aluno02.calcularNota(null, 10.0));
        } catch (AlunoInvalidoException aluno) {

            System.out.println("Aluno invalido");
            System.out.println("Mensagem: " + aluno.getMessage());
        } catch (NotaInvalidaException nota) {

            System.out.println("Nota invalida");
            System.out.println("Mensagem: " + nota.getMessage());
        }
        System.out.println("Continuando...");
    }

}