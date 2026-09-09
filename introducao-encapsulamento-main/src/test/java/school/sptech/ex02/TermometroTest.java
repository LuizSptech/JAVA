package school.sptech.ex02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import school.sptech.ex02.provider.AlterarTemperaturaProvider;
import school.sptech.ex02.provider.ConstrutorProvider;
import school.sptech.ex02.provider.ConverterParaFahrenheitProvider;
import school.sptech.ex02.provider.ConverterParaKelvinProvider;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertMethodReturnType;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicConstructor;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Termometro")
class TermometroTest {

    private final Class<Termometro> clazz = Termometro.class;

    private Termometro novoTermometro(Double atual, Double max, Double min)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Termometro.class)
              .with("temperaturaAtual", atual)
              .with("temperaturaMaxRegistrada", max)
              .with("temperaturaMinRegistrada", min)
              .build();
    }

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            assertPrivateField(clazz, "temperaturaAtual", Double.class);
            assertPrivateField(clazz, "temperaturaMaxRegistrada", Double.class);
            assertPrivateField(clazz, "temperaturaMinRegistrada", Double.class);
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
            assertGetter(clazz, "temperaturaAtual", Double.class);
            assertGetter(clazz, "temperaturaMaxRegistrada", Double.class);
            assertGetter(clazz, "temperaturaMinRegistrada", Double.class);
            assertNoSetter(clazz, "temperaturaAtual", Double.class);
            assertNoSetter(clazz, "temperaturaMaxRegistrada", Double.class);
            assertNoSetter(clazz, "temperaturaMinRegistrada", Double.class);
        }
    }

    @Nested
    @DisplayName("Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve possuir um construtor publico que recebe um Double")
        void validarConstrutor() {
            assertPublicConstructor(clazz, Double.class);
        }

        @ParameterizedTest(name = "temperaturaInicial={0}")
        @ArgumentsSource(ConstrutorProvider.class)
        @DisplayName("Deve atribuir a temperatura inicial aos 3 atributos")
        void deveInicializarAtributos(Double temperaturaInicial) throws ReflectiveOperationException {
            assertPublicConstructor(clazz, Double.class);
            Constructor<Termometro> construtor = clazz.getDeclaredConstructor(Double.class);
            construtor.setAccessible(true);

            Termometro obj = construtor.newInstance(temperaturaInicial);

            assertEquals(temperaturaInicial, (Double) getField(obj, "temperaturaAtual"), 0.01,
                  "O construtor deveria atribuir a temperatura inicial em temperaturaAtual");
            assertEquals(temperaturaInicial, (Double) getField(obj, "temperaturaMaxRegistrada"), 0.01,
                  "O construtor deveria atribuir a temperatura inicial em temperaturaMaxRegistrada");
            assertEquals(temperaturaInicial, (Double) getField(obj, "temperaturaMinRegistrada"), 0.01,
                  "O construtor deveria atribuir a temperatura inicial em temperaturaMinRegistrada");
        }
    }

    @Nested
    @DisplayName("alterarTemperatura(Double)")
    class AlterarTemperaturaTest {

        @ParameterizedTest(name = "atual={0}, max={1}, min={2}, nova={3} -> atual={4}, max={5}, min={6}")
        @ArgumentsSource(AlterarTemperaturaProvider.class)
        @DisplayName("Deve atualizar temperaturaAtual e os extremos registrados")
        void deveAlterarTemperatura(Double atualIni, Double maxIni, Double minIni, Double nova,
              Double atualEsp, Double maxEsp, Double minEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "alterarTemperatura", Double.class);
            Method metodo = clazz.getDeclaredMethod("alterarTemperatura", Double.class);
            metodo.setAccessible(true);

            Termometro obj = novoTermometro(atualIni, maxIni, minIni);

            invocar(metodo, obj, nova);

            assertEquals(atualEsp, (Double) getField(obj, "temperaturaAtual"), 0.01,
                  "alterarTemperatura(Double) nao atualizou temperaturaAtual corretamente");
            assertEquals(maxEsp, (Double) getField(obj, "temperaturaMaxRegistrada"), 0.01,
                  "alterarTemperatura(Double) nao atualizou temperaturaMaxRegistrada corretamente");
            assertEquals(minEsp, (Double) getField(obj, "temperaturaMinRegistrada"), 0.01,
                  "alterarTemperatura(Double) nao atualizou temperaturaMinRegistrada corretamente");
        }
    }

    @Nested
    @DisplayName("converterParaKelvin()")
    class ConverterParaKelvinTest {

        @ParameterizedTest(name = "temperaturaAtual={0} -> {1} K")
        @ArgumentsSource(ConverterParaKelvinProvider.class)
        @DisplayName("Deve converter temperaturaAtual de Celsius para Kelvin")
        void deveConverter(Double temperaturaAtual, Double esperado) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "converterParaKelvin");
            assertMethodReturnType(clazz, "converterParaKelvin", Double.class);
            Method metodo = clazz.getDeclaredMethod("converterParaKelvin");
            metodo.setAccessible(true);

            Termometro obj = novoTermometro(temperaturaAtual, temperaturaAtual, temperaturaAtual);

            Object retorno = invocar(metodo, obj);

            assertEquals(esperado, (Double) retorno, 0.01,
                  "converterParaKelvin() nao retornou o valor esperado");
        }
    }

    @Nested
    @DisplayName("converterParaFahrenheit()")
    class ConverterParaFahrenheitTest {

        @ParameterizedTest(name = "temperaturaAtual={0} -> {1} F")
        @ArgumentsSource(ConverterParaFahrenheitProvider.class)
        @DisplayName("Deve converter temperaturaAtual de Celsius para Fahrenheit")
        void deveConverter(Double temperaturaAtual, Double esperado) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "converterParaFahrenheit");
            assertMethodReturnType(clazz, "converterParaFahrenheit", Double.class);
            Method metodo = clazz.getDeclaredMethod("converterParaFahrenheit");
            metodo.setAccessible(true);

            Termometro obj = novoTermometro(temperaturaAtual, temperaturaAtual, temperaturaAtual);

            Object retorno = invocar(metodo, obj);

            assertEquals(esperado, (Double) retorno, 0.01,
                  "converterParaFahrenheit() nao retornou o valor esperado");
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
