package school.sptech;

import java.util.concurrent.ThreadLocalRandom;

public class ContaCorrente {


    //Encapsulamento: tecnica para proteger nossa regra
    // de negocio
    //Modificadores de acesso
    // default -> apenas o pacote pode acessar (padrão)
    // private -> apenas a propria classe pode acessar
    // public -> pode acessar de qualquer lugar
    // protected -> apenas o pacote ou subclasses


    private String numero;
    private String titular;
    private Double saldo;
    private String telefone;
    private String email;

    //Construtor: "método especial" responsável
    //Por criar novos objetos
    // Ele tem que ter o nome da classe
    //Quando não criamos um, o Java cria um "vazio"
    //por baixo dos panos
    //this palavra-chave que significa " dessa classe" / referencia a essa instancia
    public ContaCorrente(String titular, String email){
        this.titular = titular;
        this.email = email;
        saldo = 0.0;
        telefone = null;
        this.numero = ThreadLocalRandom.current().nextInt(1000,2001) + "";

    }


    //Sobrecarga no construtor
    public ContaCorrente(String titular, String email,String telefone){
       this(titular, email);
        this.telefone = telefone;


    }


    public void depositar(Double valor){
        if (valor == null || valor <= 0){
            System.out.println("Valor invalido para deposito");
            return;
        }
        saldo += valor;
        System.out.println("Deposito realizado com sucesso");
    }


    public void sacar(Double valor){
        if (valor == null || valor <= 0){
            System.out.println("Valor invalido para saque");
            return;
        }

        if (valor > saldo){
            System.out.println("Saldo insuficiente!");
            return;
        }
        saldo -= valor;
        System.out.println("Saque realizado com sucesso");

    }


    public void printarInformacoes(){
        String mensagem = """
                =========================
                Titular: %s
                Numero: %s
                Saldo: %.2f
                Email: %s
                Telefone: %s
                =========================
                """.formatted(titular,numero,saldo,email,telefone);
        System.out.println(mensagem);
    }



}
