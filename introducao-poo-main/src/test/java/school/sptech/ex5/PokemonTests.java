package school.sptech.ex5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.ex5.provider.BatalharProvider;
import school.sptech.ex5.provider.CalcularNivelProvider;
import school.sptech.ex5.provider.CalcularPoderDeCombateProvider;
import school.sptech.ex5.provider.GanharExperienciaProvider;
import school.sptech.ex5.provider.ReceberAtaqueProvider;
import school.sptech.ex5.provider.RecuperarVidaProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Atividade 5 - Pokémon")
class PokemonTests {

    @Nested
    @DisplayName("Estrutura")
    class EstruturaTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Pokemon> clazz = Pokemon.class;

            assertField(clazz, "nome", String.class);
            assertField(clazz, "tipo", String.class);
            assertField(clazz, "vida", Integer.class);
            assertField(clazz, "ataque", Integer.class);
            assertField(clazz, "experiencia", Integer.class);
        }

        @Test
        @DisplayName("Deve conter os métodos com as assinaturas corretas")
        void validarMetodos() {
            Class<Pokemon> clazz = Pokemon.class;

            assertMethod(clazz, "receberAtaque", Integer.class);
            assertMethod(clazz, "recuperarVida", Integer.class);
            assertMethod(clazz, "ganharExperiencia", Integer.class);
            assertMethod(clazz, "calcularNivel");
            assertMethod(clazz, "calcularPoderDeCombate");
            assertMethod(clazz, "batalhar", Integer[].class, Integer[].class);
        }
    }

    @Nested
    @DisplayName("receberAtaque()")
    class ReceberAtaqueTest {

        @ParameterizedTest
        @DisplayName("Deve aplicar o dano e atualizar vida corretamente, sem deixá-la negativa")
        @ArgumentsSource(ReceberAtaqueProvider.class)
        void testReceberAtaque(Integer vida, Integer danoRecebido, Integer vidaEsperada)
              throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("receberAtaque", Integer.class);

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("vida", vida)
                  .build();

            method.invoke(obj, danoRecebido);

            assertEquals(vidaEsperada, getField(obj, "vida"),
                  "receberAtaque(" + danoRecebido + ") não atualizou vida corretamente");
        }
    }

    @Nested
    @DisplayName("recuperarVida()")
    class RecuperarVidaTest {

        @ParameterizedTest
        @DisplayName("Deve recuperar a vida e atualizá-la corretamente, sem ultrapassar 100")
        @ArgumentsSource(RecuperarVidaProvider.class)
        void testRecuperarVida(Integer vida, Integer recuperacaoVida, Integer vidaEsperada)
              throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("recuperarVida", Integer.class);

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("vida", vida)
                  .build();

            method.invoke(obj, recuperacaoVida);

            assertEquals(vidaEsperada, getField(obj, "vida"),
                  "recuperarVida(" + recuperacaoVida + ") não atualizou vida corretamente");
        }
    }

    @Nested
    @DisplayName("ganharExperiencia()")
    class GanharExperienciaTest {

        @ParameterizedTest
        @DisplayName("Deve ganhar experiência e atualizar o atributo corretamente")
        @ArgumentsSource(GanharExperienciaProvider.class)
        void testGanharExperiencia(Integer experiencia, Integer experienciaRecebida, Integer experienciaEsperada)
              throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("ganharExperiencia", Integer.class);

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("experiencia", experiencia)
                  .build();

            method.invoke(obj, experienciaRecebida);

            assertEquals(experienciaEsperada, getField(obj, "experiencia"),
                  "ganharExperiencia(" + experienciaRecebida + ") não atualizou experiencia corretamente");
        }
    }

    @Nested
    @DisplayName("calcularNivel()")
    class CalcularNivelTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o nível corretamente a partir da experiência")
        @ArgumentsSource(CalcularNivelProvider.class)
        void testCalcularNivel(Integer experiencia, Integer nivelEsperado) throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("calcularNivel");

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("experiencia", experiencia)
                  .build();

            Integer nivel = (Integer) method.invoke(obj);

            assertEquals(nivelEsperado, nivel,
                  "calcularNivel() deveria retornar " + nivelEsperado + ", mas retornou " + nivel);
        }
    }

    @Nested
    @DisplayName("calcularPoderDeCombate()")
    class CalcularPoderDeCombateTest {

        @ParameterizedTest
        @DisplayName("Deve calcular o poder de combate corretamente")
        @ArgumentsSource(CalcularPoderDeCombateProvider.class)
        void testCalcularPoderDeCombate(Integer ataque, Integer vida, Integer experiencia, Integer poderEsperado)
              throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("calcularPoderDeCombate");

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("ataque", ataque)
                  .with("vida", vida)
                  .with("experiencia", experiencia)
                  .build();

            Integer poder = (Integer) method.invoke(obj);

            assertEquals(poderEsperado, poder,
                  "calcularPoderDeCombate() deveria retornar " + poderEsperado + ", mas retornou " + poder);
        }
    }

    @Nested
    @DisplayName("batalhar()")
    class BatalharTest {

        @ParameterizedTest
        @DisplayName("Deve aplicar a sequência de ataques e curas e atualizar a vida corretamente")
        @ArgumentsSource(BatalharProvider.class)
        void testBatalhar(Integer vida, Integer[] ataques, Integer[] curas, Integer vidaEsperada)
              throws ReflectiveOperationException {

            Class<Pokemon> clazz = Pokemon.class;
            Method method = clazz.getDeclaredMethod("batalhar", Integer[].class, Integer[].class);

            method.setAccessible(true);

            Pokemon obj = new ObjectFieldBuilder<>(Pokemon.class)
                  .with("vida", vida)
                  .build();

            method.invoke(obj, ataques, curas);

            assertEquals(vidaEsperada, getField(obj, "vida"),
                  "batalhar() não atualizou a vida corretamente");
        }
    }
}
