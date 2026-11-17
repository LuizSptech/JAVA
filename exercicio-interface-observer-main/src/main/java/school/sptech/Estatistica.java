package school.sptech;

public abstract class Estatistica implements Observador{
private Integer qtdPublicacoesRecebidas;

    public Estatistica() {
        this.qtdPublicacoesRecebidas = 0;
    }

public abstract Double getPercentual();

public void receberPublicacao(Noticia noticia){
    qtdPublicacoesRecebidas++;
}

    public Integer getQtdPublicacoesRecebidas() {
        return qtdPublicacoesRecebidas;
    }
}
