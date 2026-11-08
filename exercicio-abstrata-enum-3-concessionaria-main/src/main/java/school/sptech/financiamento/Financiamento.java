package school.sptech.financiamento;

import school.sptech.Veiculo;
import school.sptech.exceptions.PagamentoInvalidoException;

public abstract class Financiamento {
    private Veiculo veiculo;
    private Integer parcelas;
    private Integer parcelasPagas;


    public Financiamento(Veiculo veiculo, Integer parcelas, Integer parcelasPagas) {
        this.veiculo = veiculo;
        this.parcelas = parcelas;
        this.parcelasPagas = 0;
    }


    public abstract Double calcularValorFinanciamento();

    public Double calcularValorParcela(){
       Double retorno =  calcularValorFinanciamento() / parcelas;

       return retorno;
    }

    public void pagarParcela(Double valor) throws PagamentoInvalidoException {
        if (valor == null || !valor.equals(calcularValorParcela())){
            throw new PagamentoInvalidoException();
        }
        parcelasPagas++;
    }

    public Double calcularValorPago(){
        Double pagoAgora = calcularValorParcela() * parcelasPagas;
        return pagoAgora;
    }

    public Double calcularValorAPagar(){
        Double total = calcularValorParcela() * parcelas;
        Double pago =  total - calcularValorPago();
        return pago;
    }


    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Integer getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(Integer parcelasPagas) {
        this.parcelasPagas = parcelasPagas;
    }
}
