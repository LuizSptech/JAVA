package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String nome;
    private List<Aluno> alunos = new ArrayList<>();

    public Turma(String nome) {
        this.nome = nome;
    }

    public void adicionarAluno(Aluno aluno){
        if (aluno == null || aluno.getRa() == null || aluno.getRa().isBlank()){
            return;
        }
        for (Aluno outro : alunos) {
            if (aluno.getRa().equals(outro.getRa())){
                return;
            }
        }
        alunos.add(aluno);
    }

    public Double calcularMediaGeral(){
        if (alunos == null || alunos.size() <= 0){
            return 0.;
        }
        Double mediaTotal = 0.;
        for (Aluno aluno : alunos) {
            mediaTotal += aluno.calcularMedia();
        }
        return mediaTotal / alunos.size();
    }

    public Aluno buscarAlunoMaiorMedia(){
        if (alunos == null || alunos.size() <= 0){
            return null;
        }
        Aluno maiorMedia = alunos.getFirst();
        for (Aluno aluno : alunos) {
            if (maiorMedia.calcularMedia() < aluno.calcularMedia()){
                maiorMedia = aluno;
            }
        }
        return maiorMedia;
    }

    public List<Aluno> filtrarPorSituacao(String situacao){
        List<Aluno> alunosSituacao = new ArrayList<>();
        if (alunos == null || alunos.size() <= 0){
            return alunosSituacao;
        }
        for (Aluno aluno : alunos) {
            if (aluno.calcularSituacao().equalsIgnoreCase(situacao)){
                alunosSituacao.add(aluno);
            }
        }
        return alunosSituacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "nome='" + nome + '\'' +
                ", alunos=" + alunos +
                '}';
    }
}
