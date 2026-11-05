package school.sptech;

public class Carta {
    private Naipe naipe;
    private Simbolo simbolo;

    public Carta(Naipe naipe, Simbolo simbolo) {
        this.naipe = naipe;
        this.simbolo = simbolo;
    }

    public Naipe getNaipe() {
        return naipe;
    }

    public void setNaipe(Naipe naipe) {
        this.naipe = naipe;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    @Override
    public String toString() {
        return "Carta{" +
                "naipe=" + naipe +
                ", simbolo=" + simbolo +
                '}';
    }
}

