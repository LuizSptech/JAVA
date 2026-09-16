package school.sptech.ex02;

public class Aluno {
    private String nome;
    private Integer idade;
    private String matricula;
    private Double novaProva;
    private Double notaAtividade;



    public Double calcularNotaFinal(){
        Double notaFinal = 0.0;
        
        return notaFinal;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public Double getNovaProva() {
        return novaProva;
    }

    public Double getNotaAtividade() {
        return notaAtividade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setNovaProva(Double novaProva) {
        this.novaProva = novaProva;
    }

    public void setNotaAtividade(Double notaAtividade) {
        this.notaAtividade = notaAtividade;
    }
}
