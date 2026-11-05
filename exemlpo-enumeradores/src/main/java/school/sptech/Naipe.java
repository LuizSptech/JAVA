package school.sptech;

// Enum -> enumerador
// lista de valores constantes
// Padrão de nomenclatura: SCREAMING_CASE
public enum Naipe {
    OUROS("♦\uFE0F"),
    PAUS("♣\uFE0F"),
    ESPADAS("♠\uFE0F"),
    COPAS("♥\uFE0F");

    private final String unicode;

    //Não se deve ter um constructor publico se não outra classe pode usar
    //Por padrão, o construtotr do ENUM é private
     Naipe(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode() {
        return unicode;
    }


    @Override
    public String toString() {
        return "Naipe{" +
                "unicode='" + unicode + '\'' +
                "} " + super.toString();
    }
}
