package school.sptech.hospede;

import school.sptech.exceptions.DadoInvalidoException;

public class Reserva extends Hospede{


    private Integer numeroQuarto;
    private Integer qtdDiarias;
    private Double valorDiaria;
    private Boolean ativa;

    public Reserva(String nome, String cpf ,Integer numeroQuarto, Integer qtdDiarias, Double valorDiaria)  throws DadoInvalidoException{
    super(nome, cpf);

        if (cpf == null || cpf.isBlank()){
            throw new DadoInvalidoException();
        }
        if (numeroQuarto == null || numeroQuarto <= 0){
            throw new DadoInvalidoException();
        }

        if (qtdDiarias == null || qtdDiarias <= 0){
            throw new DadoInvalidoException();
        }
        if (valorDiaria == null || valorDiaria <= 0){
            throw new DadoInvalidoException();
        }
        this.numeroQuarto = numeroQuarto;
        this.qtdDiarias = qtdDiarias;
        this.valorDiaria = valorDiaria;
        this.ativa = true;
    }

    public Double calcularValor(){
        Double valorTotal = qtdDiarias * valorDiaria;
        Double taxaLimpeza = 40.0;
        if (qtdDiarias > 10){
            taxaLimpeza = 0.0;
        }
        if (qtdDiarias > 5){
            Double desconto = valorTotal * (20.0/100);
            valorTotal -= desconto;
        }
        return valorTotal + taxaLimpeza;
    }

    public void finalizar(){
        ativa = false;
    }



    public Integer getNumeroQuarto() {
        return numeroQuarto;
    }

    public Integer getQtdDiarias() {
        return qtdDiarias;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public Boolean getAtiva() {
        return ativa;
    }
}
