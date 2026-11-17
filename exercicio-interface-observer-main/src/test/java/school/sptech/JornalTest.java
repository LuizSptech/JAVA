package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.factory.EstatisticaJornalistaFactory;
import school.sptech.factory.EstatisticaTemaFactory;
import school.sptech.factory.JornalFactory;
import school.sptech.factory.LeitorFactory;
import school.sptech.factory.NoticiaFactory;
import school.sptech.factory.ObservadorFake;
import school.sptech.provider.jornal.ConstructorProvider;
import school.sptech.provider.jornal.DesinscreverProvider;
import school.sptech.provider.jornal.GetJornalistaComMaisPublicacoesProvider;
import school.sptech.provider.jornal.GetLeitoresPorTemaProvider;
import school.sptech.provider.jornal.GetMaiorPercentualProvider;
import school.sptech.provider.jornal.GetNoticiaMaisFavoritadaProvider;
import school.sptech.provider.jornal.GetTemaFavoritoProvider;
import school.sptech.provider.jornal.InscreverProvider;
import school.sptech.provider.jornal.PublicarProvider;
import school.sptech.util.EnumConstantMapping;
import school.sptech.util.ObjectFieldMapping;

@DisplayName("Jornal")
public class JornalTest {

    @Nested
    @DisplayName("Jornal 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Jornal> clazz = Jornal.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("observadores"),
                        "Deve possuir o atributo observadores")
            );
        }

        @Test
        @DisplayName("2. Validar Atributos 'final'")
        void test2() throws ReflectiveOperationException {
            Field campo = Jornal.class.getDeclaredField("nome");

            assertTrue(Modifier.isFinal(campo.getModifiers()), "nome deve ser final");
        }
    }

    @Nested
    @DisplayName("Jornal 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            Class<Jornal> clazz = Jornal.class;

            assertAll(
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("inscrever", Observador.class),
                        "Deve possuir o método inscrever"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("desinscrever", Observador.class),
                        "Deve possuir o método desinscrever"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("publicar", Noticia.class),
                        "Deve possuir o método publicar"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getMaiorPercentual"),
                        "Deve possuir o método getMaiorPercentual"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("getLeitoresPorTema", Tema.class),
                        "Deve possuir o método getLeitoresPorTema"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("getJornalistaComMaisPublicacoes"),
                        "Deve possuir o método getJornalistaComMaisPublicacoes"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredMethod("getTemaFavorito"),
                        "Deve possuir o método getTemaFavorito"),
                  () -> assertDoesNotThrow(
                        () -> clazz.getDeclaredMethod("getNoticiaMaisFavoritada"),
                        "Deve possuir o método getNoticiaMaisFavoritada")
            );
        }
    }

    @Nested
    @DisplayName("Jornal 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Class<Jornal> clazz = Jornal.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Class<Jornal> clazz = Jornal.class;
            List<Method> metodos = new ArrayList<>();

            try {
                metodos.add(clazz.getDeclaredMethod("inscrever", Observador.class));
                metodos.add(clazz.getDeclaredMethod("desinscrever", Observador.class));
                metodos.add(clazz.getDeclaredMethod("publicar", Noticia.class));
                metodos.add(clazz.getDeclaredMethod("getMaiorPercentual"));
                metodos.add(clazz.getDeclaredMethod("getLeitoresPorTema", Tema.class));
                metodos.add(clazz.getDeclaredMethod("getJornalistaComMaisPublicacoes"));
                metodos.add(clazz.getDeclaredMethod("getTemaFavorito"));
                metodos.add(clazz.getDeclaredMethod("getNoticiaMaisFavoritada"));
            } catch (ReflectiveOperationException ignored) {
            }

            Stream<Executable> validacoes = metodos.stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Jornal> clazz = Jornal.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()),
                                String.format("Getter %s deve ser público", getName));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map(campo -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertThrows(NoSuchMethodException.class,
                            () -> clazz.getDeclaredMethod(setName, campo.getType()),
                            String.format("%s não deve possuir setter", campo.getName()));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }

        @ParameterizedTest(name = "{index}. Jornal(\"{0}\")")
        @ArgumentsSource(ConstructorProvider.class)
        @DisplayName("4. Construtor deve inicializar os atributos corretamente")
        void test4(String nome) throws ReflectiveOperationException {
            Class<Jornal> clazz = Jornal.class;

            assertDoesNotThrow(() -> clazz.getDeclaredConstructor(String.class),
                  "Construtor com parâmetros (String nome) deve existir");

            Object obj = clazz.getDeclaredConstructor(String.class).newInstance(nome);

            ObjectFieldMapping<Jornal> mapping = new ObjectFieldMapping<>(Jornal.class);

            assertAll(
                  () -> assertEquals(nome, mapping.get("nome").get(obj),
                        "nome deve ser inicializado corretamente"),
                  () -> assertIterableEquals(List.of(),
                        (List<?>) mapping.get("observadores").get(obj),
                        "observadores deve ser inicializada como uma lista vazia")
            );
        }
    }

    @Nested
    @DisplayName("Jornal 4. Método - inscrever")
    class InscreverTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(InscreverProvider.class)
        @DisplayName("Deve adicionar o observador ao final da lista de observadores")
        void test1(Object jornal, Object observador, List<?> expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("inscrever", Observador.class);

            metodo.invoke(jornal, observador);

            ObjectFieldMapping<Jornal> mapping = new ObjectFieldMapping<>(Jornal.class);

            assertIterableEquals(expected, (List<?>) mapping.get("observadores").get(jornal),
                  "A lista de observadores deve conter o observador inscrito");
        }
    }

    @Nested
    @DisplayName("Jornal 5. Método - desinscrever")
    class DesinscreverTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(DesinscreverProvider.class)
        @DisplayName("Deve remover o observador da lista de observadores")
        void test1(Object jornal, Object observador, List<?> expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("desinscrever", Observador.class);

            metodo.invoke(jornal, observador);

            ObjectFieldMapping<Jornal> mapping = new ObjectFieldMapping<>(Jornal.class);

            assertIterableEquals(expected, (List<?>) mapping.get("observadores").get(jornal),
                  "A lista de observadores não deve conter o observador desinscrito");
        }
    }

    @Nested
    @DisplayName("Jornal 6. Método - publicar")
    class PublicarTest {

        @ParameterizedTest(name = "{index}. {0} observador(es)")
        @ArgumentsSource(PublicarProvider.class)
        @DisplayName("Deve notificar cada observador exatamente uma vez com a notícia publicada")
        void test1(Integer qtdObservadores, Object noticia) throws ReflectiveOperationException {
            List<ObservadorFake.ReceberPublicacaoFake> delegacoes = new ArrayList<>();
            List<Object> observadores = new ArrayList<>();

            for (int i = 0; i < qtdObservadores; i++) {
                ObservadorFake.ReceberPublicacaoFake delegacao = new ObservadorFake.ReceberPublicacaoFake();
                delegacoes.add(delegacao);
                observadores.add(ObservadorFake.build(delegacao));
            }

            Object jornal = JornalFactory.build("Jornal", observadores);
            Method metodo = Jornal.class.getDeclaredMethod("publicar", Noticia.class);

            metodo.invoke(jornal, noticia);

            Stream<Executable> validacoes = delegacoes.stream()
                  .map(delegacao -> () -> assertIterableEquals(List.of(noticia),
                        delegacao.getRecebidas(),
                        "Cada observador deve receber a notícia publicada exatamente uma vez"));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("Deve atualizar os leitores e as estatísticas inscritos")
        void test2() throws ReflectiveOperationException {
            Object noticia = NoticiaFactory.build("Titulo 1", "TECNOLOGIA", "Bruno", 10);

            Object leitor = LeitorFactory.build("Ana", "ana@email.com", List.of(), List.of(),
                  "TECNOLOGIA");
            Object estatisticaTema = EstatisticaTemaFactory.build("TECNOLOGIA", 2, 5);
            Object estatisticaJornalista = EstatisticaJornalistaFactory.build(6, "Bruno", 5);

            Object jornal = JornalFactory.build("Jornal",
                  List.of(leitor, estatisticaTema, estatisticaJornalista));
            Method metodo = Jornal.class.getDeclaredMethod("publicar", Noticia.class);

            metodo.invoke(jornal, noticia);

            ObjectFieldMapping<Leitor> leitorMapping = new ObjectFieldMapping<>(Leitor.class);
            ObjectFieldMapping<EstatisticaTema> temaMapping = new ObjectFieldMapping<>(
                  EstatisticaTema.class);
            ObjectFieldMapping<EstatisticaJornalista> jornalistaMapping = new ObjectFieldMapping<>(
                  EstatisticaJornalista.class);

            assertAll(
                  () -> assertIterableEquals(List.of(noticia),
                        (List<?>) leitorMapping.get("publicacoesRecebidas").get(leitor),
                        "O leitor deve receber a notícia"),
                  () -> assertIterableEquals(List.of(noticia),
                        (List<?>) leitorMapping.get("publicacoesFavoritas").get(leitor),
                        "A notícia do tema favorito deve ir para as favoritas do leitor"),
                  () -> assertEquals(6,
                        temaMapping.get("qtdPublicacoesRecebidas").get(estatisticaTema),
                        "A estatística de tema deve contabilizar a notícia recebida"),
                  () -> assertEquals(3,
                        temaMapping.get("qtdPublicacoesTema").get(estatisticaTema),
                        "A estatística de tema deve contabilizar a notícia do seu tema"),
                  () -> assertEquals(7,
                        jornalistaMapping.get("qtdPublicacoesRecebidas").get(estatisticaJornalista),
                        "A estatística de jornalista deve contabilizar a notícia recebida"),
                  () -> assertEquals(6,
                        jornalistaMapping.get("qtdPublicacoesJornalista").get(estatisticaJornalista),
                        "A estatística de jornalista deve contabilizar a notícia do seu jornalista")
            );
        }
    }

    @Nested
    @DisplayName("Jornal 7. Método - getMaiorPercentual")
    class GetMaiorPercentualTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetMaiorPercentualProvider.class)
        @DisplayName("Deve retornar a estatística com o maior percentual")
        void test1(Object jornal, Object expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("getMaiorPercentual");

            Object resposta = metodo.invoke(jornal);

            assertSame(expected, resposta,
                  "Deve retornar a estatística com o maior percentual");
        }
    }

    @Nested
    @DisplayName("Jornal 8. Método - getLeitoresPorTema")
    class GetLeitoresPorTemaTest {

        @ParameterizedTest(name = "{index}. {3}")
        @ArgumentsSource(GetLeitoresPorTemaProvider.class)
        @DisplayName("Deve retornar os leitores cujo tema favorito é o tema informado")
        void test1(Object jornal, String tema, List<?> expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("getLeitoresPorTema", Tema.class);

            Tema temaEnum = EnumConstantMapping.getEnumConstant(Tema.class, tema);
            List<?> resposta = (List<?>) metodo.invoke(jornal, temaEnum);

            assertIterableEquals(expected, resposta,
                  "Deve retornar a lista de leitores do tema corretamente");
        }
    }

    @Nested
    @DisplayName("Jornal 9. Método - getJornalistaComMaisPublicacoes")
    class GetJornalistaComMaisPublicacoesTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetJornalistaComMaisPublicacoesProvider.class)
        @DisplayName("Deve retornar o nome do jornalista com mais publicações")
        void test1(Object jornal, String expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("getJornalistaComMaisPublicacoes");

            Object resposta = metodo.invoke(jornal);

            assertEquals(expected, resposta,
                  "Deve retornar o jornalista com mais publicações corretamente");
        }
    }

    @Nested
    @DisplayName("Jornal 10. Método - getTemaFavorito (Desafio)")
    class GetTemaFavoritoTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetTemaFavoritoProvider.class)
        @DisplayName("Deve retornar o tema favorito mais frequente entre os leitores")
        void test1(Object jornal, String tema, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("getTemaFavorito");

            Tema expected = tema == null ? null
                  : EnumConstantMapping.getEnumConstant(Tema.class, tema);
            Object resposta = metodo.invoke(jornal);

            assertEquals(expected, resposta, "Deve retornar o tema favorito corretamente");
        }
    }

    @Nested
    @DisplayName("Jornal 11. Método - getNoticiaMaisFavoritada (Desafio)")
    class GetNoticiaMaisFavoritadaTest {

        @ParameterizedTest(name = "{index}. {2}")
        @ArgumentsSource(GetNoticiaMaisFavoritadaProvider.class)
        @DisplayName("Deve retornar a notícia favoritada pelo maior número de leitores")
        void test1(Object jornal, Object expected, String cenario)
              throws ReflectiveOperationException {
            Method metodo = Jornal.class.getDeclaredMethod("getNoticiaMaisFavoritada");

            Object resposta = metodo.invoke(jornal);

            assertSame(expected, resposta,
                  "Deve retornar a notícia que aparece nas favoritas de mais leitores");
        }
    }
}
