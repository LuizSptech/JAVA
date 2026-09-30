package school.sptech;

//extends: palavra-chave para usar na herança (é um)
//AlunoPos e a classe filha ou subclasse
//Aluno e a classe pai ou superclasse
//uma classe so pode herdar de apenas uma classe
public class AlunoPos extends Aluno {

    private Double notaTcc;

    public AlunoPos(String ra, String nome){
        //super é uma palavra-chave que referencia
        // a super classe ou classepai
        super(ra, nome);
        this.notaTcc = 0.0;
    }

    // Polimorfismo: mesmo método com formas diferentes
    // Sobrescrita: mesmo método (pai e filha) com comportamentos difefentes
    @Override //opcional
    public Double calcularNotaFinal(){
        // acs -> cada uma vai valer 20%
        // tcc -> 40%
        return  super.getAc01() * 0.2 +
                super.getAc02() * 0.2 +
                super.getAc03() * 0.2 +
                this.notaTcc * 0.4;
    }

    public Double getNotaTcc() {
        return notaTcc;
    }

    public void setNotaTcc(Double notaTcc) {
        this.notaTcc = notaTcc;
    }

    @Override
    public String toString() {
        return "AlunoPos{" +
                "notaTcc=" + notaTcc +
                "} " + super.toString();
    }
}
