package school.sptech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Aluno aluno01 = new Aluno("04261001", "Bob");
        aluno01.setAc01(6.0);
        aluno01.setAc02(8.0);
        aluno01.setAc03(9.0);

        System.out.println(aluno01);
        System.out.println("Media final aluno01 " + aluno01.calcularNotaFinal());

        AlunoPos alunoPos01 = new AlunoPos("04261002", "Ian");
        alunoPos01.setAc01(6.0);
        alunoPos01.setAc02(8.0);
        alunoPos01.setAc03(9.0);
        alunoPos01.setNotaTcc(9.0);
        AlunoPos alunoPos03 = new AlunoPos("04261003", "Ana");
        alunoPos03.setAc01(6.0);
        alunoPos03.setAc02(4.0);
        alunoPos03.setAc03(10.0);
        alunoPos03.setNotaTcc(5.0);


        System.out.println(alunoPos01);
        System.out.println("Media final alunoPos01 " + alunoPos01.calcularNotaFinal());




        Faculdade faculdade = new Faculdade("SPtech");
        faculdade.matiicular(aluno01);
        faculdade.matiicular(alunoPos01);
        faculdade.matiicular(alunoPos03);

        faculdade.exibirAlunos();
        System.out.println("Média da faculdade " + faculdade.calcularMediaFaculdade());

        faculdade.exibirAlunosPos();
        System.out.println("Nota media TCC " + faculdade.calcularMediaTcc());
    }
}