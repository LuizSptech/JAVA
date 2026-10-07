package school.sptech;

import java.util.List;

public class TesteLeitura {
    public static void main(String[] args) {
        Leitor leitor = new Leitor();

       // leitor.ler("base-dados-livro.csv");
        System.out.println("Arquivo lido");


        List<Livro> livrosLidos =
                leitor.importarLivros("base-dados-livro.csv");
        // livrosLidos.for
        for (Livro livrosLido : livrosLidos) {
            System.out.println(livrosLido);

        }
    }
}
