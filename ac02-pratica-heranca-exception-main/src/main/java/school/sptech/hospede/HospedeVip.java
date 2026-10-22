package school.sptech.hospede;

import school.sptech.exceptions.DadoInvalidoException;

public class HospedeVip extends Hospede{

    private Double percentualDesconto;


    public HospedeVip(String nome, String cpf, Double percentualDesconto) throws DadoInvalidoException {
        super(nome, cpf);
        if (percentualDesconto == null || (percentualDesconto < 0.0 || percentualDesconto > 50.0)){
            throw new DadoInvalidoException();
        }


        this.percentualDesconto = percentualDesconto;
        limiteReservasAtivas = 10;

    }

    public Double calcularTotalGasto(){
        Double gas = 0.0;

        for (Reserva res : reservas){
            gas += res.calcularValor();
           Double teste =  res.calcularValor() * (percentualDesconto / 100.0);
           gas -= teste;
        }
        return gas;
    }


    public Double getPercentualDesconto() {
        return percentualDesconto;
    }
}
