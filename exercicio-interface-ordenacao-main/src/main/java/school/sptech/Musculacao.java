package school.sptech;

public class Musculacao extends Plano{
private Boolean vinteQuatroHoras;

    public Musculacao(String nome, Double precoBase, Boolean vinteQuatroHoras) {
        super(nome, precoBase);
        this.vinteQuatroHoras = vinteQuatroHoras;
    }



    public Double calcularMensalidade(){
        Double acrescimmo = 0.0;
        Double preco = getPrecoBase();
        if (vinteQuatroHoras == true){
            acrescimmo = getPrecoBase() * (20.0/100);
        }
        preco += acrescimmo;
        return preco;
    }


    public Boolean getVinteQuatroHoras() {
        return vinteQuatroHoras;
    }
}
