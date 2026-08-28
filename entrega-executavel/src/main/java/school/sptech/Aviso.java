package school.sptech;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Aviso {

    LocalDateTime timestamp = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
    String dataFormatada = timestamp.format(formato);
    String nivel;



    String login_feito(String email, String senha, String codigo){
        String mensagem;
        if (email == null || senha == null || codigo == null){

             mensagem = "[%s] %s erro de autentificação por favor insira corretamente: Email Senha e Codigo de ativação".formatted(nivel,dataFormatada);
            System.out.println(mensagem);
            return mensagem;

        } mensagem = "[%s] %s login/cadastro realizado com sucesso! redirecionando...".formatted(nivel,dataFormatada);
        System.out.println(mensagem);
        return mensagem;
    }






}
