package school.sptech.ex01;

public class Onibus {
    private Integer qtdPassageiros;
    private Double valorPassagem;




    public void cobrarPassagem(BilheteUnico bilhete01){
        if (bilhete01 == null){
            return;
        }

        if (bilhete01.getBloqueado() == true){
            System.out.println("bilhete bloqueado");
            return;
        }
        if (bilhete01 == null || bilhete01.getSaldo() <= 0){
            System.out.println("Não há saldo suficiente para realizar operação");
            return;
        }
        if (bilhete01.getEstudante() == true){
            valorPassagem /= 2;
            if (bilhete01.getSaldo() < valorPassagem){
                return;
            }

            bilhete01.setSaldo(bilhete01.getSaldo() - valorPassagem);
            qtdPassageiros++;
            return;
        }
        if (bilhete01.getSaldo() < 5){
            return;
        }
        bilhete01.setSaldo(bilhete01.getSaldo() - valorPassagem);
        qtdPassageiros++;

    }


    public void cobrarPassagem(Double dinheiro){
        if (dinheiro == null || dinheiro < valorPassagem){
            System.out.println("Dinheiro insuficiente para realizar a operação");
            return;
        }
        qtdPassageiros+=1;

    }


    public Double getValorPassagem() {
        return valorPassagem;
    }

    public Integer getQtdPassageiros() {
        return qtdPassageiros;
    }
}
