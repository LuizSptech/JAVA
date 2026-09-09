package school.sptech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.provider.ComprarFichasProvider;
import school.sptech.provider.ConsumirFichaProvider;
import school.sptech.provider.ContarPontuacoesAcimaDaMediaProvider;
import school.sptech.provider.EncontrarMaiorPontuacaoProvider;
import school.sptech.provider.EncontrarMaiorSequenciaDeVitoriasProvider;
import school.sptech.provider.RegistrarPartidaProvider;
import school.sptech.provider.ValidarJogadorProvider;
import school.sptech.util.ObjectFieldBuilder;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static school.sptech.util.ReflectionUtil.assertField;
import static school.sptech.util.ReflectionUtil.assertMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Jogador")
class JogadorTest {

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            Class<Jogador> clazz = Jogador.class;

            assertField(clazz, "nome", String.class);
            assertField(clazz, "saldo", Double.class);
            assertField(clazz, "quantidadeFichas", Integer.class);
            assertField(clazz, "quantidadePartidas", Integer.class);
            assertField(clazz, "vitorias", Integer.class);
        }
    }

    @Nested
    @DisplayName("validarJogador()")
    class ValidarJogadorTest {

        @ParameterizedTest(name = "nome={0}, saldo={1} -> esperado={2}")
        @DisplayName("Deve validar o jogador corretamente")
        @ArgumentsSource(ValidarJogadorProvider.class)
        void testValidarJogador(String nome, Double saldo, Boolean valorEsperado)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "validarJogador");
            Method method = clazz.getDeclaredMethod("validarJogador");

            method.setAccessible(true);

            Jogador obj = new ObjectFieldBuilder<>(Jogador.class)
                  .with("nome", nome)
                  .with("saldo", saldo)
                  .build();

            Boolean valido = (Boolean) method.invoke(obj);

            assertEquals(valorEsperado, valido,
                  "validarJogador() com nome=" + nome + " e saldo=" + saldo + " deveria retornar "
                        + valorEsperado + ", mas retornou " + valido);
        }
    }

    @Nested
    @DisplayName("registrarPartida()")
    class RegistrarPartidaTest {

        @ParameterizedTest(name = "quantidadePartidas={0}, vitorias={1}, vitoria={2} -> quantidadePartidas={3}, vitorias={4}")
        @DisplayName("Deve registrar a partida e atualizar o estado do jogador corretamente")
        @ArgumentsSource(RegistrarPartidaProvider.class)
        void testRegistrarPartida(Integer quantidadePartidas, Integer vitorias, Boolean vitoria,
              Integer quantidadePartidasEsperado, Integer vitoriasEsperado)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "registrarPartida", Boolean.class);
            Method method = clazz.getDeclaredMethod("registrarPartida", Boolean.class);

            method.setAccessible(true);

            Jogador obj = new ObjectFieldBuilder<>(Jogador.class)
                  .with("quantidadePartidas", quantidadePartidas)
                  .with("vitorias", vitorias)
                  .build();

            method.invoke(obj, vitoria);

            assertEquals(quantidadePartidasEsperado, getField(obj, "quantidadePartidas"),
                  "registrarPartida() não atualizou quantidadePartidas corretamente");
            assertEquals(vitoriasEsperado, getField(obj, "vitorias"),
                  "registrarPartida() não atualizou vitorias corretamente");
        }
    }

    @Nested
    @DisplayName("consumirFicha()")
    class ConsumirFichaTest {

        @ParameterizedTest(name = "quantidadeFichas={0}, consumir={1} -> sucesso={2}, quantidadeFichas={3}")
        @DisplayName("Deve consumir fichas corretamente")
        @ArgumentsSource(ConsumirFichaProvider.class)
        void testConsumirFicha(Integer quantidadeFichas, Integer quantidadeFichasParametro,
              Boolean sucessoEsperado, Integer quantidadeFichasEsperada)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "consumirFicha", Integer.class);
            Method method = clazz.getDeclaredMethod("consumirFicha", Integer.class);

            method.setAccessible(true);

            Jogador obj = new ObjectFieldBuilder<>(Jogador.class)
                  .with("quantidadeFichas", quantidadeFichas)
                  .build();

            Boolean sucesso = (Boolean) method.invoke(obj, quantidadeFichasParametro);

            assertEquals(sucessoEsperado, sucesso,
                  "consumirFicha(" + quantidadeFichasParametro + ") deveria retornar " + sucessoEsperado
                        + ", mas retornou " + sucesso);
            assertEquals(quantidadeFichasEsperada, getField(obj, "quantidadeFichas"),
                  "consumirFicha(" + quantidadeFichasParametro + ") não atualizou quantidadeFichas corretamente");
        }
    }

    @Nested
    @DisplayName("comprarFichas()")
    class ComprarFichasTest {

        @ParameterizedTest(name = "saldo={0}, quantidadeFichas={1}, comprar={2} -> sucesso={3}, saldo={4}, quantidadeFichas={5}")
        @DisplayName("Deve comprar fichas corretamente, respeitando a tabela de preços")
        @ArgumentsSource(ComprarFichasProvider.class)
        void testComprarFichas(Double saldo, Integer quantidadeFichas, Integer quantidadeFichasParametro,
              Boolean sucessoEsperado, Double saldoEsperado, Integer quantidadeFichasEsperada)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "comprarFichas", Integer.class);
            Method method = clazz.getDeclaredMethod("comprarFichas", Integer.class);

            method.setAccessible(true);

            Jogador obj = new ObjectFieldBuilder<>(Jogador.class)
                  .with("saldo", saldo)
                  .with("quantidadeFichas", quantidadeFichas)
                  .build();

            Boolean sucesso = (Boolean) method.invoke(obj, quantidadeFichasParametro);

            assertEquals(sucessoEsperado, sucesso,
                  "comprarFichas(" + quantidadeFichasParametro + ") deveria retornar " + sucessoEsperado
                        + ", mas retornou " + sucesso);
            assertEquals(saldoEsperado, (Double) getField(obj, "saldo"), 0.01,
                  "comprarFichas(" + quantidadeFichasParametro + ") não atualizou saldo corretamente");
            assertEquals(quantidadeFichasEsperada, getField(obj, "quantidadeFichas"),
                  "comprarFichas(" + quantidadeFichasParametro + ") não atualizou quantidadeFichas corretamente");
        }
    }

    @Nested
    @DisplayName("encontrarMaiorPontuacao()")
    class EncontrarMaiorPontuacaoTest {

        @ParameterizedTest(name = "pontuacoes={0} -> esperado={1}")
        @DisplayName("Deve encontrar a maior pontuação do array corretamente")
        @ArgumentsSource(EncontrarMaiorPontuacaoProvider.class)
        void testEncontrarMaiorPontuacao(Integer[] pontuacoes, Integer valorEsperado)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "encontrarMaiorPontuacao", Integer[].class);
            Method method = clazz.getDeclaredMethod("encontrarMaiorPontuacao", Integer[].class);

            method.setAccessible(true);

            Jogador obj = new Jogador();

            Integer maiorPontuacao = (Integer) method.invoke(obj, (Object) pontuacoes);

            assertEquals(valorEsperado, maiorPontuacao,
                  "encontrarMaiorPontuacao() deveria retornar " + valorEsperado + ", mas retornou "
                        + maiorPontuacao);
        }
    }

    @Nested
    @DisplayName("contarPontuacoesAcimaDaMedia()")
    class ContarPontuacoesAcimaDaMediaTest {

        @ParameterizedTest(name = "pontuacoes={0} -> esperado={1}")
        @DisplayName("Deve contar as pontuações acima da média corretamente")
        @ArgumentsSource(ContarPontuacoesAcimaDaMediaProvider.class)
        void testContarPontuacoesAcimaDaMedia(Integer[] pontuacoes, Integer valorEsperado)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "contarPontuacoesAcimaDaMedia", Integer[].class);
            Method method = clazz.getDeclaredMethod("contarPontuacoesAcimaDaMedia", Integer[].class);

            method.setAccessible(true);

            Jogador obj = new Jogador();

            Integer quantidade = (Integer) method.invoke(obj, (Object) pontuacoes);

            assertEquals(valorEsperado, quantidade,
                  "contarPontuacoesAcimaDaMedia() deveria retornar " + valorEsperado + ", mas retornou "
                        + quantidade);
        }
    }

    @Nested
    @DisplayName("encontrarMaiorSequenciaDeVitorias()")
    class EncontrarMaiorSequenciaDeVitoriasTest {

        @ParameterizedTest(name = "resultados={0} -> esperado={1}")
        @DisplayName("Deve encontrar a maior sequência de vitórias consecutivas corretamente")
        @ArgumentsSource(EncontrarMaiorSequenciaDeVitoriasProvider.class)
        void testEncontrarMaiorSequenciaDeVitorias(Boolean[] resultados, Integer valorEsperado)
              throws ReflectiveOperationException {

            Class<Jogador> clazz = Jogador.class;
            assertMethod(clazz, "encontrarMaiorSequenciaDeVitorias", Boolean[].class);
            Method method = clazz.getDeclaredMethod("encontrarMaiorSequenciaDeVitorias", Boolean[].class);

            method.setAccessible(true);

            Jogador obj = new Jogador();

            Integer maiorSequencia = (Integer) method.invoke(obj, (Object) resultados);

            assertEquals(valorEsperado, maiorSequencia,
                  "encontrarMaiorSequenciaDeVitorias() deveria retornar " + valorEsperado + ", mas retornou "
                        + maiorSequencia);
        }
    }
}
