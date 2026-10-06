package school.sptech;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.especialistas.DesenvolvedorWeb;
import school.sptech.provider.desenvolvedorweb.CalcularSalarioProvider;
import school.sptech.provider.desenvolvedorweb.IsFullstackProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("DesenvolvedorWeb")
public class DesenvolvedorWebTest {

  Map<String, Field> campos() throws ReflectiveOperationException {
    Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
    Class<Desenvolvedor> superClazz = Desenvolvedor.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "qtdHoras", "valorHora" };
    String[] nomeCampos = { "backend", "frontend", "sgbd", "horasMentoria" };

    for (String campoNome : nomeCamposSuper) {
      Field campo = superClazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

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
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("backend"), "Deve possuir o atributo backend"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("frontend"), "Deve possuir o atributo frontend"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("sgbd"), "Deve possuir o atributo sgbd"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredField("horasMentoria"), "Deve possuir o atributo horasMentoria")
      );
    }

    @Test
    @DisplayName("2. Deve herdar de Desenvolvedor")
    void deveHerdarDeDesenvolvedor() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      String[] camposHerdados = { "nome", "qtdHoras", "valorHora" };

      Stream<Executable> validacoesCampos = Arrays.stream(camposHerdados)
          .map((campo) -> () -> assertThrows(NoSuchFieldException.class, () -> clazz.getDeclaredField(campo),
              String.format("O atributo %s não deve ser declarado novamente, pois é herdado de Desenvolvedor", campo)));

      assertAll(
          () -> assertEquals(Desenvolvedor.class, clazz.getSuperclass(), "DesenvolvedorWeb deve herdar de Desenvolvedor"),
          () -> assertAll(validacoesCampos)
      );
    }
  }

  @Nested
  @DisplayName("2. Métodos")
  class MetodosTest {

    @Test
    @DisplayName("1. Validar Métodos")
    void devePossuirOsMetodos() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      assertAll(
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("calcularSalario"), "Deve sobrescrever o método calcularSalario"),
          () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("isFullstack"), "Deve possuir o método isFullstack")
      );
    }
  }

  @Nested
  @DisplayName("3. Encapsulamento")
  class EncapsulamentoTest {

    @Test
    @DisplayName("1. Atributos Privados")
    void atributosDevemSerPrivados() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Field[] campos = clazz.getDeclaredFields();

      Stream<Executable> validacoes = Arrays.stream(campos)
          .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
              String.format("%s deve ser privado", campo.getName())));

      assertAll(validacoes);
    }

    @Test
    @DisplayName("2. Métodos Públicos")
    void metodosDevemSerPublicos() {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;

      List<Method> metodos = new ArrayList<>();

      try {
        metodos.add(clazz.getDeclaredMethod("calcularSalario"));
      } catch (ReflectiveOperationException ignored) {}

      try {
        metodos.add(clazz.getDeclaredMethod("isFullstack"));
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
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Field[] campos = clazz.getDeclaredFields();

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
  @DisplayName("4. Método - calcularSalario")
  class MetodoCalcularSalarioTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(CalcularSalarioProvider.class)
    @DisplayName("Deve calcular o salário somando as horas de mentoria (R$ 300,00/hora)")
    void deveCalcularSalarioComHorasDeMentoria(String descricao, String nome, Integer qtdHoras, Double valorHora,
               String frontend, String backend, String sgbd, Integer horasMentoria, Double expected) throws ReflectiveOperationException {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Method metodo = clazz.getDeclaredMethod("calcularSalario");

      Object obj = new ObjectFieldBuilder<>(DesenvolvedorWeb.class)
          .with("nome", nome)
          .with("qtdHoras", qtdHoras)
          .with("valorHora", valorHora)
          .with("frontend", frontend)
          .with("backend", backend)
          .with("sgbd", sgbd)
          .with("horasMentoria", horasMentoria)
          .build();

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, (Double) resposta, 0.01, "calcularSalario deve calcular o salário corretamente"));
    }
  }

  @Nested
  @DisplayName("5. Método - isFullstack")
  class MetodoIsFullstackTest {

    @ParameterizedTest(name = "{index}. {0}")
    @ArgumentsSource(IsFullstackProvider.class)
    @DisplayName("Deve ser fullstack somente com frontend, backend e sgbd preenchidos")
    void deveVerificarSeEhFullstack(String descricao, String nome, Integer qtdHoras, Double valorHora,
               String frontend, String backend, String sgbd, Integer horasMentoria, Boolean expected) throws ReflectiveOperationException {
      Class<DesenvolvedorWeb> clazz = DesenvolvedorWeb.class;
      Method metodo = clazz.getDeclaredMethod("isFullstack");

      Object obj = new ObjectFieldBuilder<>(DesenvolvedorWeb.class)
          .with("nome", nome)
          .with("qtdHoras", qtdHoras)
          .with("valorHora", valorHora)
          .with("frontend", frontend)
          .with("backend", backend)
          .with("sgbd", sgbd)
          .with("horasMentoria", horasMentoria)
          .build();

      // When
      Object resposta = metodo.invoke(obj);

      // Then
      assertAll(() -> assertEquals(expected, resposta, "isFullstack deve retornar corretamente"));
    }
  }
}
