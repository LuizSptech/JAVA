package school.sptech;

public class CalculoRenda {
    public static void main(String[] args) {

        Integer Filhos1 = 6;
        Integer Filhos2 = 4;
        Integer Filhos3 = 2;

        Double valor1 = 25.12;
        Double valor2 = 15.88;
        Double valor3 = 12.44;


        Integer quantidadeCrias = Filhos1 + Filhos2 + Filhos3;
        Double valorCriancas = Filhos1 * valor1;
        Double valorAdolecen = Filhos2 * valor2;
        Double valorJovens = Filhos3 * valor3;

        Double valorTotal = valorCriancas + valorAdolecen + valorJovens;
        String mensagem = "Você tem um total de %d filhos e vai reber R$%.2f".formatted(quantidadeCrias, valorTotal);
        System.out.println(mensagem);

    }
}
