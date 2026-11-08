package school.sptech;

public enum TipoEmpresa {
MEI(0.03),
ME(0.05),
LTDA(0.10),
SA(0.15),
ONG(0.0),
COOPERATIVA (0.01);

private final Double juros;

    TipoEmpresa(Double juros) {
        this.juros = juros;
    }

    public Double getJuros() {
        return juros;
    }
}
