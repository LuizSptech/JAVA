package school.sptech;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Escritor {
    // OutputStream -> fluxo de saida de bytes
    // FileOutputStream -> para escrever arquivos
    // ByteArrayOutputStream -> escrever na memoria
    // PrintStream (extra) -> escreve a saida formatada
    // InputStram -> fluxo de entrada de bytes

    public void escrever(String nomeArquivo, List<Produto> produtos){
        // try-with-resourceres
        try (FileOutputStream outputStream = new FileOutputStream(nomeArquivo, false);
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);)
        {
            String cabecalho = "nome;preco;categoria;estoque";
            bufferedWriter.write(cabecalho);
            bufferedWriter.newLine();
            for (Produto produto : produtos) {
                String linha = "%s;%.2f;%s;%d".formatted(produto.getNome(),produto.getPreco(), produto.getCategoria(), produto.getEstoque());
                bufferedWriter.write(linha);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo");
        }
    }
    // CSV -> comma separated values



/*  bufferedWriter.write("nome;idade");
            // bufferWritter.newLine(); gera uma nova linha sem precisar do \n
            bufferedWriter.newLine();
            bufferedWriter.write("joao;20");
            bufferedWriter.newLine();
            bufferedWriter.write("luiz;18");
           // outputStream.close(); //  se não fechar e uma pessima pratica*/
}
