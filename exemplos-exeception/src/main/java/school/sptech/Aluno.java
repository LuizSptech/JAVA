package school.sptech;

import school.sptech.exceptions.AlunoInvalidoException;
import school.sptech.exceptions.NotaInvalidaException;

public class Aluno {
    private String nome;
    private String documento;
    private Double nota;
    private Double individual;

    public Aluno(String nome, String documento) throws AlunoInvalidoException {
        if (nome == null || documento == null){
            throw new AlunoInvalidoException("Nome e documentos são obrigatorios");
        }


        this.nome = nome;
        this.documento = documento;
    }

 // Criar o metodo que faz a faz a nota final do aluno
    // 70% do invidiual
    // 30% da nota
    // se uma das notas for null lançar uma exception personalizada
    // Chamar na main e tratar a exception com uma mensagem para o usuario


    // criar um metodo que atualiza o documento do aluno
    // novo documento não pode ser nulo ou vazio
    // o documento precisa ter exatamente 11 char
    // o novo documente não pode ser igual o atual
    // o documento atual não pode ser alterado caso alguma regra seja alterada
    // se todas as regras forem atentidas se não lança o DocumentoInvalidException


    public Double calcularNota(Double nota, Double individual ){
        if (nota == null || individual == null){
            throw new NotaInvalidaException("As notas de prova e individual precisam ser obrigatoria");
        }
        this.nota = nota;
        this.individual = individual;

        Double nota1 = nota * 3;
        Double nota2 = individual * 7;
        Double soma = nota1 + nota2;
        return soma / 10.0;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Double getIndividual() {
        return individual;
    }

    public void setIndividual(Double individual) {
        this.individual = individual;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "nome='" + nome + '\'' +
                ", documento='" + documento + '\'' +
                ", nota=" + nota +
                ", individual=" + individual +
                '}';
    }
}
