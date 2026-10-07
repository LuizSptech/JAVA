package school.sptech;

public class Livro {
    private String isbn;
    private String titulo;
    private Double precoMedio;


    public Livro(String isbn, String titulo, Double precoMedio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.precoMedio = precoMedio;
    }

    public Livro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(Double precoMedio) {
        this.precoMedio = precoMedio;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", precoMedio=" + precoMedio +
                '}';
    }
}
