package school.sptech.ex02;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex02.provider.AlunoCalcularNotaFinalProvider;
import school.sptech.ex02.util.TurmaFixtures;

@DisplayName("Aluno")
public class AlunoTest {

  @Nested
  @DisplayName("Aluno 01. Atributos")
  class AtributosTests {

    @Test
    @DisplayName("Validar Atributos")
    void cenario1() {
      Class<Aluno> clazz = Aluno.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("nome")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("idade")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("matricula")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("notaProva")),
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredField("notaAtividade"))
      );
    }
  }

  @Nested
  @DisplayName("Aluno 02. Método")
  class MetodosTests {

    @Test
    @DisplayName("Validar Métodos")
    void validarMetodos() {
      Class<Aluno> clazz = Aluno.class;

      Assertions.assertAll(
          () -> Assertions.assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularNotaFinal"))
      );
    }
  }

  @Nested
  @DisplayName("Aluno 03. Encapsulamento")
  class EncapsulamentoTests {

    @Test
    @DisplayName("Atributos Privados")
    void cenario1() {
      Class<Aluno> clazz = Aluno.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> Assertions.assertTrue(Modifier.isPrivate(campo.getModifiers())));

      Assertions.assertAll(validacoes);
    }

    @Test
    @DisplayName("Atributos devem possuir getters e setters")
    void cenario2() {
      Class<Aluno> clazz = Aluno.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoesGetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
            Method getter = clazz.getDeclaredMethod(getName);
            int getModifier = getter.getModifiers();
            Assertions.assertTrue(Modifier.isPublic(getModifier));
          });

      Stream<Executable> validacoesSetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
            Method setter = clazz.getDeclaredMethod(setName, campo.getType());
            int setModifier = setter.getModifiers();
            Assertions.assertTrue(Modifier.isPublic(setModifier));
          });

      Assertions.assertAll(Stream.concat(validacoesGetter, validacoesSetter));
    }

    @Test
    @DisplayName("Método calcularNotaFinal público")
    void cenario3() throws ReflectiveOperationException {
      Class<Aluno> clazz = Aluno.class;
      Method calcularNotaFinal = clazz.getDeclaredMethod("calcularNotaFinal");

      Assertions.assertTrue(Modifier.isPublic(calcularNotaFinal.getModifiers()));
    }
  }

  @Nested
  @DisplayName("Aluno 04. Método - calcularNotaFinal")
  class MetodoCalcularNotaFinalTests {

    @ParameterizedTest
    @ArgumentsSource(AlunoCalcularNotaFinalProvider.class)
    @DisplayName("Cenário 1")
    void cenario1(Double notaProva, Double notaAtividade, double esperado)
          throws ReflectiveOperationException {
      Class<Aluno> clazz = Aluno.class;
      Method metodo = clazz.getDeclaredMethod("calcularNotaFinal");

      Aluno aluno = TurmaFixtures.novoAluno("Ana Silva", 16, "M001", notaProva, notaAtividade);

      Double resposta = (Double) metodo.invoke(aluno);

      Assertions.assertAll(
            () -> Assertions.assertEquals(esperado, resposta, 0.01)
      );
    }
  }
}
