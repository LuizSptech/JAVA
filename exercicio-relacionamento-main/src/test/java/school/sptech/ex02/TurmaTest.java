package school.sptech.ex02;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex02.provider.TurmaBuscarAcimaDaMediaProvider;
import school.sptech.ex02.provider.TurmaBuscarAprovadosProvider;
import school.sptech.ex02.provider.TurmaBuscarMenorNotaProvider;
import school.sptech.ex02.provider.TurmaBuscarPorParteDoNomeProvider;
import school.sptech.ex02.provider.TurmaCalcularAmplitudeNotasProvider;
import school.sptech.ex02.provider.TurmaCalcularIdadeMediaProvider;
import school.sptech.ex02.provider.TurmaCalcularMediaTurmaProvider;
import school.sptech.ex02.provider.TurmaEncontrarAlunosComMesmaNotaProvider;
import school.sptech.ex02.provider.TurmaMatricularProvider;
import school.sptech.ex02.util.TurmaFixtures;

@DisplayName("Turma")
public class TurmaTest {

  @Nested
  @DisplayName("Turma 01. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<Turma> clazz = Turma.class;

      Assertions.assertAll(
            () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
            () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("alunos"))
      );
    }
  }

  @Nested
  @DisplayName("Turma 02. Método")
  class MetodosTests {

    @Test
    @DisplayName("Validar Métodos")
    void validarMetodos() {
      Class<Turma> clazz = Turma.class;

      Assertions.assertAll(
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("matricular", Aluno.class)),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("buscarPorParteDoNome", String.class)),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("buscarAprovados", Double.class)),
            () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMediaTurma")),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("buscarAcimaDaMedia")),
            () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarMenorNota")),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("calcularIdadeMedia")),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("calcularAmplitudeNotas")),
            () -> Assertions.assertDoesNotThrow(
                  () -> clazz.getDeclaredMethod("encontrarAlunosComMesmaNota"))
      );
    }
  }

  @Nested
  @DisplayName("Turma 03. Encapsulamento")
  class EncapsulamentoTests {

    @Test
    @DisplayName("Atributos Privados")
    void cenario1() {
      Class<Turma> clazz = Turma.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
            .map((campo) -> () -> Assertions.assertTrue(
                  Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Métodos públicos")
    void cenario2() throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;

      Method matricular = clazz.getDeclaredMethod("matricular", Aluno.class);
      Method buscarPorParteDoNome = clazz.getDeclaredMethod("buscarPorParteDoNome", String.class);
      Method buscarAprovados = clazz.getDeclaredMethod("buscarAprovados", Double.class);
      Method calcularMediaTurma = clazz.getDeclaredMethod("calcularMediaTurma");
      Method buscarAcimaDaMedia = clazz.getDeclaredMethod("buscarAcimaDaMedia");
      Method buscarMenorNota = clazz.getDeclaredMethod("buscarMenorNota");
      Method calcularIdadeMedia = clazz.getDeclaredMethod("calcularIdadeMedia");
      Method calcularAmplitudeNotas = clazz.getDeclaredMethod("calcularAmplitudeNotas");
      Method encontrarAlunosComMesmaNota = clazz.getDeclaredMethod("encontrarAlunosComMesmaNota");

      Assertions.assertAll(
            () -> Assertions.assertTrue(Modifier.isPublic(matricular.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(buscarPorParteDoNome.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(buscarAprovados.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(calcularMediaTurma.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(buscarAcimaDaMedia.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(buscarMenorNota.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(calcularIdadeMedia.getModifiers())),
            () -> Assertions.assertTrue(Modifier.isPublic(calcularAmplitudeNotas.getModifiers())),
            () -> Assertions.assertTrue(
                  Modifier.isPublic(encontrarAlunosComMesmaNota.getModifiers()))
      );
    }
  }

  @Nested
  @DisplayName("Turma 04. Método - matricular")
  class MetodoMatricularTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaMatricularProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> paraMatricular, List<Aluno> esperado)
          throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("matricular", Aluno.class);

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>());

      for (Aluno aluno : paraMatricular) {
        metodo.invoke(turma, aluno);
      }

      Field alunosField = clazz.getDeclaredField("alunos");
      alunosField.trySetAccessible();

      Assertions.assertAll(
            () -> Assertions.assertIterableEquals(esperado, (List<?>) alunosField.get(turma))
      );
    }
  }

  @Nested
  @DisplayName("Turma 05. Método - buscarPorParteDoNome")
  class MetodoBuscarPorParteDoNomeTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaBuscarPorParteDoNomeProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, String nome, List<Aluno> esperado)
          throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorParteDoNome", String.class);

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Object resposta = metodo.invoke(turma, nome);

      Assertions.assertAll(
            () -> Assertions.assertIterableEquals(esperado, (List<?>) resposta)
      );
    }
  }

  @Nested
  @DisplayName("Turma 06. Método - buscarAprovados")
  class MetodoBuscarAprovadosTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaBuscarAprovadosProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, Double notaMinima, List<Aluno> esperado)
          throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("buscarAprovados", Double.class);

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Object resposta = metodo.invoke(turma, notaMinima);

      Assertions.assertAll(
            () -> Assertions.assertIterableEquals(esperado, (List<?>) resposta)
      );
    }
  }

  @Nested
  @DisplayName("Turma 07. Método - calcularMediaTurma")
  class MetodoCalcularMediaTurmaTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaCalcularMediaTurmaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, double esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("calcularMediaTurma");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Double resposta = (Double) metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertEquals(esperado, resposta, 0.01)
      );
    }
  }

  @Nested
  @DisplayName("Turma 08. Método - buscarAcimaDaMedia")
  class MetodoBuscarAcimaDaMediaTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaBuscarAcimaDaMediaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, List<Aluno> esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("buscarAcimaDaMedia");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Object resposta = metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertIterableEquals(esperado, (List<?>) resposta)
      );
    }
  }

  @Nested
  @DisplayName("Turma 09. Método - buscarMenorNota")
  class MetodoBuscarMenorNotaTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaBuscarMenorNotaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, Aluno esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("buscarMenorNota");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Object resposta = metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertEquals(esperado, resposta)
      );
    }
  }

  @Nested
  @DisplayName("Turma 10. Método - calcularIdadeMedia")
  class MetodoCalcularIdadeMediaTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaCalcularIdadeMediaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, double esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("calcularIdadeMedia");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Double resposta = (Double) metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertEquals(esperado, resposta, 0.01)
      );
    }
  }

  @Nested
  @DisplayName("Turma 11. Método - calcularAmplitudeNotas")
  class MetodoCalcularAmplitudeNotasTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaCalcularAmplitudeNotasProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, double esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("calcularAmplitudeNotas");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Double resposta = (Double) metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertEquals(esperado, resposta, 0.01)
      );
    }
  }

  @Nested
  @DisplayName("Turma 12. Método - encontrarAlunosComMesmaNota")
  class MetodoEncontrarAlunosComMesmaNotaTests {

    @ParameterizedTest
    @ArgumentsSource(TurmaEncontrarAlunosComMesmaNotaProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(List<Aluno> alunos, List<Aluno> esperado) throws ReflectiveOperationException {
      Class<Turma> clazz = Turma.class;
      Method metodo = clazz.getDeclaredMethod("encontrarAlunosComMesmaNota");

      Turma turma = TurmaFixtures.novaTurma("1A", new ArrayList<>(alunos));

      Object resposta = metodo.invoke(turma);

      Assertions.assertAll(
            () -> Assertions.assertIterableEquals(esperado, (List<?>) resposta)
      );
    }
  }
}
