package school.sptech;

public class Aluno {
    private String ra;
    private String nome;
    private Double nota1;
    private Double nota2;
    private Integer frequencia;

    public Aluno(String ra, String nome, Double nota1, Double nota2, Integer frequencia) {
        this.ra = ra;
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.frequencia = frequencia;
    }

    public Double calcularMedia(){
        return (nota1 + nota2) / 2;
    }

    public String calcularSituacao(){
        if (frequencia < 75){
            return "REPROVADO";
        }
        Double media = calcularMedia();
        if (media >= 6){
            return "APROVADO";
        }
        if (media >= 4){
            return "RECUPERACAO";
        }
        return "REPROVADO";
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

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "ra='" + ra + '\'' +
                ", nome='" + nome + '\'' +
                ", nota1=" + nota1 +
                ", nota2=" + nota2 +
                ", frequencia=" + frequencia +
                '}';
    }
}
