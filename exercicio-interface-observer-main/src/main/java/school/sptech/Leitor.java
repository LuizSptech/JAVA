package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Leitor implements Observador{
private String nome;
private String email;
private List<Noticia> publicacoesRecebidas ;
private List<Noticia> publicacoesFavoritas ;
private Tema temaFavorito;


    public Leitor(String nome, String email, Tema temaFavorito ) {
        this.nome = nome;
        this.email = email;
        this.publicacoesRecebidas = new ArrayList<>();
        this.publicacoesFavoritas = new ArrayList<>();
        this.temaFavorito = temaFavorito;

    }

    public void receberPublicacao(Noticia noticia){
        publicacoesRecebidas.add(noticia);
        if (noticia.getTema() == temaFavorito){
            publicacoesFavoritas.add(noticia);

        }
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Noticia> getPublicacoesRecebidas() {
        return publicacoesRecebidas;
    }

    public List<Noticia> getPublicacoesFavoritas() {
        return publicacoesFavoritas;
    }

    public Tema getTemaFavorito() {
        return temaFavorito;
    }
}
