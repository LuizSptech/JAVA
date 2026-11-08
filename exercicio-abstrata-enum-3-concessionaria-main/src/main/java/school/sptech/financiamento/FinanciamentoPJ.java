package school.sptech.financiamento;

import school.sptech.Empresa;
import school.sptech.Veiculo;

public class FinanciamentoPJ extends Financiamento{
    private Empresa empresa;

    public FinanciamentoPJ(Veiculo veiculo, Integer parcelas, Integer parcelasPagas, Empresa empresa) {
        super(veiculo, parcelas, parcelasPagas);
        this.empresa = empresa;
    }

    public Double calcularValorFinanciamento(){
        Double valorTotal = getVeiculo().calcularValorFinal();
        Double juros = empresa.getTipo().getJuros();
        Double conta = valorTotal * juros;
        valorTotal += conta;
       return valorTotal;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
