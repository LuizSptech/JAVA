package school.sptech.ex05;

public class Adivinhador {
    private Integer tentativas;

    public Integer getTentativas() {
        return tentativas;
    }

    public void adivinharNumero(Integer numero) {
        tentativas = 0;
        int sorteado;

        do {
            tentativas++;
            sorteado = (int) (Math.random() * 51);
            System.out.println("Tentativa " + tentativas + ": " + sorteado);
        } while (sorteado != numero);

        System.out.println("O número " + numero + " foi sorteado após " + tentativas + " tentativas.");
    }
}
