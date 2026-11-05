package school.sptech;

public enum Simbolo {
    AS(0),
    DOIS(1),
    TRES(2),
    QUATRO(3),
    CINCO(4),
    SEIS(5),
    SETE(6),
    OITO(7),
    NOVE(8),
    DEZ(9),
    J(10),
    Q(11),
    K(12);

    private Integer indice;

    Simbolo(Integer indice) {
        this.indice = indice;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }
}
