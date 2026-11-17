package br.com.school.tech;

public class Teste {
    public static void main(String[] args) {


        Pessoa aluno = new Aluno("isabela", "123456789","119222","rua 1", "CCO", new double[]{10.0} );
        Professor professor = new Professor("Mariana","123456789","119222","Rua 2", new String[]{"CCO","ADS"}, 5000.0);


        System.out.println(aluno);
        System.out.println(professor);



    }
}
