package school.sptech;

public class JogoTabuleiro extends Produto {
    private Integer qtdPecas;

    public JogoTabuleiro(String nome, Raridade raridade, Integer qtdPecas) {
        super(nome, raridade);
        this.qtdPecas = qtdPecas;
    }

    public Integer getQtdPecas() {
        return qtdPecas;
    }

    public void setQtdPecas(Integer qtdPecas) {
        this.qtdPecas = qtdPecas;
    }

    public Double calcularPreco(){
        Double valorPorPeca = qtdPecas * 0.50;

        Double calculaRaridade = valorPorPeca * getRaridade().getAdicionalPorcentagem()/100.0;
        Double valor = valorPorPeca + calculaRaridade;

        return valor;
    }
}
