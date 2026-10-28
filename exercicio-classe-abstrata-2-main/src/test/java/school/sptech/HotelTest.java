package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
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
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import school.sptech.exceptions.HospedeForaDoHotelException;
import school.sptech.exceptions.QuartoInvalidoException;
import school.sptech.exceptions.QuartoOcupadoException;
import school.sptech.exceptions.ValorInvalidoException;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.HotelFactory;
import school.sptech.factory.TuristaFactory;
import school.sptech.provider.hotel.AplicarCortesiasProvider;
import school.sptech.provider.hotel.BuscarHospedeComMaisPontosProvider;
import school.sptech.provider.hotel.BuscarProximoQuartoLivreProvider;
import school.sptech.provider.hotel.CheckInExceptionProvider;
import school.sptech.provider.hotel.CheckInProvider;
import school.sptech.provider.hotel.CheckOutProvider;
import school.sptech.provider.hotel.GetTuristasProvider;
import school.sptech.provider.hotel.TransferirQuartoProvider;
import school.sptech.provider.hotel.TrocarQuartosProvider;
import school.sptech.provider.hotel.VerificarDisponibilidadeDoQuartoProvider;
import school.sptech.util.EqualsUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Hotel")
public class HotelTest {

    static Object getCampo(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Class<?> current = obj.getClass();

        while (current != null) {
            try {
                Field campo = current.getDeclaredField(nomeCampo);
                campo.trySetAccessible();
                return campo.get(obj);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }

        throw new NoSuchFieldException(nomeCampo);
    }

    static List<?> getHospedes(Object hotel) throws ReflectiveOperationException {
        return (List<?>) getCampo(hotel, "hospedes");
    }

    static Method metodo(String nome, Class<?>... parametros) throws NoSuchMethodException {
        return Hotel.class.getDeclaredMethod(nome, parametros);
    }

    static List<Method> metodosEsperados() {
        List<Method> metodos = new ArrayList<>();
        Object[][] assinaturas = {
              {"checkIn", new Class<?>[]{Hospede.class, Integer.class}},
              {"checkOut", new Class<?>[]{Hospede.class}},
              {"verificarDisponibilidadeDoQuarto", new Class<?>[]{Integer.class}},
              {"transferirQuarto", new Class<?>[]{Hospede.class, Integer.class}},
              {"trocarQuartos", new Class<?>[]{Hospede.class, Hospede.class}},
              {"buscarProximoQuartoLivre", new Class<?>[]{Integer.class, Integer.class}},
              {"buscarHospedeComMaisPontos", new Class<?>[]{}},
              {"getTuristas", new Class<?>[]{}},
              {"aplicarCortesias", new Class<?>[]{}},
              {"getNome", new Class<?>[]{}},
              {"getHospedes", new Class<?>[]{}}
        };

        for (Object[] assinatura : assinaturas) {
            try {
                metodos.add(metodo((String) assinatura[0], (Class<?>[]) assinatura[1]));
            } catch (NoSuchMethodException ignored) {
            }
        }
        return metodos;
    }

    @Nested
    @DisplayName("Hotel 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Hotel> clazz = Hotel.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("hospedes"),
                        "Deve possuir o atributo hospedes")
            );
        }
    }

    @Nested
    @DisplayName("Hotel 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertAll(
                  () -> assertDoesNotThrow(() -> metodo("checkIn", Hospede.class, Integer.class),
                        "Deve possuir o método checkIn(Hospede, Integer)"),
                  () -> assertDoesNotThrow(() -> metodo("checkOut", Hospede.class),
                        "Deve possuir o método checkOut(Hospede)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("verificarDisponibilidadeDoQuarto", Integer.class),
                        "Deve possuir o método verificarDisponibilidadeDoQuarto(Integer)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("transferirQuarto", Hospede.class, Integer.class),
                        "Deve possuir o método transferirQuarto(Hospede, Integer)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("trocarQuartos", Hospede.class, Hospede.class),
                        "Deve possuir o método trocarQuartos(Hospede, Hospede)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("buscarProximoQuartoLivre", Integer.class, Integer.class),
                        "Deve possuir o método buscarProximoQuartoLivre(Integer, Integer)"),
                  () -> assertDoesNotThrow(() -> metodo("buscarHospedeComMaisPontos"),
                        "Deve possuir o método buscarHospedeComMaisPontos()"),
                  () -> assertDoesNotThrow(() -> metodo("getTuristas"),
                        "Deve possuir o método getTuristas()"),
                  () -> assertDoesNotThrow(() -> metodo("aplicarCortesias"),
                        "Deve possuir o método aplicarCortesias()")
            );
        }

        @Test
        @DisplayName("2. Validar tipos de retorno")
        void test2() {
            assertAll(
                  () -> assertEquals(void.class,
                        metodo("checkIn", Hospede.class, Integer.class).getReturnType(),
                        "checkIn deve ser void"),
                  () -> assertTrue(Arrays.asList(
                              metodo("checkIn", Hospede.class, Integer.class).getExceptionTypes())
                              .contains(QuartoOcupadoException.class),
                        "checkIn deve declarar throws QuartoOcupadoException"),
                  () -> assertEquals(Double.class, metodo("checkOut", Hospede.class).getReturnType(),
                        "checkOut deve retornar Double"),
                  () -> assertEquals(Boolean.class,
                        metodo("transferirQuarto", Hospede.class, Integer.class).getReturnType()),
                  () -> assertEquals(Boolean.class,
                        metodo("trocarQuartos", Hospede.class, Hospede.class).getReturnType()),
                  () -> assertEquals(Integer.class,
                        metodo("buscarProximoQuartoLivre", Integer.class, Integer.class)
                              .getReturnType()),
                  () -> assertEquals(Hospede.class,
                        metodo("buscarHospedeComMaisPontos").getReturnType()),
                  () -> assertEquals(List.class, metodo("getTuristas").getReturnType()),
                  () -> assertEquals(Integer.class, metodo("aplicarCortesias").getReturnType())
            );
        }
    }

    @Nested
    @DisplayName("Hotel 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Field[] campos = Hotel.class.getDeclaredFields();

            Stream<Executable> validacoes = Arrays.stream(campos)
                  .map((campo) -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                        String.format("%s deve ser privado", campo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("2. Métodos Públicos")
        void test2() {
            Stream<Executable> validacoes = metodosEsperados().stream()
                  .map((metodo) -> () -> assertTrue(Modifier.isPublic(metodo.getModifiers()),
                        String.format("%s deve ser público", metodo.getName())));

            assertAll(validacoes);
        }

        @Test
        @DisplayName("3. Atributos devem possuir apenas getters")
        void test3() {
            Class<Hotel> clazz = Hotel.class;
            Field[] campos = clazz.getDeclaredFields();

            Stream<Executable> validacoesGetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String getName = String.format("get%s",
                            StringUtils.capitalize(campo.getName()));
                      assertDoesNotThrow(() -> {
                          Method getter = clazz.getDeclaredMethod(getName);
                          assertTrue(Modifier.isPublic(getter.getModifiers()));
                      }, String.format("Deve possuir o getter %s", getName));
                  });

            Stream<Executable> validacoesSetter = Arrays.stream(campos)
                  .map((campo) -> () -> {
                      String setName = String.format("set%s",
                            StringUtils.capitalize(campo.getName()));
                      assertThrows(NoSuchMethodException.class,
                            () -> clazz.getDeclaredMethod(setName, campo.getType()),
                            String.format("Não deve possuir o setter %s", setName));
                  });

            assertAll(Stream.concat(validacoesGetter, validacoesSetter));
        }
    }

    @Nested
    @DisplayName("Hotel 4. Construtor")
    class ConstrutorTest {

        @Test
        @DisplayName("Deve receber apenas o nome e iniciar a lista de hóspedes vazia")
        void test1() throws ReflectiveOperationException {
            Constructor<Hotel> construtor = Hotel.class.getDeclaredConstructor(String.class);
            Hotel hotel = construtor.newInstance("SPTech Hotel");

            assertAll(
                  () -> assertEquals("SPTech Hotel", getCampo(hotel, "nome")),
                  () -> assertNotNull(getHospedes(hotel), "A lista de hóspedes deve ser iniciada"),
                  () -> assertTrue(getHospedes(hotel).isEmpty(),
                        "A lista de hóspedes deve começar vazia")
            );
        }
    }

    @Nested
    @DisplayName("Hotel 5. Método - checkIn")
    class MetodoCheckInTest {

        @ParameterizedTest(name = "{index} - {5}")
        @ArgumentsSource(CheckInProvider.class)
        @DisplayName("Deve realizar o check-in corretamente")
        void test1(List<?> hospedes, Object hospede, Integer quarto, Integer expectedQuarto,
              List<?> expectedHospedes, String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));

            assertAll(
                  () -> ExceptionUtil.assertNaoLanca(
                        () -> metodo("checkIn", Hospede.class, Integer.class)
                              .invoke(obj, hospede, quarto)),
                  () -> assertEquals(expectedQuarto, getCampo(hospede, "quarto"),
                        "Quarto do hóspede incorreto após o check-in"),
                  () -> EqualsUtil.assertListEquals(expectedHospedes, getHospedes(obj))
            );
        }

        @ParameterizedTest(name = "{index} - {4}")
        @ArgumentsSource(CheckInExceptionProvider.class)
        @DisplayName("Deve lançar a exception correta e não alterar o hotel")
        void test2(List<?> hospedes, Object hospede, Integer quarto, Class<?> expectedException,
              String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Object quartoAntes = hospede == null ? null : getCampo(hospede, "quarto");

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(expectedException,
                        () -> metodo("checkIn", Hospede.class, Integer.class)
                              .invoke(obj, hospede, quarto)),
                  () -> EqualsUtil.assertListEquals(hospedes, getHospedes(obj),
                        "A lista de hóspedes não deve ser alterada"),
                  () -> {
                      if (hospede != null) {
                          assertEquals(quartoAntes, getCampo(hospede, "quarto"),
                                "O quarto do hóspede não deve ser alterado");
                      }
                  }
            );
        }
    }

    @Nested
    @DisplayName("Hotel 6. Método - checkOut")
    class MetodoCheckOutTest {

        @ParameterizedTest(name = "{index} - {4}")
        @ArgumentsSource(CheckOutProvider.class)
        @DisplayName("Deve realizar o check-out corretamente")
        void test1(List<?> hospedes, Object hospede, List<?> expectedHospedes,
              Double expectedResultado, String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Double resposta = (Double) metodo("checkOut", Hospede.class).invoke(obj, hospede);

            assertAll(
                  () -> assertNotNull(resposta, "Não deve retornar null"),
                  () -> assertEquals(expectedResultado, resposta, 0.01),
                  () -> assertNull(getCampo(hospede, "quarto"),
                        "O quarto do hóspede deve ser null após o check-out"),
                  () -> EqualsUtil.assertListEquals(expectedHospedes, getHospedes(obj))
            );
        }

        @Test
        @DisplayName("Deve lançar HospedeForaDoHotelException quando o hóspede não está no hotel")
        void test2() throws ReflectiveOperationException {
            Object leo = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);
            Object bia = TuristaFactory.build("Bia", "444", 404, 1, 0.0, false, false);
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(List.of(leo)));

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(HospedeForaDoHotelException.class,
                        () -> metodo("checkOut", Hospede.class).invoke(obj, bia)),
                  () -> ExceptionUtil.assertLancaExatamente(HospedeForaDoHotelException.class,
                        () -> metodo("checkOut", Hospede.class).invoke(obj, (Object) null)),
                  () -> assertEquals(404, getCampo(bia, "quarto"),
                        "O quarto do hóspede não deve ser alterado"),
                  () -> EqualsUtil.assertListEquals(List.of(leo), getHospedes(obj))
            );
        }
    }

    @Nested
    @DisplayName("Hotel 7. Método - verificarDisponibilidadeDoQuarto")
    class MetodoVerificarDisponibilidadeDoQuartoTest {

        @ParameterizedTest(name = "{index} - {3}")
        @ArgumentsSource(VerificarDisponibilidadeDoQuartoProvider.class)
        @DisplayName("Deve verificar a disponibilidade do quarto corretamente")
        void test1(List<?> hospedes, Integer quarto, Boolean expected, String cenario)
              throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Object resposta = metodo("verificarDisponibilidadeDoQuarto", Integer.class)
                  .invoke(obj, quarto);

            assertEquals(expected, resposta);
        }

        @ParameterizedTest(name = "{index} - quarto = {0}")
        @NullSource
        @ValueSource(ints = {0, -1})
        @DisplayName("Deve lançar QuartoInvalidoException para um quarto inválido")
        void test2(Integer quarto) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>());

            ExceptionUtil.assertLancaExatamente(QuartoInvalidoException.class,
                  () -> metodo("verificarDisponibilidadeDoQuarto", Integer.class)
                        .invoke(obj, quarto));
        }
    }

    @Nested
    @DisplayName("Hotel 8. Método - transferirQuarto")
    class MetodoTransferirQuartoTest {

        @ParameterizedTest(name = "{index} - {5}")
        @ArgumentsSource(TransferirQuartoProvider.class)
        @DisplayName("Deve transferir o hóspede de quarto corretamente")
        void test1(List<?> hospedes, Object hospede, Integer novoQuarto, Object expected,
              Integer expectedQuarto, String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Executable chamada = () -> metodo("transferirQuarto", Hospede.class, Integer.class)
                  .invoke(obj, hospede, novoQuarto);

            assertAll(
                  () -> {
                      if (expected instanceof Class<?> exception) {
                          ExceptionUtil.assertLancaExatamente(exception, chamada);
                      } else {
                          assertEquals(expected, metodo("transferirQuarto", Hospede.class,
                                Integer.class).invoke(obj, hospede, novoQuarto));
                      }
                  },
                  () -> assertEquals(expectedQuarto, getCampo(hospede, "quarto"),
                        "Quarto do hóspede incorreto após a transferência")
            );
        }
    }

    @Nested
    @DisplayName("Hotel 9. Método - trocarQuartos")
    class MetodoTrocarQuartosTest {

        @ParameterizedTest(name = "{index} - {6}")
        @ArgumentsSource(TrocarQuartosProvider.class)
        @DisplayName("Deve trocar os quartos corretamente")
        void test1(List<?> hospedes, Object hospedeA, Object hospedeB, Object expected,
              Integer expectedQuartoA, Integer expectedQuartoB, String cenario)
              throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));

            assertAll(
                  () -> {
                      if (expected instanceof Class<?> exception) {
                          ExceptionUtil.assertLancaExatamente(exception,
                                () -> metodo("trocarQuartos", Hospede.class, Hospede.class)
                                      .invoke(obj, hospedeA, hospedeB));
                      } else {
                          assertEquals(expected, metodo("trocarQuartos", Hospede.class,
                                Hospede.class).invoke(obj, hospedeA, hospedeB));
                      }
                  },
                  () -> assertEquals(expectedQuartoA, getCampo(hospedeA, "quarto"),
                        "Quarto do primeiro hóspede incorreto"),
                  () -> assertEquals(expectedQuartoB, getCampo(hospedeB, "quarto"),
                        "Quarto do segundo hóspede incorreto")
            );
        }
    }

    @Nested
    @DisplayName("Hotel 10. Método - buscarProximoQuartoLivre")
    class MetodoBuscarProximoQuartoLivreTest {

        @ParameterizedTest(name = "{index} - {4}")
        @ArgumentsSource(BuscarProximoQuartoLivreProvider.class)
        @DisplayName("Deve buscar o próximo quarto livre do intervalo")
        void test1(List<?> hospedes, Integer inicio, Integer fim, Object expected,
              String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Executable chamada = () -> metodo("buscarProximoQuartoLivre", Integer.class,
                  Integer.class).invoke(obj, inicio, fim);

            if (expected instanceof Class<?> exception) {
                ExceptionUtil.assertLancaExatamente(exception, chamada);
            } else {
                assertEquals(expected, metodo("buscarProximoQuartoLivre", Integer.class,
                      Integer.class).invoke(obj, inicio, fim));
            }
        }
    }

    @Nested
    @DisplayName("Hotel 11. Método - buscarHospedeComMaisPontos")
    class MetodoBuscarHospedeComMaisPontosTest {

        @ParameterizedTest(name = "{index} - {2}")
        @ArgumentsSource(BuscarHospedeComMaisPontosProvider.class)
        @DisplayName("Deve retornar o hóspede com mais pontos de fidelidade")
        void test1(List<?> hospedes, Object expected, String cenario)
              throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Object resposta = metodo("buscarHospedeComMaisPontos").invoke(obj);

            assertSame(expected, resposta);
        }
    }

    @Nested
    @DisplayName("Hotel 12. Método - getTuristas")
    class MetodoGetTuristasTest {

        @ParameterizedTest(name = "{index} - {2}")
        @ArgumentsSource(GetTuristasProvider.class)
        @DisplayName("Deve retornar a lista de turistas corretamente")
        void test1(List<?> hospedes, List<?> expected, String cenario)
              throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            List<?> resposta = (List<?>) metodo("getTuristas").invoke(obj);

            assertNotNull(resposta, "Não deve retornar null");
            EqualsUtil.assertListEquals(expected, resposta);
        }
    }

    @Nested
    @DisplayName("Hotel 13. Método - aplicarCortesias")
    class MetodoAplicarCortesiasTest {

        @ParameterizedTest(name = "{index} - {3}")
        @ArgumentsSource(AplicarCortesiasProvider.class)
        @DisplayName("Deve aplicar as cortesias e retornar quantos hóspedes foram beneficiados")
        void test1(List<?> hospedes, Integer expected, List<List<?>> estadoEsperado,
              String cenario) throws ReflectiveOperationException {
            Object obj = HotelFactory.build("SPTech Hotel", new ArrayList<>(hospedes));
            Object resposta = metodo("aplicarCortesias").invoke(obj);

            Stream<Executable> validacoesEstado = estadoEsperado.stream()
                  .map((linha) -> () -> assertEquals(linha.get(2),
                        getCampo(linha.get(0), (String) linha.get(1)),
                        String.format("Valor incorreto de %s em %s", linha.get(1),
                              getCampo(linha.get(0), "nome"))));

            assertAll(
                  () -> assertEquals(expected, resposta,
                        "Quantidade de cortesias aplicadas incorreta"),
                  () -> assertAll(validacoesEstado),
                  () -> EqualsUtil.assertListEquals(hospedes, getHospedes(obj),
                        "A lista de hóspedes não deve ser alterada")
            );
        }

        @Test
        @DisplayName("Não deve aplicar a mesma cortesia duas vezes")
        void test2() throws ReflectiveOperationException {
            Object turista = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, true);
            Object executivo = ExecutivoFactory.build("Leo", "444", 201, 1, 0.0, true, false,
                  true);
            Object obj = HotelFactory.build("SPTech Hotel",
                  new ArrayList<>(List.of(turista, executivo)));

            Object primeira = metodo("aplicarCortesias").invoke(obj);
            Object segunda = metodo("aplicarCortesias").invoke(obj);

            assertAll(
                  () -> assertEquals(2, primeira, "A primeira chamada deve aplicar 2 cortesias"),
                  () -> assertEquals(0, segunda,
                        "A segunda chamada não deve aplicar nenhuma cortesia")
            );
        }
    }
}
