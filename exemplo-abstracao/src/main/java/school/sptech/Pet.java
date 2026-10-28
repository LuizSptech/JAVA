package school.sptech;

//abstract: é so uma ideia
//Não iremos criar um objeto do tipo pet
//Ele é apenas uma base para os outros pets
public abstract class Pet {

    private String nome;
    private Integer idade;
    private Double peso;
    private Double tamanho;


    public Pet(String nome, Integer idade, Double peso, Double tamanho) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.tamanho = tamanho;
    }

    public abstract void comer();

    public abstract void emitirSom();

//obrigado a reescrever apenas em classe abastrada
// Subclasses são obrigadas a sobreescrever desde que elas sejam subclasses concretas
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", peso=" + peso +
                ", tamanho=" + tamanho +
                '}';
    }
}
