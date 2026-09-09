package teste;

import school.sptech.ContaCorrente;

public class TesteEncapsulamento {
    public static void main(String[] args) {
        ContaCorrente conta02 = new ContaCorrente("bob","luiz@gmail");

        conta02.printarInformacoes();
    }
}
