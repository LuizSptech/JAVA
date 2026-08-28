package school.sptech;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatacao {
    public static void main(String[] args) {
        // a forma de escrever varia de como você escreve a formatação "d/MMMM/yyyy" => 3/agosto/2026
        // "dd/MM/yy" => 03/ago/26

        LocalDate data = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MMMM/yyyy");
        String dataFormatada = data.format(formatter);
        System.out.println(dataFormatada);
    }
}
