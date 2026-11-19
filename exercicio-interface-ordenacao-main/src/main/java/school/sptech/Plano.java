package school.sptech;

import school.sptech.exception.DadoInvalidoException;

public abstract class Plano {
    private  final String nome;
    private  final Double precoBase;


    public Plano(String nome, Double precoBase) {
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException();
        }
        if (precoBase == null || precoBase < 0){
            throw new DadoInvalidoException();
        }
        this.nome = nome;
        this.precoBase = precoBase;
    }

    public abstract Double calcularMensalidade();


    public String getNome() {
        return nome;
    }

    public Double getPrecoBase() {
        return precoBase;
    }
}
