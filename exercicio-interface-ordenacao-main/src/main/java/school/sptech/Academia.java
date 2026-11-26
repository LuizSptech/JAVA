
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

        Plano planoEncontrado = null;
        Aluno alunoEncontrado = null;


        for (Plano plano : planos) {
            if (plano.getNome().equalsIgnoreCase(nomePlano)) {
                planoEncontrado = plano;
                break;
            }
        }
        if (planoEncontrado == null) {
            throw new NaoEncontradoException();
        }
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunoEncontrado = aluno;
                break;
            }
        }
        if (alunoEncontrado == null) {
            throw new NaoEncontradoException();
        }


        alunoEncontrado.matricular(planoEncontrado);
    }

    void agendar(String nomeAluno, Agendavel agendavel) {

        if (agendavel == null) {
            throw new DadoInvalidoException();
        }
        Aluno alunoEncontrado = null;

        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nomeAluno)) {
                alunoEncontrado = aluno;
                break;
            }
        }

        if (alunoEncontrado == null) {
            throw new NaoEncontradoException();
        }

        if (alunoEncontrado.getAgendamentos().contains(agendavel)) {
            throw new DadoInvalidoException();
        }

        agendavel.reservarVaga();

        alunoEncontrado.getAgendamentos().add(agendavel);
    }


    List<Plano> buscarPlanos(String texto) {

        List<Plano> planosEncontrados = new ArrayList<>();


        if (texto == null) {
            return planosEncontrados;
        }


        for (Plano plano : planos) {

            if (plano.getNome().toLowerCase().contains(texto.toLowerCase())) {
                planosEncontrados.add(plano);
            }
        }

        return planosEncontrados;
    }


    Double calcularFaturamentoMensal() {

        Double faturamento = 0.0;

        for (Aluno aluno : alunos) {

            if (aluno.getPlano() != null) {
                faturamento += aluno.getPlano().calcularMensalidade();
            }

        }

        return faturamento;
    }

    List<Agendavel> buscarAgendaveisComVagas() {

        List<Agendavel> agendaveisEncontrados = new ArrayList<>();


        for (Plano plano : planos) {
            if (plano instanceof Agendavel) {
                Agendavel agendavel = (Agendavel) plano;

                if (agendavel.getVagasDisponiveis() > 0) {
                    agendaveisEncontrados.add(agendavel);
                }
            }
        }


        for (AvaliacaoFisica avaliacao : avaliacoes) {
            if (avaliacao.getVagasDisponiveis() > 0) {
                agendaveisEncontrados.add(avaliacao);
            }
        }

        return agendaveisEncontrados;
    }


    List<String> getNomesAlunosOrdenados() {

        List<String> nomes = new ArrayList<>();


        for (Aluno aluno : alunos) {
            nomes.add(aluno.getNome());
        }


        for (int i = 0; i < nomes.size() - 1; i++) {

            for (int j = 0; j < nomes.size() - 1 - i; j++) {

                if (nomes.get(j).compareToIgnoreCase(nomes.get(j + 1)) > 0) {

                    String auxiliar = nomes.get(j);

                    nomes.set(j, nomes.get(j + 1));

                    nomes.set(j + 1, auxiliar);
                }
            }
        }

        return nomes;
    }

    List<Plano> ordenarPlanosPorMensalidade() {

        List<Plano> planosOrdenados = new ArrayList<>(planos);


        for (int i = 0; i < planosOrdenados.size() - 1; i++) {

            for (int j = 0; j < planosOrdenados.size() - 1 - i; j++) {

                if (planosOrdenados.get(j).calcularMensalidade() >
                        planosOrdenados.get(j + 1).calcularMensalidade()) {

                    Plano auxiliar = planosOrdenados.get(j);

                    planosOrdenados.set(j, planosOrdenados.get(j + 1));

                    planosOrdenados.set(j + 1, auxiliar);
                }
            }
        }

        return planosOrdenados;
    }

    List<Agendavel> ordenarAgendaveisPorCapacidade(){
        return null;
    }

        public String getNome () {
            return nome;
        }

        public List<Plano> getPlanos () {
            return planos;
        }

        public List<Aluno> getAlunos () {
            return alunos;
        }

        public List<AvaliacaoFisica> getAvaliacoes () {
            return avaliacoes;
        }


    }