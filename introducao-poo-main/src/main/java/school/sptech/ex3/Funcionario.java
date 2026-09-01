package school.sptech.ex3;

public class Funcionario {
    String nome;
    String cargo;
    Double salario;


    Double reajustarSalario(Integer reajuste){
        Double ajuste = salario * (reajuste/100.0);
        System.out.println(ajuste);
        salario += ajuste;
        System.out.println(salario);
        return salario;
    }

    Double calcularValorHora(){
        Double valor_Hora = salario / 220.0;
        return valor_Hora;
    }

    Double calcularHoraExtra(Integer horasTrampada, Integer norturno){

        Double hora_normal = salario / 220.0;
        Double valor_tr = hora_normal * (norturno/100.0);
        Double fin = hora_normal + valor_tr;
        Double valor_Final = fin * horasTrampada;

        return valor_Final;

    }

    Double calcularBonificacaoAnual(){
        Integer porcent;
        if (salario <= 2500.0){
            porcent = 15;
        } else if (salario <= 6000.0) {
            porcent = 10;
        }else {porcent = 5;}

        Double bonus = salario * (porcent/100.0);

        return bonus;
    }


}
