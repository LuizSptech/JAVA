package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Faculdade {

    private String nome;
    private List<Aluno> alunos = new ArrayList<>();


    public Faculdade(String nome) {
        this.nome = nome;
    }

    public void matiicular(Aluno aluno){
        alunos.add(aluno);
    }

    public void exibirAlunos(){
        System.out.println("=".repeat(30));
        System.out.println("Alunos da faculdade");
        System.out.println("=".repeat(30));
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
    public Double calcularMediaFaculdade(){
        Double total = 0.0;
        for (Aluno aluno : alunos){
            total += aluno.calcularNotaFinal();
        }
        return total / alunos.size();
    }

    public void exibirAlunosPos(){
        System.out.println("=".repeat(50));
        System.out.println("Apenas alunos pos");
        System.out.println("=".repeat(50));

        for (Aluno aluno : alunos) {
            // para saber se o objeto "E-UM"
            //usamos um instanceof para saber
            // se é uma !instancia de"
            if (aluno instanceof AlunoPos){
             System.out.println(aluno);}
        }
    }

    public Double calcularMediaTcc(){
        Double total = 0.0;
        Integer quantidade = 0;

        for (Aluno aluno : alunos) {
            /*if (aluno instanceof AlunoPos){
                //cast
                Aluno alunoPos = (AlunoPos) aluno;
                total += alunoPos.getNotaTcc();
                quantidade++;
            }*/

            //Pattern matching
            if (aluno instanceof AlunoPos alunoPos){
                total += alunoPos.getNotaTcc();
                quantidade++;
            }
        }z

        return total / quantidade;
    }
}


//Instanciar o objeto