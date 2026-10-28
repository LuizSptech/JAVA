package school.sptech;

public class Gato extends Pet{
    private Boolean arranhaMuito;

    public Gato(String nome, Integer idade, Double peso, Double tamanho, Boolean arranhaMuito) {
        super(nome, idade, peso, tamanho);
        this.arranhaMuito = arranhaMuito;
    }


    @Override
    public void emitirSom() {
        System.out.println("miau miau");
    }

    public  void comer(){
        System.out.println("comendo sache");

    }
    public Boolean getArranhaMuito() {
        return arranhaMuito;
    }

    public void setArranhaMuito(Boolean arranhaMuito) {
        this.arranhaMuito = arranhaMuito;
    }

    @Override
    public String toString() {
        return "Gato{" +
                "arranhaMuito=" + arranhaMuito +
                "} " + super.toString();
    }
}
