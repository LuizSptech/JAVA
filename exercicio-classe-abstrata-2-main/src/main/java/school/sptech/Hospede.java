package school.sptech;

import school.sptech.exceptions.ValorInvalidoException;

public  abstract class Hospede {
    private String nome;
    private String documento;
    private Integer quarto;
    private Integer dias;
    private Double consumo;


    public Hospede(String nome, String documento, Integer quarto, Integer dias, Double consumo) {


        this.nome = nome;
        this.documento = documento;
        this.quarto = quarto;
        setDias(dias);
        setConsumo(consumo);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws ValorInvalidoException{
        if ( nome == null ||nome.isBlank() ){
            throw new ValorInvalidoException();
        }
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getQuarto() {
        return quarto;
    }

    public void setQuarto(Integer quarto) {
        this.quarto = quarto;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) throws ValorInvalidoException{
        if (dias == null || dias <= 0){
            throw new ValorInvalidoException();
        }
        this.dias = dias;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo)throws ValorInvalidoException {
        if (consumo == null || consumo < 0){
            throw new ValorInvalidoException();
        }
        this.consumo = consumo;
    }

    public abstract Double calcularDiaria();


    public abstract Integer calcularPontosFidelidade();

    public abstract String getTipo();


    public Double calcularTotalEstadia(){
        Double desconto = 0.0;
        Double valor = calcularDiaria();
        for (Integer i = 0; i < dias; i++) {
            if (i > 7){
                desconto = valor * (15/100.0);
                valor = valor - desconto;
                break;
            }

        }Double valorConsumo = getConsumo();

        Double valorHospedagem = valor * dias;
        Double valorTaxa = valorConsumo * (10.0/100.0);
        Double total = valorConsumo + valorTaxa + valorHospedagem;

        System.out.println(valor);
        System.out.println(valorConsumo);
        System.out.println(valorHospedagem);
        System.out.println(valorTaxa);
        return total;
    }


}
