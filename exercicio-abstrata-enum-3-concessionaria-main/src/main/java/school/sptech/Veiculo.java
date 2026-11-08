package school.sptech;

public class Veiculo {
 private String placa;
 private String modelo;
 private String marca;
 private Double precoBase;
 private  Integer anosUso;
 private Integer kmRodados;

    public Veiculo(String placa, String modelo, String marca, Double precoBase, Integer anosUso, Integer kmRodados) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.precoBase = precoBase;
        this.anosUso = anosUso;
        this.kmRodados = kmRodados;
    }


    public Double calcularValorFinal(){
        Double valorTotal = precoBase;
        Double teste = 0.0;
        for (int i = 0; i < anosUso; i++) {
            teste += valorTotal * (1.0/100.0);
        }
        valorTotal -= teste;
        Integer R = kmRodados;
        Integer conta = 0;
        while (R >= 10000){
            conta++;
            R -= 10000;
        }
        conta *= 500;
        valorTotal -= conta;

        if (marca.equalsIgnoreCase("Ferrari") || marca.equalsIgnoreCase("Lamborghini") || marca.equalsIgnoreCase("Porsche")){
            valorTotal += precoBase * (10.0/100.0);
        }


        return valorTotal;
    }



    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    public Integer getAnosUso() {
        return anosUso;
    }

    public void setAnosUso(Integer anosUso) {
        this.anosUso = anosUso;
    }

    public Integer getKmRodados() {
        return kmRodados;
    }

    public void setKmRodados(Integer kmRodados) {
        this.kmRodados = kmRodados;
    }
}
