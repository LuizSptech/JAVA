package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;

public class Usuario {

    private String nome;


    public Usuario(String nome) throws DadoInvalidoException {

        if (nome == null||nome.isBlank()){
            throw new DadoInvalidoException();
        }

        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}