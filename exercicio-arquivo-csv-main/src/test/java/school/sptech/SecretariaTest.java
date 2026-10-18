package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.SecretariaFactory;
import school.sptech.provider.secretaria.ExportarAlunosProvider;
import school.sptech.provider.secretaria.ExportarPorSituacaoProvider;
import school.sptech.provider.secretaria.ExportarRelatorioProvider;
import school.sptech.provider.secretaria.ImportarAlunosProvider;
import school.sptech.provider.secretaria.ProcessarRecuperacaoProvider;
import school.sptech.util.AlunoUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Secretaria")
public class SecretariaTest {

  static List<String> descrever(List<?> alunos) throws ReflectiveOperationException {
    List<String> descricoes = new ArrayList<>();

    for (Object aluno : alunos) {
      descricoes.add(String.format("%s|%s|%s|%s|%s",
          AlunoUtil.campo(aluno, "ra"),
          AlunoUtil.campo(aluno, "nome"),
          AlunoUtil.campo(aluno, "nota1"),
          AlunoUtil.campo(aluno, "nota2"),
          AlunoUtil.campo(aluno, "frequencia")));
    }

    return descricoes;
  }

  static List<String> lerLinhas(Path arquivo) throws IOException {
    return Files.readAllLines(arquivo, StandardCharsets.UTF_8).stream()
        .filter(linha -> !linha.isBlank())
        .toList();
  }

  @Nested
  @DisplayName("1. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Secretaria> clazz = Secretaria.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("importarAlunos", String.class), "Deve possuir o método importarAlunos(String)"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("importarAlunos", InputStream.class), "Deve possuir o método importarAlunos(InputStream)"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("exportarAlunos", List.class, String.class), "Deve possuir o método exportarAlunos"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("exportarRelatorio", List.class, String.class), "Deve possuir o método exportarRelatorio"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("exportarPorSituacao", List.class, String.class), "Deve possuir o método exportarPorSituacao")
      );
    }
  }

  @Nested
  @DisplayName("2. Método - importarAlunos(String)")
  class MetodoImportarAlunosTest {

    @TempDir
    Path pasta;

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ImportarAlunosProvider.class)
    @DisplayName("1. Deve importar os alunos válidos do arquivo")
    void test1(String cenario, String conteudo, List<String> expected) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("importarAlunos", String.class);
      Path arquivo = pasta.resolve("alunos.csv");
      Files.writeString(arquivo, conteudo, StandardCharsets.UTF_8);

      List<?> resposta = (List<?>) metodo.invoke(SecretariaFactory.build(), arquivo.toString());

      assertIterableEquals(expected, descrever(resposta),
          "[" + cenario + "] importarAlunos deve ler os alunos válidos, ignorando cabeçalho, linhas em branco e linhas inválidas");
    }

    @Test
    @DisplayName("2. Arquivo inexistente deve retornar lista vazia")
    void test2() throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("importarAlunos", String.class);
      String caminho = pasta.resolve("nao-existe.csv").toString();

      Object resposta = assertDoesNotThrow(() -> metodo.invoke(SecretariaFactory.build(), caminho),
          "importarAlunos não deve lançar exceção quando o arquivo não existe");

      assertAll(
          () -> assertNotNull(resposta, "importarAlunos deve retornar uma lista vazia quando o arquivo não existe"),
          () -> assertTrue(((List<?>) resposta).isEmpty(), "importarAlunos deve retornar uma lista vazia quando o arquivo não existe")
      );
    }
  }

  @Nested
  @DisplayName("3. Método - importarAlunos(InputStream)")
  class MetodoImportarAlunosInputStreamTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ImportarAlunosProvider.class)
    @DisplayName("1. Deve importar os alunos válidos do InputStream")
    void test1(String cenario, String conteudo, List<String> expected) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("importarAlunos", InputStream.class);
      InputStream entrada = new ByteArrayInputStream(conteudo.getBytes(StandardCharsets.UTF_8));

      List<?> resposta = (List<?>) metodo.invoke(SecretariaFactory.build(), entrada);

      assertIterableEquals(expected, descrever(resposta),
          "[" + cenario + "] importarAlunos deve ler os alunos válidos do InputStream, ignorando cabeçalho, linhas em branco e linhas inválidas");
    }
  }

  @Nested
  @DisplayName("4. Método - exportarAlunos")
  class MetodoExportarAlunosTest {

    @TempDir
    Path pasta;

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExportarAlunosProvider.class)
    @DisplayName("1. Deve exportar o cabeçalho e um aluno por linha")
    void test1(String cenario, List<?> alunos, List<String> expected) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("exportarAlunos", List.class, String.class);
      Path arquivo = pasta.resolve("saida.csv");

      metodo.invoke(SecretariaFactory.build(), alunos, arquivo.toString());

      assertIterableEquals(expected, lerLinhas(arquivo), "[" + cenario + "] exportarAlunos deve gravar o cabeçalho e um aluno por linha");
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExportarAlunosProvider.class)
    @DisplayName("2. Alunos exportados devem poder ser importados novamente")
    void test2(String cenario, List<?> alunos, List<String> ignorado) throws Exception {
      Method gravar = Secretaria.class.getDeclaredMethod("exportarAlunos", List.class, String.class);
      Method ler = Secretaria.class.getDeclaredMethod("importarAlunos", String.class);
      Path arquivo = pasta.resolve("ida-e-volta.csv");
      Object secretaria = SecretariaFactory.build();

      gravar.invoke(secretaria, alunos, arquivo.toString());
      List<?> lidos = (List<?>) ler.invoke(secretaria, arquivo.toString());

      assertIterableEquals(AlunoUtil.ras(alunos), AlunoUtil.ras(lidos),
          "[" + cenario + "] Os alunos gravados por exportarAlunos devem poder ser lidos de volta por importarAlunos");
    }
  }

  @Nested
  @DisplayName("5. Método - exportarRelatorio")
  class MetodoExportarRelatorioTest {

    @TempDir
    Path pasta;

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExportarRelatorioProvider.class)
    @DisplayName("1. Deve exportar ra, nome, média e situação de cada aluno")
    void test1(String cenario, List<?> alunos, List<String> expected) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("exportarRelatorio", List.class, String.class);
      Path arquivo = pasta.resolve("relatorio.csv");

      metodo.invoke(SecretariaFactory.build(), alunos, arquivo.toString());

      assertIterableEquals(expected, lerLinhas(arquivo),
          "[" + cenario + "] exportarRelatorio deve gravar ra, nome, média e situação, na ordem da lista recebida");
    }
  }

  @Nested
  @DisplayName("6. Método - exportarPorSituacao")
  class MetodoExportarPorSituacaoTest {

    @TempDir
    Path pasta;

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ExportarPorSituacaoProvider.class)
    @DisplayName("1. Deve exportar um arquivo para cada situação")
    void test1(String cenario, List<?> alunos, List<String> aprovados, List<String> recuperacao, List<String> reprovados) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("exportarPorSituacao", List.class, String.class);

      metodo.invoke(SecretariaFactory.build(), alunos, pasta.toString());

      assertAll(
          () -> assertIterableEquals(aprovados, lerLinhas(pasta.resolve("aprovados.csv")), "[" + cenario + "] aprovados.csv incorreto"),
          () -> assertIterableEquals(recuperacao, lerLinhas(pasta.resolve("recuperacao.csv")), "[" + cenario + "] recuperacao.csv incorreto"),
          () -> assertIterableEquals(reprovados, lerLinhas(pasta.resolve("reprovados.csv")), "[" + cenario + "] reprovados.csv incorreto")
      );
    }
  }

  @Nested
  @DisplayName("7. Desafio - processarRecuperacao")
  class MetodoProcessarRecuperacaoTest {

    @TempDir
    Path pasta;

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(ProcessarRecuperacaoProvider.class)
    @DisplayName("1. Deve exportar a média final dos alunos em recuperação")
    void test1(String cenario, String conteudoAlunos, String conteudoNotas, List<String> expected) throws Exception {
      Method metodo = Secretaria.class.getDeclaredMethod("processarRecuperacao", InputStream.class, InputStream.class, String.class);
      InputStream alunos = new ByteArrayInputStream(conteudoAlunos.getBytes(StandardCharsets.UTF_8));
      InputStream notas = new ByteArrayInputStream(conteudoNotas.getBytes(StandardCharsets.UTF_8));
      Path saida = pasta.resolve("resultado-recuperacao.csv");

      metodo.invoke(SecretariaFactory.build(), alunos, notas, saida.toString());

      assertIterableEquals(expected, lerLinhas(saida),
          "[" + cenario + "] processarRecuperacao deve gravar a média final dos alunos em recuperação");
    }
  }
}
