package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nome;
    private List<Musica> musicas;
    private Usuario dono;


    public Playlist(String nome, Usuario dono) {
        this.nome = nome;
        this.dono = dono;
        musicas = new ArrayList<>();
    }
    public void adicionarMusica(Musica novaMusica){
        musicas.add(novaMusica);
        //this.musicas.add(novaMusica);
    }

    public void  removerMusica(Musica musica){
        musicas.remove(musica);
    }

    public Integer calcularDuracaoTotal(){
        //this.musica.for
        Integer totalDuracao = 0;
        for (Musica musica : this.musicas) {
            totalDuracao += musica.getDuracaoSeg();
        }
        return totalDuracao;
    }


    public String getNome() {
        return nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "nome='" + nome + '\'' +
                ", musicas=" + musicas +
                ", dono=" + dono +
                '}';
    }
}
