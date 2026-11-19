package school.sptech;

import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.VagaIndisponivelException;

public class Natacao extends Plano implements Agendavel{
    private Turno turno;
    private Integer raias;
    private Integer alunosPorRaia;
    private Integer inscritos;


    public Natacao(String nome, Double precoBase, Turno turno, Integer raias, Integer alunosPorRaia) {
        super(nome, precoBase);
        if (raias == null || raias <= 0){
            throw new DadoInvalidoException();
        }
        if (alunosPorRaia == null || alunosPorRaia <= 0){
            throw new DadoInvalidoException();
        }
        this.turno = turno;
        this.raias = raias;
        this.alunosPorRaia = alunosPorRaia;
        this.inscritos = 0;
    }

    public Double calcularMensalidade(){
        Double acrescimo = 0.0;
        Double valor = getPrecoBase();
        if (turno.getPosicao() == "MANHA"){
            acrescimo = valor * (10.0 /100.0);
            valor -= acrescimo;
        }
        if (turno.getPosicao() == "NOITE"){
            acrescimo = valor * (15.0/100.0);
            valor += acrescimo;
        }
        return valor;
    }



    public Integer getCapacidade(){
       return raias * alunosPorRaia;
    }

    public Integer getVagasDisponiveis(){
        return getCapacidade() - inscritos;
    }

    public void reservarVaga(){
        if (getVagasDisponiveis() <= 0){
            throw new VagaIndisponivelException();
        }
        ++inscritos;
    }

    public Turno getTurno() {
        return turno;
    }

    public Integer getRaias() {
        return raias;
    }

    public Integer getAlunosPorRaia() {
        return alunosPorRaia;
    }

    public Integer getInscritos() {
        return inscritos;
    }
}
