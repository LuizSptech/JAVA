package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.TurmaFactory;
import school.sptech.provider.turma.AdicionarAlunoProvider;
import school.sptech.provider.turma.BuscarAlunoMaiorMediaProvider;
import school.sptech.provider.turma.CalcularMediaGeralProvider;
import school.sptech.provider.turma.FiltrarPorSituacaoProvider;
import school.sptech.util.AlunoUtil;
import school.sptech.util.EncapsulationUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Turma")
public class TurmaTest {

  static List<?> alunosDa(Object turma) throws ReflectiveOperationException {
    Field campo = Turma.class.getDeclaredField("alunos");
    campo.trySetAccessible();
    return (List<?>) campo.get(turma);
  }

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void test1() {
      Class<Turma> clazz = Turma.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("alunos"), "Deve possuir o atributo alunos")
      );
    }

    @Test
    @DisplayName("2. Lista de alunos deve ser inicializada vazia")
    void test2() {
      Constructor<?>[] construtores = Turma.class.getDeclaredConstructors();

      Stream<Executable> validacoes = Arrays.stream(construtores)
          .map((construtor) -> () -> {
            Object[] argumentos = Arrays.stream(construtor.getParameterTypes())
                .map((tipo) -> tipo.equals(String.class) ? "1ADS-A" : null)
                .toArray();

            construtor.trySetAccessible();
            Object obj = assertDoesNotThrow(() -> construtor.newInstance(argumentos),
                "Não foi possível criar a turma pelo construtor");

            List<?> alunos = alunosDa(obj);

            assertNotNull(alunos,
                "A lista de alunos deve ser inicializada (ex.: new ArrayList<>()) e não pode ser null");
            assertTrue(alunos.isEmpty(),
                "A lista de alunos deve ser inicializada vazia");
          });

      assertAll(validacoes);
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void test1() {
      Class<Turma> clazz = Turma.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("adicionarAluno", Aluno.class), "Deve possuir o método adicionarAluno"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMediaGeral"), "Deve possuir o método calcularMediaGeral"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarAlunoMaiorMedia"), "Deve possuir o método buscarAlunoMaiorMedia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("filtrarPorSituacao", String.class), "Deve possuir o método filtrarPorSituacao")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void test1() {
      Field[] campos = Turma.class.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Atributos devem possuir getters e setters")
    void test2() {
      EncapsulationUtil.validarGettersSetters(Turma.class);
    }
  }

  @Nested
  @DisplayName("4. Método - adicionarAluno")
  class MetodoAdicionarAlunoTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(AdicionarAlunoProvider.class)
    @DisplayName("1. Deve adicionar apenas alunos válidos e sem RA repetido")
    void test1(String cenario, List<?> iniciais, Object novo, List<String> expected) throws ReflectiveOperationException {
      Method metodo = Turma.class.getDeclaredMethod("adicionarAluno", Aluno.class);
      Object turma = TurmaFactory.build("1ADS-A", iniciais);

      metodo.invoke(turma, novo);

      assertIterableEquals(expected, AlunoUtil.ras(alunosDa(turma)),
          "[" + cenario + "] adicionarAluno deve ignorar alunos nulos, sem RA ou com RA já cadastrado");
    }
  }

  @Nested
  @DisplayName("5. Método - calcularMediaGeral")
  class MetodoCalcularMediaGeralTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(CalcularMediaGeralProvider.class)
    @DisplayName("1. Deve retornar a média das médias dos alunos")
    void test1(String cenario, List<?> alunos, Double expected) throws ReflectiveOperationException {
      Method metodo = Turma.class.getDeclaredMethod("calcularMediaGeral");
      Object turma = TurmaFactory.build("1ADS-A", alunos);

      Object resposta = metodo.invoke(turma);

      assertEquals(expected, (Double) resposta, 0.001, "[" + cenario + "] calcularMediaGeral deve retornar a média das médias dos alunos");
    }
  }

  @Nested
  @DisplayName("6. Método - buscarAlunoMaiorMedia")
  class MetodoBuscarAlunoMaiorMediaTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(BuscarAlunoMaiorMediaProvider.class)
    @DisplayName("1. Deve retornar o aluno com a maior média")
    void test1(String cenario, List<?> alunos, String expectedRa) throws ReflectiveOperationException {
      Method metodo = Turma.class.getDeclaredMethod("buscarAlunoMaiorMedia");
      Object turma = TurmaFactory.build("1ADS-A", alunos);

      Object resposta = metodo.invoke(turma);

      assertEquals(expectedRa, AlunoUtil.ra(resposta), "[" + cenario + "] buscarAlunoMaiorMedia deve retornar o aluno com a maior média");
    }
  }

  @Nested
  @DisplayName("7. Método - filtrarPorSituacao")
  class MetodoFiltrarPorSituacaoTest {

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(FiltrarPorSituacaoProvider.class)
    @DisplayName("1. Deve retornar apenas os alunos da situação informada")
    void test1(String cenario, List<?> alunos, String situacao, List<String> expected) throws ReflectiveOperationException {
      Method metodo = Turma.class.getDeclaredMethod("filtrarPorSituacao", String.class);
      Object turma = TurmaFactory.build("1ADS-A", alunos);

      List<?> resposta = (List<?>) metodo.invoke(turma, situacao);

      assertIterableEquals(expected, AlunoUtil.ras(resposta),
          String.format("[%s] filtrarPorSituacao(\"%s\") deve retornar apenas os alunos nessa situação", cenario, situacao));
    }
  }
}
