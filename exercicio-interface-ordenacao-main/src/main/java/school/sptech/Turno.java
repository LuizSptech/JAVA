package school.sptech;

public enum Turno {
    MANHA ("MANHA"),
    TARDE ("TARDE"),
    NOITE ("NOITE");

    private final String posicao;

    Turno(String posicao) {
        this.posicao = posicao;
    }

    public String getPosicao() {
        return posicao;
    }
}
