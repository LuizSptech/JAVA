package school.sptech;

public class Livro extends Produto {
    private String editora;
    private String autor;
    private Integer qtdPaginas;
    private Formato formato;


    public Livro( String nome, Raridade raridade, String editora, String autor, Integer qtdPaginas, Formato formato) {
        super(nome,raridade);
        this.editora = editora;
        this.autor = autor;
        this.qtdPaginas = qtdPaginas;
        this.formato = formato;

    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getQtdPaginas() {
        return qtdPaginas;
    }

    public void setQtdPaginas(Integer qtdPaginas) {
        this.qtdPaginas = qtdPaginas;
    }

    public Formato getFormato() {
        return formato;
    }

    public void setFormato(Formato formato) {
        this.formato = formato;
    }

    public Double calcularPreco(){
        Double valorPagina = null;
        if (formato.equals(Formato.DIGITAL)){
            valorPagina = 0.20;
        }
        else if (formato.equals(Formato.COMUM)){
            valorPagina = 0.30;
        } else{
            valorPagina = 0.40;
        }
        Double valorPelasPagina = valorPagina * qtdPaginas;
        Double acrescimo = (valorPelasPagina * (getRaridade().getAdicionalPorcentagem()/100.0) + valorPelasPagina);

        return acrescimo;
    }

}
