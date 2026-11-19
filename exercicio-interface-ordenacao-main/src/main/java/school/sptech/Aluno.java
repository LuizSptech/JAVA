package school.sptech;

import school.sptech.exception.DadoInvalidoException;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
 private String nome;
 private Plano plano;
 private List<Agendavel> agendamentos;

    public Aluno(String nome) {
        this.nome = nome;
        this.plano = null;
        this.agendamentos = new ArrayList<>();
    }


    public void matricular(Plano plano){
        if (plano == null){
            throw new DadoInvalidoException();
        }
        this.plano = plano;
    }

    public void adicionarAgendamento(Agendavel agendavel){
        if (agendavel == null){
            throw new DadoInvalidoException();
        }
        for (Agendavel agendamento : agendamentos) {
            if (agendamento.equals(agendavel)){
                throw new DadoInvalidoException();
            }
        }
        agendamentos.add(agendavel);
    }

    public String getNome() {
        return nome;
    }

    public Plano getPlano() {
        return plano;
    }

    public List<Agendavel> getAgendamentos() {
        return agendamentos;
    }
}
