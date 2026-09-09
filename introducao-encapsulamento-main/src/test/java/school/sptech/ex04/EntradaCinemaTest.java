package school.sptech.ex04;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import school.sptech.ex04.provider.AplicarDescontoHorarioProvider;
import school.sptech.ex04.provider.AplicarDescontoProvider;
import school.sptech.ex04.provider.ComprarIngressoProvider;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertMethodReturnType;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("EntradaCinema")
class EntradaCinemaTest {

    private final Class<EntradaCinema> clazz = EntradaCinema.class;

    private EntradaCinema nova(Double valor, Integer hora, Boolean[] assentosDisponiveis)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(EntradaCinema.class)
              .with("nome", "Filme Teste")
              .with("hora", hora)
              .with("sala", 1)
              .with("valor", valor)
              .with("assentosDisponiveis", assentosDisponiveis)
              .build();
    }

    private Method metodoComprarIngresso() throws NoSuchMethodException {
        assertPublicMethod(clazz, "comprarIngresso",
              Integer.class, Double.class, Integer.class, Boolean.class);
        Method metodo = clazz.getDeclaredMethod("comprarIngresso",
              Integer.class, Double.class, Integer.class, Boolean.class);
        metodo.setAccessible(true);
        return metodo;
    }

    private static Boolean[] assentos(boolean... valores) {
        Boolean[] resultado = new Boolean[valores.length];
        for (int i = 0; i < valores.length; i++) {
            resultado[i] = valores[i];
        }
        return resultado;
    }

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            assertPrivateField(clazz, "nome", String.class);
            assertPrivateField(clazz, "hora", Integer.class);
            assertPrivateField(clazz, "sala", Integer.class);
            assertPrivateField(clazz, "valor", Double.class);
            assertPrivateField(clazz, "assentosDisponiveis", Boolean[].class);
        }
    }

    @Nested
    @DisplayName("Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("Todos os atributos devem ser privados")
        void atributosPrivados() {
            assertAllFieldsPrivate(clazz);
        }

        @Test
        @DisplayName("Deve possuir apenas getters publicos (sem setters)")
        void gettersSemSetters() {
            assertGetter(clazz, "nome", String.class);
            assertGetter(clazz, "hora", Integer.class);
            assertGetter(clazz, "sala", Integer.class);
            assertGetter(clazz, "valor", Double.class);
            assertGetter(clazz, "assentosDisponiveis", Boolean[].class);

            assertNoSetter(clazz, "nome", String.class);
            assertNoSetter(clazz, "hora", Integer.class);
            assertNoSetter(clazz, "sala", Integer.class);
            assertNoSetter(clazz, "valor", Double.class);
            assertNoSetter(clazz, "assentosDisponiveis", Boolean[].class);
        }
    }

    @Nested
    @DisplayName("Metodos")
    class MetodosTest {

        @Test
        @DisplayName("Deve conter os metodos publicos com as assinaturas e retornos corretos")
        void validarMetodos() {
            assertPublicMethod(clazz, "aplicarDesconto", Integer.class, Boolean.class);
            assertMethodReturnType(clazz, "aplicarDesconto", void.class, Integer.class, Boolean.class);
            assertPublicMethod(clazz, "aplicarDescontoHorario");
            assertMethodReturnType(clazz, "aplicarDescontoHorario", void.class);
            assertPublicMethod(clazz, "comprarIngresso",
                  Integer.class, Double.class, Integer.class, Boolean.class);
            assertMethodReturnType(clazz, "comprarIngresso", Boolean.class,
                  Integer.class, Double.class, Integer.class, Boolean.class);
        }
    }

    @Nested
    @DisplayName("aplicarDesconto(Integer, Boolean)")
    class AplicarDescontoTest {

        @ParameterizedTest(name = "valorIni={0}, idade={1}, estudante={2} -> valor={3}")
        @ArgumentsSource(AplicarDescontoProvider.class)
        @DisplayName("Deve atualizar o valor conforme o cupom de desconto adequado")
        void deveAplicarDesconto(Double valorIni, Integer idade, Boolean estudante, Double valorEsp)
              throws ReflectiveOperationException {

            assertPublicMethod(clazz, "aplicarDesconto", Integer.class, Boolean.class);
            Method metodo = clazz.getDeclaredMethod("aplicarDesconto", Integer.class, Boolean.class);
            metodo.setAccessible(true);

            EntradaCinema obj = nova(valorIni, 20, assentos(true, true, true));

            invocar(metodo, obj, idade, estudante);

            assertEquals(valorEsp, (Double) getField(obj, "valor"), 0.01,
                  "aplicarDesconto(Integer, Boolean) nao atualizou o valor corretamente");
        }
    }

    @Nested
    @DisplayName("aplicarDescontoHorario()")
    class AplicarDescontoHorarioTest {

        @ParameterizedTest(name = "valorIni={0}, hora={1} -> valor={2}")
        @ArgumentsSource(AplicarDescontoHorarioProvider.class)
        @DisplayName("Deve aplicar 10% de desconto quando o filme e exibido antes das 16h")
        void deveAplicarDescontoHorario(Double valorIni, Integer hora, Double valorEsp)
              throws ReflectiveOperationException {

            assertPublicMethod(clazz, "aplicarDescontoHorario");
            Method metodo = clazz.getDeclaredMethod("aplicarDescontoHorario");
            metodo.setAccessible(true);

            EntradaCinema obj = nova(valorIni, hora, assentos(true, true, true));

            invocar(metodo, obj);

            assertEquals(valorEsp, (Double) getField(obj, "valor"), 0.01,
                  "aplicarDescontoHorario() nao atualizou o valor corretamente");
        }
    }

    @Nested
    @DisplayName("comprarIngresso(Integer, Double, Integer, Boolean)")
    class ComprarIngressoTest {

        @ParameterizedTest(name = "valorIni={0}, hora={1}, indice={3}, pagamento={4}, idade={5},"
              + " estudante={6} -> retorno={7}, valor={8}, assento={9}")
        @ArgumentsSource(ComprarIngressoProvider.class)
        @DisplayName("Deve aplicar os descontos, validar assento/pagamento e ocupar o assento no sucesso")
        void deveComprarIngresso(Double valorIni, Integer hora, Boolean[] assentosDisponiveis, Integer indice,
              Double pagamento, Integer idade, Boolean estudante, Boolean retornoEsp, Double valorEsp,
              Boolean assentoEsp) throws ReflectiveOperationException {

            Method metodo = metodoComprarIngresso();

            EntradaCinema obj = nova(valorIni, hora, assentosDisponiveis.clone());

            Object retorno = invocar(metodo, obj, indice, pagamento, idade, estudante);

            assertEquals(retornoEsp, retorno,
                  "comprarIngresso(...) nao retornou o valor esperado");
            assertEquals(valorEsp, (Double) getField(obj, "valor"), 0.01,
                  "comprarIngresso(...) nao tratou o atributo valor conforme esperado");

            Boolean[] arr = (Boolean[]) getField(obj, "assentosDisponiveis");
            assertEquals(assentoEsp, arr[indice],
                  "comprarIngresso(...) nao atualizou o assento escolhido conforme esperado");
        }

        @Test
        @DisplayName("Com indiceAssento null deve retornar false e nao alterar valor nem assentos")
        void comIndiceNull() throws ReflectiveOperationException {
            Method metodo = metodoComprarIngresso();
            EntradaCinema obj = nova(20.0, 20, assentos(true, true, true));

            Object retorno = invocar(metodo, obj, null, 100.0, 30, false);

            assertEquals(Boolean.FALSE, retorno, "comprarIngresso(null, ...) deveria retornar false");
            assertEquals(20.0, (Double) getField(obj, "valor"), 0.01,
                  "comprarIngresso(null, ...) nao deveria alterar o valor");
            assertArrayEquals(assentos(true, true, true), (Boolean[]) getField(obj, "assentosDisponiveis"),
                  "comprarIngresso(null, ...) nao deveria alterar os assentos");
        }

        @Test
        @DisplayName("Com pagamento null deve retornar false e nao alterar valor nem o assento")
        void comPagamentoNull() throws ReflectiveOperationException {
            Method metodo = metodoComprarIngresso();
            EntradaCinema obj = nova(20.0, 20, assentos(true, true, true));

            Object retorno = invocar(metodo, obj, 0, null, 30, false);

            assertEquals(Boolean.FALSE, retorno, "comprarIngresso(..., pagamento null, ...) deveria retornar false");
            assertEquals(20.0, (Double) getField(obj, "valor"), 0.01,
                  "comprarIngresso com pagamento null nao deveria alterar o valor");
            assertEquals(Boolean.TRUE, ((Boolean[]) getField(obj, "assentosDisponiveis"))[0],
                  "comprarIngresso com pagamento null nao deveria ocupar o assento");
        }

        @Test
        @DisplayName("Com idade null deve retornar false e nao alterar valor nem o assento")
        void comIdadeNull() throws ReflectiveOperationException {
            Method metodo = metodoComprarIngresso();
            EntradaCinema obj = nova(20.0, 20, assentos(true, true, true));

            Object retorno = invocar(metodo, obj, 0, 100.0, null, false);

            assertEquals(Boolean.FALSE, retorno, "comprarIngresso(..., idade null, ...) deveria retornar false");
            assertEquals(20.0, (Double) getField(obj, "valor"), 0.01,
                  "comprarIngresso com idade null nao deveria alterar o valor");
            assertEquals(Boolean.TRUE, ((Boolean[]) getField(obj, "assentosDisponiveis"))[0],
                  "comprarIngresso com idade null nao deveria ocupar o assento");
        }

        @Test
        @DisplayName("Com estudante null deve retornar false e nao alterar valor nem o assento")
        void comEstudanteNull() throws ReflectiveOperationException {
            Method metodo = metodoComprarIngresso();
            EntradaCinema obj = nova(20.0, 20, assentos(true, true, true));

            Object retorno = invocar(metodo, obj, 0, 100.0, 30, null);

            assertEquals(Boolean.FALSE, retorno, "comprarIngresso(..., estudante null) deveria retornar false");
            assertEquals(20.0, (Double) getField(obj, "valor"), 0.01,
                  "comprarIngresso com estudante null nao deveria alterar o valor");
            assertEquals(Boolean.TRUE, ((Boolean[]) getField(obj, "assentosDisponiveis"))[0],
                  "comprarIngresso com estudante null nao deveria ocupar o assento");
        }
    }

    private static Object invocar(Method metodo, Object alvo, Object... args) {
        try {
            return metodo.invoke(alvo, args);
        } catch (InvocationTargetException e) {
            fail("O metodo '" + metodo.getName() + "' lancou " + e.getCause()
                  + " para os dados do cenario.");
        } catch (IllegalAccessException e) {
            fail("Nao foi possivel invocar o metodo '" + metodo.getName() + "': " + e.getMessage());
        }
        return null;
    }
}
