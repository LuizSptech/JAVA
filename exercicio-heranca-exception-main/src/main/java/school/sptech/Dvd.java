package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Dvd extends ItemBiblioteca{

    private Integer duracao;

    public Dvd(String titulo, Integer duracao) throws DadoInvalidoException {
        super(titulo);
        if (duracao == null || duracao <= 0){
            throw new DadoInvalidoException();
        }
        this.duracao = duracao;
    }

    public Integer getPrazoEmprestimoDias(){
        return 3;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }


}