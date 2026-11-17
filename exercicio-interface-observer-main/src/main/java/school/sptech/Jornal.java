package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Jornal {
    private final String nome;
    private List<Observador> observadores;

    public Jornal(String nome) {
        this.nome = nome;
        this.observadores = new ArrayList<>();
    }

    public void inscrever(Observador observador) {
        observadores.add(observador);
    }

    public void desinscrever(Observador observador) {
        observadores.remove(observador);
    }

    public void publicar(Noticia noticia) {

        for (Observador observadore : observadores) {
            observadore.receberPublicacao(noticia);

        }
    }

    public Estatistica getMaiorPercentual(){
        Double maiorPotencia = 0.0;
        Estatistica escolhido = null;
        for (Observador observadore : observadores) {
            if (observadore instanceof Estatistica est){
                 if (est.getPercentual() >= maiorPotencia){
                     maiorPotencia = est.getPercentual();
                     escolhido = est;
                 }
            }
        }

        return escolhido;
    }

    public List<Leitor> getLeitoresPorTema(Tema tema){
        List<Leitor> leitores = new ArrayList<>();
        for (Observador observadore : observadores) {
            if (observadore instanceof Leitor let){
                if (let.getTemaFavorito().equals(tema)){
                    leitores.add(let);
                }
            }
        }
        return leitores;
    }

    public String getJornalistaComMaisPublicacoes(){
        Integer maior = 0;
        String jornal = null;
        for (Observador observadore : observadores) {
            if (observadore instanceof Estatistica est){
                if (est instanceof EstatisticaJornalista jor){
                    if (jor.getQtdPublicacoesJornalista() > maior){
                        maior = jor.getQtdPublicacoesJornalista();
                        jornal = jor.getJornalista();
                    }
                }
            }
        }
        return jornal;
    }

    public Tema getTemaFavorito(){
        Tema escolhido = null;
        for (Observador observadore : observadores) {
           if (observadore instanceof Leitor let){

               escolhido = let.getTemaFavorito();
           }
        }
        return escolhido;
    }




    public String getNome() {
        return nome;
    }

    public List<Observador> getObservadores() {
        return observadores;
    }
}
