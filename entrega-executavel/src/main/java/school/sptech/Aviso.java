package school.sptech;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Aviso {

    LocalDateTime timestamp = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String dataFormatada = timestamp.format(formato);
    String nivel;




    String Login(String email, String senha, String codigo){
        String mensagem;
        if (email == null || senha == null || codigo == null){
                nivel = "ERRO";
             mensagem = "[%s] %s erro de autentificação um dos campos Email, Senha e Codigo de ativação retorna null".formatted(nivel,dataFormatada);
            System.out.println(mensagem);
            return mensagem;

        }
        nivel = "INFO";
        mensagem = "[%s] %s login/cadastro realizado com sucesso! redirecionando...".formatted(nivel,dataFormatada);
        System.out.println(mensagem);
        return mensagem;
    }


    String capturaDados(Boolean captura){
        nivel = "INFO";
        String take = "[%s] %s Começando a captura do uso dos componentes...";
        String saida;
            if (captura == false){
                nivel =  "DEBUG";
                System.out.println(take.formatted(nivel,dataFormatada));
                saida = "[%s] %s Variavel 'NucleoCPU8' is undefined";
                System.out.println(saida.formatted(nivel,dataFormatada));
                return saida;
            }
            nivel = "FATAL";
            saida = "[%s] %s Erro ao capturar dados do servidor 'PADR-S501'";
        System.out.println(saida.formatted(nivel,dataFormatada));



        return saida;
    }

    String Tratamento(Boolean trat){
        
        return "hi";
    }
}
