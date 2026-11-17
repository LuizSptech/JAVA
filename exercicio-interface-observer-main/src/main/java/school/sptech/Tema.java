package school.sptech;

public enum Tema {
    POLITICA("POLITICA"),
    TECNOLOGIA("TECNOLOGIA"),
    ENTRETENIMENTO("ENTRETENIMENTO"),
    ESPORTE("ESPORTE");


    private final String posicao;

    Tema(String posicao) {
        this.posicao = posicao;
    }

    public String getPosicao() {
        return posicao;
    }
}
