package school.sptech;


// InputStream -> Fluxo de entrada de bytes
// FileInputStream -> ler arquivos
// ByteArrayInputStream -< ler em memoria


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Leitor {
    public void ler(String nomeArquivo){
        try (
                InputStream inputStream = new FileInputStream(nomeArquivo);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                ){
            String linha;
            while ((linha = reader.readLine()) != null){
                System.out.println(linha);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");;
        }
    }






    public List<Livro> importarLivros(String nomeArquivo){
        List<Livro> livros = new ArrayList<>();
        try (
                InputStream inputStream = new FileInputStream(nomeArquivo);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
        ){
            String linha;
            reader.readLine(); //pular cabeçalho
            while ((linha = reader.readLine()) != null){
                String[] colunas = linha.split(";");
                if (linha.isBlank() || colunas.length !=8){
                   continue;
                }


                if (colunas[5].contains(",")){
                    colunas[5].replace(",", ";");

                }
                    String isbn = colunas[0];
                    String titulo = colunas[2];
                    Double precoMedio = Double.valueOf(colunas[5]);
                    Livro livro = new Livro(isbn,titulo,precoMedio);
                    livros.add(livro);

                }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");;
        }
        return livros;
    }
}
