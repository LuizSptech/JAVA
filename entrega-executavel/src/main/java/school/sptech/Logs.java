package school.sptech;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {

    LocalDateTime timestamp = LocalDateTime.now();
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String dataFormatada = timestamp.format(formato);
    String nivel;




    void Login(Boolean in){
        Thread login = new Thread(() -> {
            String mensagem;
            nivel = "INFO";
            mensagem = "[%s] %s Fazendo consulta no banco de dados...";
            System.out.println(mensagem.formatted(nivel,dataFormatada));

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                nivel = "ERROR";
                System.out.println("[%s] %s Consulta interrompida".formatted(nivel,dataFormatada));
            }
            if (in == false) {
                nivel = "ERRO";
                mensagem = "[%s] %s erro de autentificação um dos campos Email, Senha e Codigo de ativação retorna null".formatted(nivel, dataFormatada);
                System.out.println(mensagem);
                return;

            }
            nivel = "INFO";
            mensagem = "[%s] %s login/cadastro realizado com sucesso! redirecionando...".formatted(nivel, dataFormatada);
            System.out.println(mensagem);
        } );
        login.start();

    }


    void capturaDados(Boolean captura){
        Thread dados = new Thread(() -> {
        String saida;


        nivel = "INFO";
        String take = "[%s] %s Começando a captura do uso dos componentes...";
        System.out.println(take.formatted(nivel,dataFormatada));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            nivel = "FATAL";
            System.out.println("[%s] %s Captura interrompida".formatted(nivel,dataFormatada));
        }
            if (captura == false){
                nivel =  "DEBUG";
                saida = "[%s] %s Variavel 'NucleoCPU8' is undefined...";
                System.out.println(saida.formatted(nivel,dataFormatada));

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e){
                    nivel = "FATAL";
                    System.out.println("[%s] %s Captura interrompida".formatted(nivel,dataFormatada));
                }

                nivel = "FATAL";
                saida = "[%s] %s Erro ao capturar dados do servidor 'PADR-S501'...";
                System.out.println(saida.formatted(nivel,dataFormatada));
                    return;
                 }
            nivel = "INFO";
            saida = "[%s] %s Dados de servidor 'PADR-S501' capturados com sucesso...";
            System.out.println(saida.formatted(nivel,dataFormatada));
        });
        dados.start();
    }

    void tratamento(Boolean trat){
        Thread trato = new Thread(() -> {
        String out;
        nivel = "INFO";
        String begin = "[%s] %s começando tratamento do arquivo 'relatorio_servidor.csv'";
            System.out.println(begin.formatted(nivel,dataFormatada));


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            nivel = "FATAL";
            System.out.println("[%s] %s Captura interrompida".formatted(nivel,dataFormatada));
        }

            if (trat == false ){
                nivel = "FATAL";
                 out = "[%s] %s não e possivel ler arquivo 'relatorio_servidor.csv'";
                System.out.println(out.formatted(nivel,dataFormatada));
                return;

            }
            nivel = "INFO";
            out = "[%s] %s Filtrando dados do pedagio: 'Imigrantes KM45 '";
        System.out.println(out.formatted(nivel,dataFormatada));


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            nivel = "FATAL";
            System.out.println("[%s] %s Captura interrompida".formatted(nivel,dataFormatada));
        }


        System.out.println("[%s] %s Dados filtrados com sucesso encaminhando para dashboard...".formatted(nivel,dataFormatada));
        });
    trato.start();
    }
}
