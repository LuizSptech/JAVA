package school.sptech;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Hora {
    public static void main(String[] args) {
        //now() cria uma nova instancia de LocalDate, pegando a data atual do serfidor no padrão "ano/mes/dia|T|hora/minuto/segundo/nano"

        LocalDateTime dataHora = LocalDateTime.now();
        System.out.println(dataHora);

        //of() cria uma nova instancia de LocalDate com os parametros na seguinte ordem: ano/mes/dia/hora/minuto e sendo opcional o restante

        LocalDateTime when = LocalDateTime.of(2023,4,20,4,30);
        System.out.println(when);

        //parse() transforma uma string numa instancia de LocalDate usando o formato FM "repetido anteriormente"
        LocalDateTime txtsec = LocalDateTime.parse("2008-01-09T14:30");

        System.out.println(txtsec);


    //Metodos de manipulação se repetem agora permitindo adiconar e diminuir horas/minutos/nanosegundos

    }
}
