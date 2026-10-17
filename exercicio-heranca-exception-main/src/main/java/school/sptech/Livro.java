package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Livro extends ItemBiblioteca{

    private String autor;


    public Livro(String titulo, String autor) throws DadoInvalidoException {
        super(titulo);
        if (autor == null || autor.isBlank()){
            throw new DadoInvalidoException();
        }

        this.autor = autor;

    }

    public Integer getPrazoEmprestimoDias(){
        return 14;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}