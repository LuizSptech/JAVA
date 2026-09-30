package school.sptech;

public class Aluno {
    // protected: somente o mesmo pacote OU SUBCLASSE podem acessar
    protected String ra;
    protected String nome;
    protected Double ac01;
    protected Double ac02;
    protected Double ac03;


    public Aluno(String ra, String nome) {
        this.ra = ra;
        this.nome = nome;
        //this()
    }
    public Double calcularNotaFinal(){
        return ac01 * 0.25 + ac02 * 0.35 + ac03 * 0.4;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getAc01() {
        return ac01;
    }

    public void setAc01(Double ac01) {
        this.ac01 = ac01;
    }

    public Double getAc02() {
        return ac02;
    }

    public void setAc02(Double ac02) {
        this.ac02 = ac02;
    }

    public Double getAc03() {
        return ac03;
    }

    public void setAc03(Double ac03) {
        this.ac03 = ac03;
    }
//Toda classe do java herda de um 'object'
    @Override
    public String toString() {
        return "Aluno{" +
                "ra='" + ra + '\'' +
                ", nome='" + nome + '\'' +
                ", ac01=" + ac01 +
                ", ac02=" + ac02 +
                ", ac03=" + ac03 +
                '}';

    }

}
