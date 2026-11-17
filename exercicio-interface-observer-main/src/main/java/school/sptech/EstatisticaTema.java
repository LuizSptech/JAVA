package school.sptech;

public class EstatisticaTema extends Estatistica{
private final Tema tema;
private Integer qtdPublicacoesTema;

    public EstatisticaTema( Tema tema) {

        this.tema = tema;
        this.qtdPublicacoesTema = 0;
    }

    public void receberPublicacao(Noticia noticia){

       super.receberPublicacao(noticia);
        if (noticia.getTema().equals(tema)) {
            qtdPublicacoesTema++;
        }



    }

    @Override
    public Double getPercentual() {
        if (getQtdPublicacoesRecebidas().equals(0)){
            return 0.0;
        }
        return ((double) qtdPublicacoesTema / super.getQtdPublicacoesRecebidas()) * 100;
    }

    public Tema getTema() {
        return tema;
    }

    public Integer getQtdPublicacoesTema() {
        return qtdPublicacoesTema;
    }
}
