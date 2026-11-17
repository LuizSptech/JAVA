package school.sptech;

public class EstatisticaJornalista extends Estatistica{
private final String jornalista;
private Integer qtdPublicacoesJornalista;

    public EstatisticaJornalista(String jornalista ) {
        this.jornalista = jornalista;
        this.qtdPublicacoesJornalista = 0;
    }

    public void receberPublicacao(Noticia noticia){
        super.receberPublicacao(noticia);
        if (noticia.getAutor().equals(jornalista)) {
            qtdPublicacoesJornalista++;
        }
    }
    @Override
    public Double getPercentual() {
        if (getQtdPublicacoesRecebidas().equals(0)){
            return 0.0;
        }
        return ((double) qtdPublicacoesJornalista / super.getQtdPublicacoesRecebidas()) * 100;

    }


    public String getJornalista() {
        return jornalista;
    }

    public Integer getQtdPublicacoesJornalista() {
        return qtdPublicacoesJornalista;
    }
}
