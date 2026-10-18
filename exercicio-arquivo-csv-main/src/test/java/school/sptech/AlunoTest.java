package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.aluno.CalcularMediaProvider;
import school.sptech.provider.aluno.CalcularSituacaoProvider;
import school.sptech.util.EncapsulationUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Aluno")
public class AlunoTest {

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Aluno> clazz = Aluno.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("ra"), "Deve possuir o atributo ra"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nota1"), "Deve possuir o atributo nota1"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nota2"), "Deve possuir o atributo nota2"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("frequencia"), "Deve possuir o atributo frequencia")
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Aluno> clazz = Aluno.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMedia"), "Deve possuir o método calcularMedia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularSituacao"), "Deve possuir o método calcularSituacao")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Field[] campos = Aluno.class.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Atributos devem possuir getters e setters")
    void test2() {
      EncapsulationUtil.validarGettersSetters(Aluno.class);
    }
  }

  @Nested
  @DisplayName("4. Método - calcularMedia")
  class MetodoCalcularMediaTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(CalcularMediaProvider.class)
    @DisplayName("1. Deve retornar a média das duas notas")
    void test1(String cenario, Double nota1, Double nota2, Double expected) throws ReflectiveOperationException {
      Method metodo = Aluno.class.getDeclaredMethod("calcularMedia");
      Object aluno = AlunoFactory.build("001", "Ana", nota1, nota2, 90);

      Object resposta = metodo.invoke(aluno);

      assertEquals(expected, (Double) resposta, 0.001, "[" + cenario + "] calcularMedia deve retornar a média das duas notas");
    }
  }

  @Nested
  @DisplayName("5. Método - calcularSituacao")
  class MetodoCalcularSituacaoTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(CalcularSituacaoProvider.class)
    @DisplayName("1. Deve retornar a situação conforme a média e a frequência")
    void test1(String cenario, Double nota1, Double nota2, Integer frequencia, String expected) throws ReflectiveOperationException {
      Method metodo = Aluno.class.getDeclaredMethod("calcularSituacao");
      Object aluno = AlunoFactory.build("001", "Ana", nota1, nota2, frequencia);

      Object resposta = metodo.invoke(aluno);

      assertEquals(expected, resposta, String.format(
          "[%s] Notas %.2f e %.2f com frequência %d%% deveriam resultar em %s", cenario, nota1, nota2, frequencia, expected));
    }
  }
}
