package school.sptech;

public class Celular extends Produto{
    private Integer garantiaMeses;
    private String marca;



    public Double calcularPrecoFinal(){
        Double garantia = garantiaMeses * 50.0;
        return super.calcularPrecoFinal() + garantia;
    }

    public Integer getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(Integer garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return "Celular{" +
                "garantiaMeses=" + garantiaMeses +
                ", marca='" + marca + '\'' +
                '}';
    }
}
