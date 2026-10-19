package school.sptech;

public class Roupa extends Produto{
    private String cor;
    private  Double tamanho;
    private String material;



    public Double calcularPrecoFinal(){
        if (material.equalsIgnoreCase("couro")){
            Double ac = super.calcularPrecoFinal() * (15.0/100.0);
            return super.calcularPrecoFinal() - ac;
        }
        return super.calcularPrecoFinal();
    }
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Roupa{" +
                "cor='" + cor + '\'' +
                ", tamanho=" + tamanho +
                ", material='" + material + '\'' +
                '}';
    }
}
