package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Notebook extends Produto{

    private Integer ramGb;


    public Notebook(String nome, Double preco, Integer estoque, Integer ramGb) {
        super(nome, preco, estoque);
        if (ramGb == null || ramGb <= 0){
            throw new DadoInvalidoException("Ram invalida");
        }
        this.ramGb = ramGb;
    }

    public Integer getGarantiaMeses(){
        return 24;
    }

    public Double calcularFrete(){
    if (getPreco() >= 5000.0){
        return 0.0;
    }
    return 60.0;
    }



}
