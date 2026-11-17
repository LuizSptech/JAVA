package br.com.school.tech;

import java.util.Arrays;

public class Professor extends Pessoa{

    private String[] cursos;
    private double salario;

    public Professor(String nome, String cpf, String telefone, String endereco, String[] cursos, double salario) {
        super(nome, cpf, telefone, endereco);
        this.cursos = cursos;
        this.salario = salario;
    }

    public void descricao(){}

    public String[] getCursos() {
        return cursos;
    }

    public void setCursos(String[] cursos) {
        this.cursos = cursos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }


    @Override
    public String toString() {
        return "Professor{" +
                "cursos=" + Arrays.toString(cursos) +
                ", salario=" + salario +
                "} " + super.toString();
    }
}
