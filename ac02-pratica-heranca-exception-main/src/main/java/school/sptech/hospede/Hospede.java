package school.sptech.hospede;


import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.LimiteReservasExcedidoException;

import java.util.ArrayList;
import java.util.List;

public class Hospede {
    protected String nome;
    protected String cpf;
    protected Integer limiteReservasAtivas;
    protected List<Reserva> reservas;


    public Hospede(String nome, String cpf) throws DadoInvalidoException {

        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException();
        }
        if (cpf == null || cpf.isBlank()){
            throw new DadoInvalidoException();
        }
        this.nome = nome;
        this.cpf = cpf;
        this.limiteReservasAtivas = 3;
        this.reservas = new ArrayList<>();
    }



    public Integer contarReservasAtivas(){
        Integer contador = 0;
        for (Reserva vagas : reservas){
            if (vagas.getAtiva() == true){
                contador++;
            }
        }
        return contador;
    }


    public void adicionarReserva(Integer numeroQuarto, Integer qtdDiarias, Double valorDiaria) throws LimiteReservasExcedidoException {
        if (contarReservasAtivas() >= limiteReservasAtivas){
            throw new LimiteReservasExcedidoException();
        }
        Boolean ati = true;
        Reserva res = new Reserva(nome,cpf,numeroQuarto,qtdDiarias,valorDiaria);
        res.getAtiva();
        reservas.add(res);
    }


    public Double calcularTotalGasto(){
        if (reservas.isEmpty()){
            return 0.0;
        }
        Double din = 0.0;
        for (Reserva res : reservas){
           din += res.calcularValor();

        }
        return din;
    }



    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getLimiteReservasAtivas() {
        return limiteReservasAtivas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLimiteReservasAtivas(Integer limiteReservasAtivas) {
        this.limiteReservasAtivas = limiteReservasAtivas;
    }
}
