package school.sptech;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Secretaria {
    public List<Aluno> importarAlunos(String caminho){
        List<Aluno> alunos = new ArrayList<>();

        try (
                InputStream inputStream = new FileInputStream(caminho);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
        ) {
            String linha;
            reader.readLine();
            while((linha = reader.readLine())!= null){
                String[] colunas = linha.split(";");
                if (linha.isBlank() || colunas.length != 5){
                    continue;
                }

                String ra = colunas[0].trim();
                String nome = colunas[1].trim();
                Double nota1;
                Double nota2;
                Integer frequencia;

                try {
                    nota1 = Double.valueOf(colunas[2].trim().replaceAll(",", "."));
                    nota2 = Double.valueOf(colunas[3].trim().replaceAll(",", "."));
                    frequencia = Integer.valueOf(colunas[4].trim());
                } catch (NumberFormatException e){
                    continue;
                }
                Aluno aluno = new Aluno(ra, nome, nota1, nota2, frequencia);
                alunos.add(aluno);


            }
        }catch (IOException e){
            return alunos;
        }
        return alunos;
    }

    public List<Aluno> importarAlunos(InputStream entrada){
        List<Aluno> alunos = new ArrayList<>();

        try (
                InputStreamReader inputStreamReader = new InputStreamReader(entrada, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);
        ) {
            String linha;
            reader.readLine();
            while((linha = reader.readLine())!= null){
                String[] colunas = linha.split(";");
                if (linha.isBlank() || colunas.length != 5){
                    continue;
                }
                for (int i = 0; i < colunas.length; i++) {
                    colunas[i] = colunas[i].trim();
                }
                String ra = colunas[0];
                String nome = colunas[1];
                Double nota1;
                Double nota2;
                Integer frequencia;

                try {
                    nota1 = Double.valueOf(colunas[2].replaceAll(",", "."));
                    nota2 = Double.valueOf(colunas[3].replaceAll(",", "."));
                    frequencia = Integer.valueOf(colunas[4]);
                } catch (NumberFormatException e){
                    continue;
                }
                Aluno aluno = new Aluno(ra, nome, nota1, nota2, frequencia);
                alunos.add(aluno);


            }
        }catch (IOException e){
            return alunos;
        }
        return alunos;
    }


    public void exportarAlunos(List<Aluno> alunos, String caminho){
        try (
                FileOutputStream outputStream = new FileOutputStream(caminho);
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(streamWriter)
        ) {

            String cabecalho = "ra;nome;nota1;nota2;frequencia";
            writer.write(cabecalho);
            writer.newLine();

            for (Aluno aluno : alunos) {
                String linha = String.format(Locale.US,"%s;%s;%.2f;%.2f;%d",
                                aluno.getRa(),
                                aluno.getNome(),
                                aluno.getNota1(),
                                aluno.getNota2(),
                                aluno.getFrequencia());
                writer.write(linha);
                writer.newLine();
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo");
        }
    }

    public void exportarRelatorio(List<Aluno> alunos, String caminho){
        try (
                FileOutputStream outputStream = new FileOutputStream(caminho);
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(streamWriter)
        ) {

            String cabecalho = "ra;nome;media;situacao";
            writer.write(cabecalho);
            writer.newLine();

            for (Aluno aluno : alunos) {
                String linha = String.format(Locale.US,"%s;%s;%.2f;%s",
                        aluno.getRa(),
                        aluno.getNome(),
                        aluno.calcularMedia(),
                        aluno.calcularSituacao());
                writer.write(linha);
                writer.newLine();
            }
            //outputStream.close(); //se não fechar é uma péssima prática
        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo");
        }
    }

    public void exportarPorSituacao(List<Aluno> alunos, String diretorio){
        List<Aluno> aprovados = new ArrayList<>();
        List<Aluno> recuperacao = new ArrayList<>();
        List<Aluno> reprovados = new ArrayList<>();

        for (Aluno aluno : alunos) {
            if (aluno.calcularSituacao().equalsIgnoreCase("APROVADO")){
                aprovados.add(aluno);
            }
            if (aluno.calcularSituacao().equalsIgnoreCase("recuperacao")){
                recuperacao.add(aluno);
            }
            if (aluno.calcularSituacao().equalsIgnoreCase("reprovado")){
                reprovados.add(aluno);
            }
        }

        exportarRelatorio(aprovados, diretorio + "/aprovados.csv");
        exportarRelatorio(recuperacao, diretorio + "/recuperacao.csv");
        exportarRelatorio(reprovados, diretorio + "/reprovados.csv");
    }

    public void processarRecuperacao(InputStream alunos, InputStream notas, String caminhoSaida){
        List<Aluno> alunosList = importarAlunos(alunos);
        Turma turma = new Turma("Turma B");

        for (Aluno aluno : alunosList) {
            turma.adicionarAluno(aluno);
        }

        List<Aluno> recuperacao = turma.filtrarPorSituacao("RECUPERACAO");

        List<String> raList = new ArrayList<>();
        List<Double> notaRecuperacaoList = new ArrayList<>();


        try (
                InputStreamReader inputStreamReader = new InputStreamReader(notas, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(inputStreamReader);

                FileOutputStream outputStream = new FileOutputStream(caminhoSaida);
                OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
                BufferedWriter writer = new BufferedWriter(streamWriter)
        ) {
            String linhaLer;
            reader.readLine();
            while((linhaLer = reader.readLine())!= null) {
                String[] colunas = linhaLer.split(";");
                if (linhaLer.isBlank() || colunas.length != 2) {
                    continue;
                }

                String ra = colunas[0].trim();
                Double notaRecuperacao;
                Boolean jaTemRa = false;

                try {
                    notaRecuperacao = Double.valueOf(colunas[1].trim().replaceAll(",", "."));
                } catch (NumberFormatException e) {
                    continue;
                }

                for (String outro : raList) {
                    if (outro.equals(ra)) {
                        jaTemRa = true;
                        break;
                    }
                }
                if (jaTemRa) {
                    continue;
                }

                raList.add(ra);
                notaRecuperacaoList.add(notaRecuperacao);


            }

            String cabecalho = "ra;nome;media;notaRecuperacao;mediaFinal;situacao";
            writer.write(cabecalho);
            writer.newLine();

            for (Aluno aluno : recuperacao) {

                Double notaRecuperacaoEscrever = 0.;
                String situacao = "APROVADO";


                for (int i = 0; i < raList.size(); i++) {
                    if (aluno.getRa().equals(raList.get(i))){
                        notaRecuperacaoEscrever = notaRecuperacaoList.get(i);

                    }
                }
                Double mediaFinal = (aluno.calcularMedia() + notaRecuperacaoEscrever) / 2;

                if (mediaFinal < 6){
                    situacao = "REPROVADO";
                }



                String linhaEscrever = String.format(Locale.US,"%s;%s;%.2f;%.2f;%.2f;%s",
                        aluno.getRa(),
                        aluno.getNome(),
                        aluno.calcularMedia(),
                        notaRecuperacaoEscrever,
                        mediaFinal,
                        situacao);
                writer.write(linhaEscrever);
                writer.newLine();
            }
        }catch (IOException e){
            return;
        }


    }

}
