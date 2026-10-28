package school.sptech;

public class Cachorro extends Pet{

    private Boolean lateMuito;

    public Cachorro(String nome, Integer idade, Double peso, Double tamanho, Boolean lateMuito) {
        super(nome, idade, peso, tamanho);
        this.lateMuito = lateMuito;
    }

    @Override
    public void comer() {
        System.out.println("Comendo osso...");
    }

    @Override
    public void emitirSom() {
        System.out.println("Au Au...");
    }

    public Boolean getLateMuito() {
        return lateMuito;
    }

    public void setLateMuito(Boolean lateMuito) {
        this.lateMuito = lateMuito;
    }
}
