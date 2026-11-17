package school.sptech;

public class Noticia{
private String titulo;
private Tema tema;
private String autor;
private Integer minutosLeitura;


    public Noticia(String titulo, Tema tema, String autor, Integer minutosLeitura) {
        if (titulo == null || titulo.isBlank() || tema == null || autor == null|| autor.isBlank() || minutosLeitura == null || minutosLeitura <= 0){
            throw new NoticiaInvalidaException();
        }

        this.titulo = titulo;
        this.tema = tema;
        this.autor = autor;
        this.minutosLeitura = minutosLeitura;
    }

    public String getTitulo() {
        return titulo;
    }

    public Tema getTema() {
        return tema;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getMinutosLeitura() {
        return minutosLeitura;
    }
}
