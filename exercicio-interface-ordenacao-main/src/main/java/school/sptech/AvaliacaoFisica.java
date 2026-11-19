package school.sptech;

import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.VagaIndisponivelException;

public class AvaliacaoFisica implements Agendavel{
    private String nome;
    private String profissional;
    private Double preco;
    private Integer limiteDiario;
    private Integer agendadas;


    public AvaliacaoFisica(String nome, String profissional, Double preco, Integer limiteDiario) {
        if (limiteDiario == null || limiteDiario <= 0){
            throw new DadoInvalidoException();
        }

        this.nome = nome;
        this.profissional = profissional;
        this.preco = preco;
        this.limiteDiario = limiteDiario;
        this.agendadas = 0;
    }

    public Integer getCapacidade(){
        return limiteDiario;
    }

    public Integer getVagasDisponiveis(){
        return getCapacidade() - agendadas;
    }

    public void reservarVaga(){
        if (getVagasDisponiveis() <= 0){
            throw new VagaIndisponivelException();
        }
        agendadas++;
    }


    @Override
    public String getNome() {
        return nome;
    }

    public String getProfissional() {
        return profissional;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getLimiteDiario() {
        return limiteDiario;
    }

    public Integer getAgendadas() {
        return agendadas;
    }
}
