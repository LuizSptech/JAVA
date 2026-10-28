package school.sptech;

public class Circulo extends Figura{
    private Double raio;

    public Circulo(String cor, Integer espessura, Double raio) {
        super(cor, espessura);
        this.raio = raio;
    }

    public Double getRaio() {
        return raio;
    }

    public void setRaio(Double raio) {
        this.raio = raio;
    }

    @Override
    public Double calcularArea() {
        Double resultado = Math.PI * (raio * raio);
        return resultado;
    }

    @Override
    public String toString() {
        return "Circulo{" +
                "raio=" + raio +
                "} " + super.toString();
    }
}
