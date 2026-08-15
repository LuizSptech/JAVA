package school.sptech;

public class Troco {
    public static void main(String[] args) {
        Double valorPicanha = 120.60;
        Integer qtdPic = 4;
        Double QtdVz = 150.0;
        Double Total = valorPicanha * qtdPic;
        Double troco = Total - QtdVz;
        String mensagem = "Seu troco será de R$ %.2f, onde %.2f é o valor a ser devoldido ao cliente".formatted(troco,troco);

        System.out.println(mensagem);


    }
}
