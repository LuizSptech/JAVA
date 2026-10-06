package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.especialistas.DesenvolvedorWeb;
import school.sptech.factory.ConsultoriaFactory;
import school.sptech.provider.consultoria.BuscarMenorSalarioProvider;
import school.sptech.provider.consultoria.BuscarPorNomeProvider;
import school.sptech.provider.consultoria.BuscarPorSalarioMaiorQueProvider;
import school.sptech.provider.consultoria.BuscarPorTecnologiaProvider;
import school.sptech.provider.consultoria.BuscarTipoComMaiorMediaSalarialProvider;
import school.sptech.provider.consultoria.CalcularMediaSalarialPorTipoProvider;
import school.sptech.provider.consultoria.CalcularMediaSalarialProvider;
import school.sptech.provider.consultoria.ContratarFullstackProvider;
import school.sptech.provider.consultoria.ContratarProvider;
import school.sptech.provider.consultoria.GetDesenvolvedoresWebProvider;
import school.sptech.provider.consultoria.GetTotalDesenvolvedoresMobileProvider;
import school.sptech.provider.consultoria.GetTotalSalariosPorTecnologiaProvider;
import school.sptech.provider.consultoria.GetTotalSalariosProvider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Consultoria")
public class ConsultoriaTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Consultoria> clazz = Consultoria.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "vagas", "desenvolvedores" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  @Nested
  @DisplayName("1. Atributos")
  class AtributosTest {

    @Test
    @DisplayName("1. Validar Atributos")
    void devePossuirOsAtributos() {
      Class<Consultoria> clazz = Consultoria.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"), "Deve possuir o atributo nome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("vagas"), "Deve possuir o atributo vagas"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("desenvolvedores"), "Deve possuir o atributo desenvolvedores")
      );
    }

    @Test
    @DisplayName("2. Lista de desenvolvedores deve ser inicializada vazia")
    void deveInicializarListaDeDesenvolvedoresVazia() {
      Class<Consultoria> clazz = Consultoria.class;
      Constructor<?>[] construtores = clazz.getDeclaredConstructors();

      Stream<Executable> validacoes = Arrays.stream(construtores)
          .map((construtor) -> () -> {
            Object[] argumentos = Arrays.stream(construtor.getParameterTypes())
                .map((tipo) -> {
                  if (tipo.equals(String.class)) {
                    return "Consultoria Teste";
                  }
                  if (tipo.equals(Integer.class)) {
                    return 10;
                  }
                  return null;
                })
                .toArray();

            construtor.trySetAccessible();
            Object obj = assertDoesNotThrow(() -> construtor.newInstance(argumentos),
                "Não foi possível criar a consultoria pelo construtor");

            List<?> desenvolvedores = (List<?>) campos().get("desenvolvedores").get(obj);

            assertNotNull(desenvolvedores,
                "A lista de desenvolvedores deve ser inicializada (ex.: new ArrayList<>()) e não pode ser null");
            assertTrue(desenvolvedores.isEmpty(),
                "A lista de desenvolvedores deve ser inicializada vazia");
          });

      assertAll(validacoes);
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void devePossuirOsMetodos() {
      Class<Consultoria> clazz = Consultoria.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("contratar", Desenvolvedor.class), "Deve possuir o método contratar"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class), "Deve possuir o método contratarFullstack"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTotalSalarios"), "Deve possuir o método getTotalSalarios"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("qtdDesenvolvedoresMobile"), "Deve possuir o método qtdDesenvolvedoresMobile"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getDesenvolvedoresWeb"), "Deve possuir o método getDesenvolvedoresWeb"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class), "Deve possuir o método buscarSalarioMaiorQue"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarMenorSalario"), "Deve possuir o método buscarMenorSalario"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMediaSalarial"), "Deve possuir o método calcularMediaSalarial"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorNome", String.class), "Deve possuir o método buscarPorNome"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularMediaSalarialPorTipo", String.class), "Deve possuir o método calcularMediaSalarialPorTipo"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarPorTecnologia", String.class), "Deve possuir o método buscarPorTecnologia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class), "Deve possuir o método getTotalSalarioPorTecnologia"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("buscarTipoComMaiorMediaSalarial"), "Deve possuir o método buscarTipoComMaiorMediaSalarial")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void atributosDevemSerPrivados() {
      Class<Consultoria> clazz = Consultoria.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void metodosDevemSerPublicos() {
      Class<Consultoria> clazz = Consultoria.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("contratar", Desenvolvedor.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("getTotalSalarios"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("qtdDesenvolvedoresMobile"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("getDesenvolvedoresWeb"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarMenorSalario"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("calcularMediaSalarial"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorNome", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("calcularMediaSalarialPorTipo", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarPorTecnologia", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("buscarTipoComMaiorMediaSalarial"));
      } catch (ReflectiveOperationException ignored) {}

      Stream<Executable> validacoes = metodos.stream()
          .map((metodo) -> () -> {
            assertTrue(Modifier.isPublic(metodo.getModifiers()), String.format("%s deve ser público", metodo.getName()));
          });

      assertAll(validacoes);
    }

    @Test
    @DisplayName("3. Atributos devem possuir getters e setters")
    void devePossuirGettersESetters() {
      Class<Consultoria> clazz = Consultoria.class;
      Field[] campos = Arrays.stream(clazz.getDeclaredFields())
          .filter(campo -> !campo.getName().equals("desenvolvedores"))
          .toArray(Field[]::new);

      Stream<Executable> validacoesGetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
            assertDoesNotThrow(() -> {
              Method getter = clazz.getDeclaredMethod(getName);
              int getModifier = getter.getModifiers();
              assertTrue(Modifier.isPublic(getModifier), String.format("Getter %s deve ser público", getName));
            }, String.format("Deve possuir o getter %s", getName));
          });

      Stream<Executable> validacoesSetter = Arrays.stream(campos)
          .map((campo) -> () -> {
            String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
            assertDoesNotThrow(() -> {
              Method setter = clazz.getDeclaredMethod(setName, campo.getType());
              int setModifier = setter.getModifiers();
              assertTrue(Modifier.isPublic(setModifier), String.format("Setter %s deve ser público", setName));
            }, String.format("Deve possuir o setter %s", setName));
          });

      assertAll(Stream.concat(validacoesGetter, validacoesSetter));
    }
  }

  @Nested
  @DisplayName("4. Método - contratar")
  class MetodoContratarTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(ContratarProvider.class)
    @DisplayName("Deve contratar somente se houver vagas")
    void deveContratarSomenteSeHouverVagas(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Object desenvolvedor, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("contratar", Desenvolvedor.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      metodo.invoke(obj, desenvolvedor);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("desenvolvedores").get(obj)),
          () -> assertEquals(vagas, campos().get("vagas").get(obj), "O atributo vagas não deve ser alterado ao contratar")
      );
    }
  }

  @Nested
  @DisplayName("5. Método - contratarFullstack")
  class MetodoContratarFullstackTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(ContratarFullstackProvider.class)
    @DisplayName("Deve contratar somente desenvolvedores fullstack e se houver vagas")
    void deveContratarSomenteFullstackComVagas(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Object desenvolvedor, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("contratarFullstack", DesenvolvedorWeb.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      metodo.invoke(obj, desenvolvedor);

      // Then
      assertAll(
          () -> assertIterableEquals(new ArrayList<>(expected), (List<?>) campos().get("desenvolvedores").get(obj)),
          () -> assertEquals(vagas, campos().get("vagas").get(obj), "O atributo vagas não deve ser alterado ao contratar")
      );
    }
  }

  @Nested
  @DisplayName("6. Método - getTotalSalarios")
  class MetodoGetTotalSalariosTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(GetTotalSalariosProvider.class)
    @DisplayName("Deve somar os salários de todos os desenvolvedores")
    void deveSomarOsSalarios(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getTotalSalarios");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve calcular o total de salários corretamente")
      );
    }
  }

  @Nested
  @DisplayName("7. Método - qtdDesenvolvedoresMobile")
  class MetodoGetDesenvolvedoresMobileTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(GetTotalDesenvolvedoresMobileProvider.class)
    @DisplayName("Deve contar os desenvolvedores mobile")
    void deveContarOsDesenvolvedoresMobile(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Integer expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("qtdDesenvolvedoresMobile");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, "Deve retornar o total de desenvolvedores mobile corretamente")
      );
    }
  }

  @Nested
  @DisplayName("8. Método - getDesenvolvedoresWeb")
  class MetodoGetDesenvolvedoresWebTest {

    @Test
    @DisplayName("1. Deve retornar uma lista de DesenvolvedorWeb")
    void deveRetornarListDeDesenvolvedorWeb() throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getDesenvolvedoresWeb");

      assertEquals("java.util.List<school.sptech.especialistas.DesenvolvedorWeb>",
          metodo.getGenericReturnType().getTypeName(),
          "getDesenvolvedoresWeb deve retornar List<DesenvolvedorWeb>");
    }

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(GetDesenvolvedoresWebProvider.class)
    @DisplayName("2. Deve retornar apenas os desenvolvedores web")
    void deveRetornarApenasOsDesenvolvedoresWeb(String descricao, String nome, Integer vagas, List<?> desenvolvedores, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getDesenvolvedoresWeb");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar apenas os desenvolvedores web da consultoria")
      );
    }
  }

  @Nested
  @DisplayName("9. Método - buscarPorSalarioMaiorIgualQue")
  class MetodoBuscarSalarioMaiorQueTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(BuscarPorSalarioMaiorQueProvider.class)
    @DisplayName("Deve buscar os desenvolvedores com salário maior ou igual ao valor")
    void deveBuscarSalariosMaioresOuIguais(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Double salario, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorSalarioMaiorIgualQue", Double.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, salario);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar os desenvolvedores com salário maior")
      );
    }
  }

  @Nested
  @DisplayName("10. Método - buscarMenorSalario")
  class MetodoBuscarMenorSalarioTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(BuscarMenorSalarioProvider.class)
    @DisplayName("Deve retornar o desenvolvedor com o menor salário")
    void deveRetornarDesenvolvedorComMenorSalario(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Object expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarMenorSalario");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, "Deve retornar o desenvolvedor com menor salário")
      );
    }
  }

  @Nested
  @DisplayName("11. Método - calcularMediaSalarial")
  class MetodoCalcularMediaSalarialTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(CalcularMediaSalarialProvider.class)
    @DisplayName("Deve calcular a média salarial")
    void deveCalcularMediaSalarial(String descricao, String nome, Integer vagas, List<?> desenvolvedores, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("calcularMediaSalarial");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve calcular a média salarial corretamente")
      );
    }
  }

  @Nested
  @DisplayName("12. Método - buscarPorNome")
  class MetodoBuscarPorNomeTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(BuscarPorNomeProvider.class)
    @DisplayName("Deve buscar os desenvolvedores por parte do nome")
    void deveBuscarPorParteDoNome(String descricao, String nome, Integer vagas, List<?> desenvolvedores, String parteNome, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorNome", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, parteNome);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar os desenvolvedores cujo nome contém o texto informado")
      );
    }
  }

  @Nested
  @DisplayName("13. Método - calcularMediaSalarialPorTipo")
  class MetodoCalcularMediaSalarialPorTipoTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(CalcularMediaSalarialPorTipoProvider.class)
    @DisplayName("Deve calcular a média salarial do tipo informado")
    void deveCalcularMediaSalarialDoTipo(String descricao, String nome, Integer vagas, List<?> desenvolvedores, String tipo, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("calcularMediaSalarialPorTipo", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj, tipo);

      // Then
      if (expected == null) {
        assertNull(resposta, "Deve retornar null para um tipo inválido");
        return;
      }

      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve calcular a média salarial do tipo corretamente")
      );
    }
  }

  @Nested
  @DisplayName("14. Método - buscarPorTecnologia")
  class MetodoBuscarPorTecnologiaTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(BuscarPorTecnologiaProvider.class)
    @DisplayName("Deve buscar os desenvolvedores pela tecnologia")
    void deveBuscarPorTecnologia(String descricao, String nome, Integer vagas, List<?> desenvolvedores, String tecnologia, List<?> expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarPorTecnologia", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      List<?> resposta = (List<?>) metodo.invoke(obj, tecnologia);

      // Then
      assertAll(
          () -> assertIterableEquals(expected, resposta, "Deve retornar os desenvolvedores com a tecnologia especificada")
      );
    }
  }

  @Nested
  @DisplayName("15. Método - getTotalSalariosPorTecnologia")
  class MetodoGetTotalSalariosPorTecnologiaTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(GetTotalSalariosPorTecnologiaProvider.class)
    @DisplayName("Deve somar os salários dos desenvolvedores da tecnologia")
    void deveSomarSalariosPorTecnologia(String descricao, String nome, Integer vagas, List<?> desenvolvedores, String tecnologia, Double expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("getTotalSalariosPorTecnologia", String.class);

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj, tecnologia);

      // Then
      assertAll(
          () -> assertEquals(expected, (Double) resposta, 0.01, "Deve retornar o total de salário dos desenvolvedores com a tecnologia especificada")
      );
    }
  }

  @Nested
  @DisplayName("16. Método - buscarTipoComMaiorMediaSalarial")
  class MetodoBuscarTipoComMaiorMediaSalarialTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(BuscarTipoComMaiorMediaSalarialProvider.class)
    @DisplayName("Deve retornar o tipo com a maior média salarial")
    void deveRetornarTipoComMaiorMedia(String descricao, String nome, Integer vagas, List<?> desenvolvedores, String expected) throws ReflectiveOperationException {
      Class<Consultoria> clazz = Consultoria.class;
      Method metodo = clazz.getDeclaredMethod("buscarTipoComMaiorMediaSalarial");

      // Case
      Object obj = ConsultoriaFactory.build(nome, vagas, new ArrayList<>(desenvolvedores));

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(
          () -> assertEquals(expected, resposta, "Deve retornar o tipo de desenvolvedor com a maior média salarial")
      );
    }
  }
}
