package school.sptech;

import javax.swing.plaf.LabelUI;

public class Main {

    public static void main(String[] args) {

        Jogador luiz = new Jogador();

        luiz.nome = "Luiz";
        luiz.saldo = null;


        luiz.validarJogador();
    }
}
