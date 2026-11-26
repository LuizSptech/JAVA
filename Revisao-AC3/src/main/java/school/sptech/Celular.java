package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Celular  extends Produto{
    private Integer armazenamentoGb;


    public Celular(String nome, Double preco, Integer estoque, Integer armazenamentoGb) {
        super(nome, preco, estoque);
        this.armazenamentoGb = armazenamentoGb;
    }

    public Integer getGarantiaMeses(){
        return 12;
    }

    public Double calcularFrete(){
        return 15.0;
    }





}
