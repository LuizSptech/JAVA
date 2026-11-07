package school.sptech;

import school.sptech.exceptions.AlunoInvalidoException;
import school.sptech.exceptions.NotaInvalidaException;

public class Aluno {

    private String nome;
    private String documento;
    private Double nota;
    private Double individual;

    public Aluno(String nome, String documento) throws AlunoInvalidoException {
        if(nome == null || documento == null) {
            throw new AlunoInvalidoException("Nome e documento obrigatório");
        }
        this.nome = nome;
        this.documento = documento;
    }

    // criar um método que faz a nota final do aluno
    // 70% individual
    // 30% nota
    // Se uma das notas for null: lançar uma exception personalizada
    // Chamar na main e tratar exception com uma mensagem pro usuário
    public Double calcularNotaFinal() {
        if(nota == null || individual == null) {
            throw new NotaInvalidaException();
        }
        return nota * 0.3 + individual * 0.7;
    }
    // crie um método que atualize o documento do aluno
//    novoDocumento não pode ser null.
//    novoDocumento não pode ser vazio.
//    O documento deve possuir exatamente 11 caracteres.
//    O novo documento não pode ser igual ao documento atual.
//    O documento atual não pode ser alterado caso alguma regra seja violada.
//    Se todas as regras forem atendidas, atualize o atributo documento.
    // se não, lança DocumentoInvalidoException

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
