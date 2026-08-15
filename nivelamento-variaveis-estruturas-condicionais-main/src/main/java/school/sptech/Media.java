package school.sptech;

public class Media {
    public static void main(String[] args) {
        String nome = "Luiz";
        Double notaA = 8.5;
        Double notaB = 5.0;
        Double calculo = Math.round(notaA + notaB) / 2.0;
        String mensagem = "Olá, %s. Sua média foi de %.1f".formatted(nome,calculo);
        System.out.println(mensagem);

    }
}
