package school.sptech.financiamento;

import school.sptech.Pessoa;
import school.sptech.Veiculo;

public class FinanciamentoPF extends Financiamento{
private Pessoa pessoa;

    public FinanciamentoPF(Veiculo veiculo, Integer parcelas, Integer parcelasPagas, Pessoa pessoa) {
        super(veiculo, parcelas, parcelasPagas);
        this.pessoa = pessoa;
    }

    public Double calcularValorFinanciamento(){
        Double valorTotal = getVeiculo().calcularValorFinal();
        valorTotal += (valorTotal * (20.0/100.0));
        return valorTotal;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
