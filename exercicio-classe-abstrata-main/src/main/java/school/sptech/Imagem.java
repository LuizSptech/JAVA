package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Imagem {
    private List<Figura> figuras;

    public Imagem(List<Figura> figuras) {
        this.figuras = new ArrayList<>();
    }


    public List<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(List<Figura> figuras) {
        this.figuras = figuras;
    }
    public void adicionar(Figura figura){
        figuras.add(figura);
    }
    public Double calcularSomaDasAreas(){
        Double soma = 0.0;
        for (Figura figura : figuras) {
            soma += figura.calcularArea();
        }
        return soma;
    }

    public List<Figura> buscarPorAreaMaiorQue20(){
       Double calculo = 0.0;
       List<Figura> lista = new ArrayList<>();
        for (Figura figura : figuras) {
            calculo = figura.calcularArea();
            if (calculo > 20.0){
                lista.add(figura);
            }
        }
        return lista;
    }


    public List<Figura> buscarQuadrados(){
        List<Figura> quadrados = new ArrayList<>();
        for (Figura figura : figuras) {
            if (figura instanceof Quadrado ac){
                quadrados.add(ac);
            }
        }
        return quadrados;
    }



    @Override
    public String toString() {
        return "Imagem{" +
                "figuras=" + figuras +
                '}';
    }
}
