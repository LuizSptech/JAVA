package school.sptech;

public class ContaCorrente {
    private String titular;
    private Double saldo;
    private String numero;

    //atributo de classe - compartilham o mesmo valor
    //Todos os objetos e a classe contaCorrente tem o mesmo valor de contador
    // Não posso acessar algo da instancia dentro de algo da clase
    private static Integer contador = 0;
    // Constante no java não e apenas "final"
    // É sim, static + "final"
    //padrão de nomenclatura SCREAMING_CASE
    private static final Double VALOR_LIMITE_SAQUE = 1_000_000.0;

    public ContaCorrente(String titular,String numero) {
        this.titular = titular;
        this.saldo = 0.0;
        this.numero = numero;
        contador++;
    }


    public void depositar (Double valor){
        if (valor == null || valor <= 0.0){
            throw new IllegalArgumentException("Valor invalido");
        }
        saldo += valor;
        System.out.println("Deposito realizado com sucesso");
    }

    public void sacar (Double valor){
        if (valor == null || valor <= 0.0){
            throw new IllegalArgumentException("Valor invalido");
        }
        if(valor > VALOR_LIMITE_SAQUE){
            throw new IllegalArgumentException("Valor acima do limite");
        }
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        saldo -= valor;
        System.out.println("saque realizado com sucesso");
    }


    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public static Integer getContador(){
        return contador;
    }
    @Override
    public String toString() {
        return "ContaCorrente{" +
                "titular='" + titular + '\'' +
                ", saldo=" + saldo +
                ", numero='" + numero + '\'' +
                '}';
    }
}
