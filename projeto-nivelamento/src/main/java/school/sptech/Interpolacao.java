package school.sptech;

public class Interpolacao {
    public static void main(String[] args) {
        //let mensagem = `Meu nome é {nome}`;

        String nome = "Luiz";
        Integer idade = 22;
        Double altura = 1.80;
        String mensagem = "Meu nome é: %s e tenho %d anos. Minha altura é: %.2f de altura  Teste: 25%%".formatted(nome,idade, altura);
        String mensagem2 = String.format("Meu nome é: %s e tenho %d anos. Minha altura é: %.2f de altura", nome, idade, altura);
        System.out.println(mensagem);
        System.out.println(mensagem2);

        String texto = """
                    Receba meu nome é %s
         Receba eu tenho %f de altura
                        Entregue espaço
                    #C# >>>> Java""".formatted(nome,altura);
        System.out.println(texto);
/*souf -> print formatado*/
        System.out.printf("My name is %s", nome);
    }
}

// %.2f para controlar numero de casas decimais

  /*ESPECIFICADORES*

        %d - int
        %f - double
        %s - String
        %c - char
        %b - boolean

        */