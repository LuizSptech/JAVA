package school.sptech;

import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.VagaIndisponivelException;

public class Personal extends Plano implements Agendavel{
    private String instrutor;
    private Integer sessoesSemana;
    private Integer sessoesAgendadas;

    public Personal(String nome, Double precoBase, String instrutor, Integer sessoesSemana) {

        super(nome, precoBase);
        if (sessoesSemana == null || sessoesSemana <= 0){
            throw new DadoInvalidoException();
        }

        this.instrutor = instrutor;
        this.sessoesSemana = sessoesSemana;
        this.sessoesAgendadas = 0;
    }


   public Double calcularMensalidade(){
        return getPrecoBase() + sessoesSemana * 50;
    }

    public Integer getCapacidade(){
        return sessoesSemana;
    }

    public Integer getVagasDisponiveis(){
        return getCapacidade() - sessoesAgendadas;
    }

    public void reservarVaga(){
        if (getVagasDisponiveis() <= 0){
            throw new VagaIndisponivelException();
        }
        sessoesAgendadas++;
    }


    public String getInstrutor() {
        return instrutor;
    }

    public Integer getSessoesSemana() {
        return sessoesSemana;
    }

    public Integer getSessoesAgendadas() {
        return sessoesAgendadas;
    }
}
