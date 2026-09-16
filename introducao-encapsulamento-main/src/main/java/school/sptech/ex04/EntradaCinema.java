package school.sptech.ex04;

public class EntradaCinema {
    private  String nome;
    private Integer hora;
    private Integer sala;
    private Double valor;
    private Boolean[] assentosDisponiveis;

    public String getNome() {
        return nome;
    }

    public Integer getHora() {
        return hora;
    }

    public Integer getSala() {
        return sala;
    }

    public Double getValor() {
        return valor;
    }

    public Boolean[] getAssentosDisponiveis() {
        return assentosDisponiveis;
    }





    public void aplicarDesconto(Integer idade, Boolean estudante) {
        if (idade == null || estudante == null || idade < 0) {
            return;
        }

        if (idade < 3) {
            this.valor = this.valor * 0.0;
        } else if (idade < 12) {
            this.valor = this.valor * 0.5;
        } else if (idade <= 15 && estudante) {
            this.valor = this.valor * 0.6;
        } else if (idade <= 20 && estudante) {
            this.valor = this.valor * 0.7;
        } else if (idade > 20 && estudante) {
            this.valor = this.valor * 0.8;
        }
    }

    public void aplicarDescontoHorario() {
        if (this.hora < 16) {
            this.valor = this.valor * 0.9;
        }
    }


    public Boolean comprarIngresso(Integer indiceAssento, Double pagamento,
                                   Integer idade, Boolean estudante) {

        Double valorOriginal = this.valor;

        if (indiceAssento == null || pagamento == null ||
                idade == null || estudante == null || idade < 0) {
            return false;
        }

        if (indiceAssento < 0 || indiceAssento >= this.assentosDisponiveis.length) {
            return false;
        }

        if (!this.assentosDisponiveis[indiceAssento]) {
            return false;
        }

        aplicarDesconto(idade, estudante);
        aplicarDescontoHorario();

        if (pagamento < this.valor) {
            this.valor = valorOriginal;
            return false;
        }

        this.assentosDisponiveis[indiceAssento] = false;

        return true;
    }

}
