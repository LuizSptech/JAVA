package school.sptech;

public abstract class Produto {
    private String nome;
    private Raridade raridade;

    public Produto(String nome, Raridade raridade) {
        this.nome = nome;
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Raridade getRaridade() {
        return raridade;
    }

    public void setRaridade(Raridade raridade) {
        this.raridade = raridade;
    }

    public abstract Double calcularPreco();}
