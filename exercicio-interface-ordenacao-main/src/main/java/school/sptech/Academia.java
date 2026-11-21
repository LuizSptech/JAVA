
package school.sptech;

import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.NaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Academia {
    private String nome;
    private List<Plano> planos;
    private List<Aluno> alunos;
    private List<AvaliacaoFisica> avaliacoes;

    public Academia(String nome) {
        this.nome = nome;
        this.planos = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarPlano(Plano plano){
        if (plano == null){
            throw new DadoInvalidoException();
        }

        for (Plano plano1 : planos) {
            if (plano1.getNome().equalsIgnoreCase(plano.getNome())){
                throw new DadoInvalidoException();
            }
        }
        planos.add(plano);
    };


    public void adicionarAvaliacao(AvaliacaoFisica avaliacao){
        if (avaliacao == null){
            throw new DadoInvalidoException();
        }
        for (AvaliacaoFisica avaliacoe : avaliacoes) {
            if (avaliacoe.getNome().equalsIgnoreCase(avaliacao.getNome())){
                throw new DadoInvalidoException();
            }
        }

        avaliacoes.add(avaliacao);
    }

    void cadastrarAluno(Aluno aluno){
        if (aluno == null){
            throw new DadoInvalidoException();
        }

        for (Aluno aluno1 : alunos) {
            if (aluno1.getNome().equalsIgnoreCase(aluno.getNome())){
                throw new DadoInvalidoException();
            }
        }
        alunos.add(aluno);
    }


    void matricular(String nomeAluno, String nomePlano) {



    }






public String getNome() {
    return nome;
}

public List<Plano> getPlanos() {
    return planos;
}

public List<Aluno> getAlunos() {
    return alunos;
}

public List<AvaliacaoFisica> getAvaliacoes() {
    return avaliacoes;
}
}
