package school.sptech;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Data {
    public static void main(String[] args) {
       //now() cria uma nova instancia de LocalDate, pegando a data atual do serfidor no padrão "ano/mes/dia"

        LocalDate data = LocalDate.now();
        System.out.println(data);

        //of() cria uma nova instancia de LocalDate com os parametros na seguinte ordem: ano/mes/dia

        LocalDate a_partir = LocalDate.of(2025,12,5);
        System.out.println(a_partir);

        //parse() transforma uma string numa instancia de LocalDate usando o formato FM "repetido anteriormente"
        LocalDate txt = LocalDate.parse("2008-01-09");

        //atTime() cria um LocalDatetime a partir de um LocalDate divindo eles por 1 T
        LocalDateTime dataHora = data.atTime(18,22);
        System.out.println(dataHora);

        //MÉTODOS DE MANIPULAÇÃO

        // 1- plus* adiciona um numero x de (dias/meses/semanas)
       LocalDate final_do_mes = data.plusDays(4);
        System.out.println(final_do_mes);

        LocalDate final_do_ano = data.plusMonths(4);
        System.out.println(final_do_ano);

        LocalDate final_da_sprint = data.plusWeeks(2);
        System.out.println(final_da_sprint);

        LocalDate ultimo_semestre = data.plusYears(3);
        System.out.println(ultimo_semestre);

        //2 - minus* remove um numero x de (dias/meses/semanas)

        LocalDate comeco_do_mes = data.minusDays(26);
        System.out.println(comeco_do_mes);

        LocalDate comeco_do_ano = data.minusMonths(7);
        System.out.println(comeco_do_ano);

        LocalDate comeco_da_sprint = data.minusWeeks(3);
        System.out.println(comeco_da_sprint);

        LocalDate ultimo_semestre_senai = data.minusYears(1);
        System.out.println(ultimo_semestre_senai);



        // -3 COMPARADORES

        //isAfter se a data é posterior a informada

        Boolean antes = final_do_mes.isAfter(comeco_do_mes);

        //isBefore se a data é depois a informada

        Boolean depois = ultimo_semestre_senai.isBefore(ultimo_semestre);

        //isEqual se a data é igual a informada

        Boolean igual = comeco_da_sprint.isEqual(final_da_sprint);

        System.out.println(antes);
        System.out.println(depois);
        System.out.println(igual);


    }
}