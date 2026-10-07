package school.sptech;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Produto produto1 = new Produto("tv", 2000.0, "eletronico", 10) ;
        Produto produto2 = new Produto("celular", 3000.0, "eletronico", 10) ;
        Produto produto3 = new Produto("melao", 10.0, "alimento", 20) ;
        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);
        produtos.add(produto3);


        Escritor escritor = new Escritor();

        escritor.escrever("teste.csv", produtos);
        System.out.println("Arquivo criado com sucesso");
        }
    }
