package school.sptech;

public class Calorias {
    public static void main(String[] args) {
        Integer aquecimento = 30;
        Integer Exercicios = 60;
        Integer Musculacao = 120;

        Integer calAque = 12;
        Integer calExerc = 20;
        Integer calMusc = 25;

        Integer totalAque = aquecimento * calAque;
        Integer totalExerc = Exercicios * calExerc;
        Integer totalMusc = Musculacao * calMusc;
        Integer totalCal = totalAque + totalExerc + totalMusc;
        Integer totalMin = aquecimento + Exercicios + Musculacao;
        String mensagem = "Olá, Luiz. Você fez um total de %d minutos de exercícios e perdeu cerca de %d calorias.".formatted(totalMin, totalCal);

        System.out.println(mensagem);

    }
}
