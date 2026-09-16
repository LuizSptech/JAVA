package school.sptech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Luiz Fernando", "Luiz@gmail.com","119573145");


        Musica musica01 = new Musica("B. Jeans","POP","Thiller",200,"Michael Jackson");
        Musica musica02 = new Musica("Boate Azul", "sertanejo", "desconhecido",250,"Bruno e Marrone");
        Musica musica03 = new Musica("Preciso me encontrar", "MPB", "se encontrando", 300, "Cartola");

        Playlist playlist1 = new Playlist("As mais top 1CCOB", usuario1);
        playlist1.adicionarMusica(musica01);
        playlist1.adicionarMusica(musica02);
        playlist1.adicionarMusica(musica03);


        System.out.println(playlist1);
        System.out.println("Duração total " + playlist1.calcularDuracaoTotal());

    }
}