package school.sptech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ContaCorrente conta01 = new ContaCorrente("Luiz","luiz@.com.br");
        ContaCorrente conta02 = new ContaCorrente("Bob","bob@.com.br","11 95321 9567");

        conta02.printarInformacoes();



        conta01.depositar(150.0);
        conta01.sacar(100.0);
        conta01.printarInformacoes();
}
}