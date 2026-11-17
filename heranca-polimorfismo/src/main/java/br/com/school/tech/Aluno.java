package br.com.school.tech;

import java.util.Arrays;

public class Aluno extends Pessoa{

    private String curso;
    private double[] notas;

    public Aluno(String nome, String cpf, String telefone, String endereco, String curso, double[] notas) {
        super(nome, cpf, telefone, endereco);
        this.curso = curso;
        this.notas = notas;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public double[] getNotas() {
        return notas;
    }

    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "curso='" + curso + '\'' +
                ", notas=" + Arrays.toString(notas) +
                "} " + super.toString();
    }
}
