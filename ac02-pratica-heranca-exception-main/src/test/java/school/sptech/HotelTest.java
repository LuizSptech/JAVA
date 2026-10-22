package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static school.sptech.util.ReflectionUtil.assertLanca;
import static school.sptech.util.ReflectionUtil.construir;
import static school.sptech.util.ReflectionUtil.invocar;
import static school.sptech.util.ReflectionUtil.metodo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.HospedeNaoEncontradoException;
import school.sptech.exceptions.LimiteReservasExcedidoException;
import school.sptech.exceptions.QuartoIndisponivelException;
import school.sptech.factory.HospedeFactory;
import school.sptech.factory.HospedeVipFactory;
import school.sptech.factory.HotelFactory;
import school.sptech.factory.ReservaFactory;
import school.sptech.hospede.Hospede;
import school.sptech.hospede.HospedeVip;
import school.sptech.provider.InteiroInvalidoProvider;
import school.sptech.provider.NullOrBlankProvider;
import school.sptech.provider.ReservaInvalidaProvider;
import school.sptech.provider.hospedevip.PercentualInvalidoProvider;
import school.sptech.provider.hotel.AtributosHotelProvider;
import school.sptech.provider.hotel.GettersSettersHotelProvider;
import school.sptech.provider.hotel.BuscarPorCpfProvider;
import school.sptech.provider.hotel.BuscarPorTipoProvider;
import school.sptech.provider.hotel.CadastrarHospedeProvider;
import school.sptech.provider.hotel.CpfInexistenteProvider;
import school.sptech.provider.hotel.CpfRepetidoProvider;
import school.sptech.provider.hotel.DadosReservaProvider;
import school.sptech.provider.hotel.DisponibilidadeProvider;
import school.sptech.provider.hotel.FaturamentoTotalProvider;
import school.sptech.provider.hotel.HospedeNoLimiteProvider;
import school.sptech.provider.hotel.HospedeQueMaisGastouProvider;
import school.sptech.provider.hotel.HospedesVipProvider;
import school.sptech.provider.hotel.MediaClassicosProvider;
import school.sptech.provider.hotel.NaoPromoverProvider;
import school.sptech.provider.hotel.NomeHotelProvider;
import school.sptech.provider.hotel.PromoverProvider;
import school.sptech.provider.hotel.QuartoOcupadoProvider;
import school.sptech.provider.hotel.ReservasPromocaoProvider;
import school.sptech.util.EncapsulationUtil;

@DisplayName("Hotel")
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class HotelTest {

    private static final Class<?>[] NOME = {String.class};
    private static final Class<?>[] CPF = {String.class};
    private static final Class<?>[] QUARTO = {Integer.class};
    private static final Class<?>[] CADASTRAR = {Hospede.class};
    private static final Class<?>[] TIPO = {String.class};
    private static final Class<?>[] RESERVAR = {String.class, Integer.class, Integer.class,
          Double.class};
    private static final Class<?>[] PROMOVER = {String.class, Double.class};

    private static Hotel hotel(Object... hospedes) throws ReflectiveOperationException {
        return HotelFactory.build("Hotel SPTech", hospedes);
    }

    private static Object campoReserva(Object reserva, String campo)
          throws ReflectiveOperationException {
        return ReservaFactory.campos().get(campo).get(reserva);
    }

    private static List<Object> estados(Object hospede) throws ReflectiveOperationException {
        List<Object> estados = new ArrayList<>();

        for (Object reserva : HospedeFactory.reservas(hospede)) {
            estados.add(campoReserva(reserva, "ativa"));
        }

        return estados;
    }

    private static List<Integer> indicesVip(List<Object> hospedes) {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < hospedes.size(); i++) {
            if (hospedes.get(i) instanceof HospedeVip) {
                indices.add(i);
            }
        }

        return indices;
    }

    private static List<Object> porIndice(List<Object> hospedes, List<Integer> indices) {
        List<Object> resultado = new ArrayList<>();

        for (Integer indice : indices) {
            resultado.add(hospedes.get(indice));
        }

        return resultado;
    }

    private static void assertMesmosHospedes(List<?> esperado, Object resultado) {
        assertNotNull(resultado, "Deve retornar uma lista");
        List<?> lista = (List<?>) resultado;

        assertEquals(esperado.size(), lista.size(), "Quantidade de hospedes");

        for (int i = 0; i < esperado.size(); i++) {
            assertSame(esperado.get(i), lista.get(i), "Hospede na posicao " + i);
        }
    }

    // Total gasto: 240.0 + 1030.0 + 954.0 = 2224.0
    private static Hospede hospedeApto(String nome, String cpf)
          throws ReflectiveOperationException {
        return HospedeFactory.build(nome, cpf,
              ReservaFactory.finalizada(101, 2, 100.0),
              ReservaFactory.finalizada(202, 7, 150.0),
              ReservaFactory.finalizada(101, 12, 90.0));
    }

    @Nested
    @Order(1)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 01. Atributos e Encapsulamento")
    class AtributosTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(AtributosHotelProvider.class)
        @Order(1)
        @DisplayName("1. Atributo deve existir com o tipo correto")
        void test1(String nome, Class<?> tipo) {
            EncapsulationUtil.validarAtributoExiste(Hotel.class, nome, tipo);
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(AtributosHotelProvider.class)
        @Order(2)
        @DisplayName("2. Atributo deve ser privado")
        void test2(String nome) {
            EncapsulationUtil.validarModificadorAtributo(Hotel.class, nome, false);
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(GettersSettersHotelProvider.class)
        @Order(3)
        @DisplayName("3. Atributo deve possuir os getters e setters esperados")
        void test3(String nome, Class<?> tipo, Boolean comGetter, Boolean comSetter) {
            EncapsulationUtil.validarGetterSetter(Hotel.class, nome, tipo, comGetter, comSetter);
        }
    }

    @Nested
    @Order(2)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 02. Construtor")
    class ConstrutorTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(NullOrBlankProvider.class)
        @Order(1)
        @DisplayName("1. Deve lancar DadoInvalidoException para nome invalido")
        void test1(String nome) {
            assertLanca(DadoInvalidoException.class, () -> construir(Hotel.class, NOME, nome));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(NomeHotelProvider.class)
        @Order(2)
        @DisplayName("2. Deve inicializar o nome e a lista de hospedes vazia")
        void test2(String nome) throws Throwable {
            Hotel hotel = construir(Hotel.class, NOME, nome);

            assertAll(
                  () -> assertEquals(nome, HotelFactory.campos().get("nome").get(hotel)),
                  () -> assertNotNull(HotelFactory.hospedes(hotel)),
                  () -> assertTrue(HotelFactory.hospedes(hotel).isEmpty())
            );
        }
    }

    @Nested
    @Order(3)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 03. Metodo - buscarPorCpf")
    class BuscarPorCpfTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(BuscarPorCpfProvider.class)
        @Order(1)
        @DisplayName("1. Deve retornar o hospede com o cpf informado")
        void test1(Object[] hospedes, String cpf, Integer indice) throws Throwable {
            Hotel hotel = hotel(hospedes);

            assertSame(hospedes[indice], invocar(hotel, "buscarPorCpf", CPF, cpf));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(CpfInexistenteProvider.class)
        @Order(2)
        @DisplayName("2. Deve lancar HospedeNaoEncontradoException quando nao encontrar")
        void test2(Object[] hospedes, String cpf) throws Throwable {
            Hotel hotel = hotel(hospedes);

            assertLanca(HospedeNaoEncontradoException.class,
                  () -> invocar(hotel, "buscarPorCpf", CPF, cpf));
        }
    }

    @Nested
    @Order(4)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 04. Metodo - calcularFaturamentoTotal")
    class CalcularFaturamentoTotalTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(FaturamentoTotalProvider.class)
        @Order(1)
        @DisplayName("1. Deve somar o total gasto de todos os hospedes (aplicando desconto Vip)")
        void test1(Object[] hospedes, Double esperado) throws Throwable {
            Hotel hotel = hotel(hospedes);

            assertEquals(esperado, (Double) invocar(hotel, "calcularFaturamentoTotal"), 0.01);

            HotelFactory.hospedes(hotel).add(HospedeFactory.build("Novo", "999",
                  ReservaFactory.ativa(999, 3, 100.0)));

            assertEquals(esperado + 340.0, (Double) invocar(hotel, "calcularFaturamentoTotal"),
                  0.01, "Deve considerar o novo hospede que gastou 340.0");
        }
    }

    @Nested
    @Order(5)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 05. Metodo - buscarHospedeQueMaisGastou")
    class BuscarHospedeQueMaisGastouTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(HospedeQueMaisGastouProvider.class)
        @Order(1)
        @DisplayName("1. Deve retornar o hospede que mais gastou (null quando nao houver)")
        void test1(Object[] hospedes, Integer indice) throws Throwable {
            Hotel hotel = hotel(hospedes);
            Object resultado = invocar(hotel, "buscarHospedeQueMaisGastou");

            if (indice == null) {
                assertNull(resultado);
            } else {
                assertSame(hospedes[indice], resultado);
            }

            Hospede maior = HospedeFactory.build("Novo", "999",
                  ReservaFactory.finalizada(999, 12, 1000.0));
            HotelFactory.hospedes(hotel).add(maior);

            assertSame(maior, invocar(hotel, "buscarHospedeQueMaisGastou"),
                  "Deve retornar o novo hospede que gastou 10600.0");
        }
    }

    @Nested
    @Order(6)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 06. Metodo - buscarHospedesVip")
    class BuscarHospedesVipTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(HospedesVipProvider.class)
        @Order(1)
        @DisplayName("1. Deve retornar apenas os hospedes Vip na ordem de cadastro")
        void test1(Object[] hospedes, List<Integer> indices) throws Throwable {
            Hotel hotel = hotel(hospedes);
            List<Object> lista = HotelFactory.hospedes(hotel);

            assertMesmosHospedes(porIndice(lista, indices), invocar(hotel, "buscarHospedesVip"));

            lista.add(HospedeVipFactory.build("Novo Vip", "999", 10.0));

            assertMesmosHospedes(porIndice(lista, indicesVip(lista)),
                  invocar(hotel, "buscarHospedesVip"));
        }
    }

    @Nested
    @Order(7)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 07. Metodo - cadastrarHospede")
    class CadastrarHospedeTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(CadastrarHospedeProvider.class)
        @Order(1)
        @DisplayName("1. Deve adicionar o novo hospede")
        void test1(Object[] iniciais, Object novo) throws Throwable {
            Hotel hotel = hotel(iniciais);

            invocar(hotel, "cadastrarHospede", CADASTRAR, novo);

            List<Object> esperado = new ArrayList<>(Arrays.asList(iniciais));
            esperado.add(novo);

            assertMesmosHospedes(esperado, HotelFactory.hospedes(hotel));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(CpfRepetidoProvider.class)
        @Order(2)
        @DisplayName("2. Nao deve cadastrar hospede com cpf ja cadastrado")
        void test2(Object[] iniciais, Object repetido, Object novo) throws Throwable {
            Hotel hotel = hotel(iniciais);

            assertDoesNotThrow(() -> invocar(hotel, "cadastrarHospede", CADASTRAR, repetido));
            assertMesmosHospedes(Arrays.asList(iniciais), HotelFactory.hospedes(hotel));

            invocar(hotel, "cadastrarHospede", CADASTRAR, novo);

            List<Object> esperado = new ArrayList<>(Arrays.asList(iniciais));
            esperado.add(novo);

            assertMesmosHospedes(esperado, HotelFactory.hospedes(hotel));
        }
    }

    @Nested
    @Order(8)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 08. Metodo - buscarPorTipo")
    class BuscarPorTipoTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(BuscarPorTipoProvider.class)
        @Order(1)
        @DisplayName("1. Deve retornar os hospedes do tipo informado na ordem de cadastro")
        void test1(Object[] hospedes, String tipo, List<Integer> indices) throws Throwable {
            Hotel hotel = hotel(hospedes);
            List<Object> lista = HotelFactory.hospedes(hotel);

            assertMesmosHospedes(porIndice(lista, indices),
                  invocar(hotel, "buscarPorTipo", TIPO, tipo));

            lista.add(HospedeVipFactory.build("Novo Vip", "998", 10.0));
            lista.add(HospedeFactory.build("Novo Classico", "999"));

            assertMesmosHospedes(porIndice(lista, indicesVip(lista)),
                  invocar(hotel, "buscarPorTipo", TIPO, "vip"));
        }
    }

    @Nested
    @Order(9)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 09. Metodo - verificarDisponibilidade")
    class VerificarDisponibilidadeTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(InteiroInvalidoProvider.class)
        @Order(1)
        @DisplayName("1. Deve lancar DadoInvalidoException para numeroQuarto invalido")
        void test1(Integer numeroQuarto) throws Throwable {
            Hotel hotel = hotel(HospedeFactory.build("Ana", "111",
                  ReservaFactory.ativa(101, 2, 100.0)));

            assertLanca(DadoInvalidoException.class,
                  () -> invocar(hotel, "verificarDisponibilidade", QUARTO, numeroQuarto));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(DisponibilidadeProvider.class)
        @Order(2)
        @DisplayName("2. Deve lancar QuartoIndisponivelException apenas para quarto com reserva ativa")
        void test2(Object[] hospedes, Integer livre, Integer ocupado) throws Throwable {
            Method m = metodo(Hotel.class, "verificarDisponibilidade", QUARTO);
            assertTrue(Arrays.asList(m.getExceptionTypes())
                        .contains(QuartoIndisponivelException.class),
                  "Metodo verificarDisponibilidade deve declarar throws QuartoIndisponivelException");

            Hotel hotel = hotel(hospedes);

            assertAll(
                  () -> assertDoesNotThrow(
                        () -> invocar(hotel, "verificarDisponibilidade", QUARTO, livre),
                        "O quarto " + livre + " esta livre"),
                  () -> assertLanca(QuartoIndisponivelException.class,
                        () -> invocar(hotel, "verificarDisponibilidade", QUARTO, ocupado))
            );
        }
    }

    @Nested
    @Order(10)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 10. Metodo - reservar")
    class ReservarTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(DadosReservaProvider.class)
        @Order(1)
        @DisplayName("1. Deve criar a reserva para o hospede")
        void test1(Integer numeroQuarto, Integer qtdDiarias, Double valorDiaria)
              throws Throwable {
            Hospede ana = HospedeFactory.build("Ana", "111");
            Hospede bruno = HospedeFactory.build("Bruno", "222",
                  ReservaFactory.ativa(999, 2, 100.0));
            Hotel hotel = hotel(bruno, ana);

            invocar(hotel, "reservar", RESERVAR, "111", numeroQuarto, qtdDiarias, valorDiaria);

            List<Object> reservas = HospedeFactory.reservas(ana);

            assertEquals(1, reservas.size());
            assertAll(
                  () -> assertEquals(numeroQuarto, campoReserva(reservas.get(0), "numeroQuarto")),
                  () -> assertEquals(qtdDiarias, campoReserva(reservas.get(0), "qtdDiarias")),
                  () -> assertEquals(valorDiaria, campoReserva(reservas.get(0), "valorDiaria")),
                  () -> assertEquals(true, campoReserva(reservas.get(0), "ativa")),
                  () -> assertEquals(1, HospedeFactory.reservas(bruno).size())
            );
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(QuartoOcupadoProvider.class)
        @Order(2)
        @DisplayName("2. Quarto ocupado: deve tratar a excecao e nao reservar")
        void test2(Object ocupante, Integer quarto) throws Throwable {
            Method m = metodo(Hotel.class, "reservar", RESERVAR);
            assertFalse(Arrays.asList(m.getExceptionTypes())
                        .contains(QuartoIndisponivelException.class),
                  "Metodo reservar deve tratar a QuartoIndisponivelException com try/catch");

            Hospede ana = HospedeFactory.build("Ana", "111");
            Hotel hotel = hotel(ocupante, ana);
            List<Object> reservasOcupante = new ArrayList<>(HospedeFactory.reservas(ocupante));
            List<Object> estadosOcupante = estados(ocupante);

            assertDoesNotThrow(
                  () -> invocar(hotel, "reservar", RESERVAR, "111", quarto, 3, 200.0));

            assertAll(
                  () -> assertTrue(HospedeFactory.reservas(ana).isEmpty()),
                  () -> assertEquals(reservasOcupante, HospedeFactory.reservas(ocupante)),
                  () -> assertEquals(estadosOcupante, estados(ocupante))
            );

            invocar(hotel, "reservar", RESERVAR, "111", 888, 3, 200.0);
            assertEquals(1, HospedeFactory.reservas(ana).size(),
                  "Deve reservar normalmente quando o quarto estiver livre");
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(CpfInexistenteProvider.class)
        @Order(3)
        @DisplayName("3. Deve lancar HospedeNaoEncontradoException para cpf inexistente")
        void test3(Object[] hospedes, String cpf) throws Throwable {
            Hotel hotel = hotel(hospedes);

            assertLanca(HospedeNaoEncontradoException.class,
                  () -> invocar(hotel, "reservar", RESERVAR, cpf, 888, 3, 200.0));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(HospedeNoLimiteProvider.class)
        @Order(4)
        @DisplayName("4. Deve propagar LimiteReservasExcedidoException do hospede")
        void test4(Object hospede, String cpf) throws Throwable {
            Hotel hotel = hotel(hospede);
            Integer antes = HospedeFactory.reservas(hospede).size();

            assertLanca(LimiteReservasExcedidoException.class,
                  () -> invocar(hotel, "reservar", RESERVAR, cpf, 888, 3, 200.0));
            assertEquals(antes, HospedeFactory.reservas(hospede).size());
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(ReservaInvalidaProvider.class)
        @Order(5)
        @DisplayName("5. Deve propagar DadoInvalidoException para dados invalidos")
        void test5(Integer numeroQuarto, Integer qtdDiarias, Double valorDiaria)
              throws Throwable {
            Hospede ana = HospedeFactory.build("Ana", "111");
            Hotel hotel = hotel(ana);

            assertLanca(DadoInvalidoException.class,
                  () -> invocar(hotel, "reservar", RESERVAR, "111", numeroQuarto, qtdDiarias,
                        valorDiaria));
            assertTrue(HospedeFactory.reservas(ana).isEmpty());
        }
    }

    @Nested
    @Order(11)
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Hotel 11. Metodo - promoverParaVip")
    class PromoverParaVipTest {

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(PromoverProvider.class)
        @Order(1)
        @DisplayName("1. Deve substituir o hospede por um HospedeVip no final da lista")
        void test1(String nome, String cpf, Double percentual) throws Throwable {
            Hospede apto = hospedeApto(nome, cpf);
            Hospede bruno = HospedeFactory.build("Bruno", "222");
            Hotel hotel = hotel(apto, bruno);

            invocar(hotel, "promoverParaVip", PROMOVER, cpf, percentual);

            List<Object> hospedes = HotelFactory.hospedes(hotel);
            Map<String, Field> campos = HospedeVipFactory.campos();

            assertEquals(2, hospedes.size());
            Object vip = hospedes.get(1);

            assertAll(
                  () -> assertSame(bruno, hospedes.get(0)),
                  () -> assertFalse(hospedes.contains(apto)),
                  () -> assertInstanceOf(HospedeVip.class, vip),
                  () -> assertEquals(nome, campos.get("nome").get(vip)),
                  () -> assertEquals(cpf, campos.get("cpf").get(vip)),
                  () -> assertEquals(percentual, campos.get("percentualDesconto").get(vip))
            );
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(ReservasPromocaoProvider.class)
        @Order(2)
        @DisplayName("2. Deve copiar as reservas (novos objetos, mesmos dados e mesmo estado)")
        void test2(Object[] antigas, Integer quantidade) throws Throwable {
            Hotel hotel = hotel(HospedeFactory.build("Ana", "111", antigas));

            invocar(hotel, "promoverParaVip", PROMOVER, "111", 15.0);

            Object vip = HotelFactory.hospedes(hotel).get(0);
            assertInstanceOf(HospedeVip.class, vip);

            List<Object> novas = HospedeFactory.reservas(vip);
            assertEquals(quantidade, novas.size());

            for (int i = 0; i < quantidade; i++) {
                Object antiga = antigas[i];
                Object nova = novas.get(i);

                assertAll(
                      () -> assertNotSame(antiga, nova,
                            "As reservas devem ser criadas pelo novo hospede (composicao)"),
                      () -> assertEquals(campoReserva(antiga, "numeroQuarto"),
                            campoReserva(nova, "numeroQuarto")),
                      () -> assertEquals(campoReserva(antiga, "qtdDiarias"),
                            campoReserva(nova, "qtdDiarias")),
                      () -> assertEquals(campoReserva(antiga, "valorDiaria"),
                            campoReserva(nova, "valorDiaria")),
                      () -> assertEquals(campoReserva(antiga, "ativa"),
                            campoReserva(nova, "ativa"))
                );
            }
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(NaoPromoverProvider.class)
        @Order(3)
        @DisplayName("3. Deve promover apenas o hospede que atende todas as condicoes")
        void test3(Object inelegivel, Object[] outros, String cpfInelegivel) throws Throwable {
            List<Object> iniciais = new ArrayList<>();
            iniciais.add(inelegivel);
            iniciais.addAll(Arrays.asList(outros));
            iniciais.add(hospedeApto("Apto", "111"));

            Hotel hotel = hotel(iniciais.toArray());

            invocar(hotel, "promoverParaVip", PROMOVER, cpfInelegivel, 30.0);
            assertMesmosHospedes(iniciais, HotelFactory.hospedes(hotel));

            invocar(hotel, "promoverParaVip", PROMOVER, "111", 15.0);

            List<Object> hospedes = HotelFactory.hospedes(hotel);
            Object ultimo = hospedes.get(hospedes.size() - 1);

            assertAll(
                  () -> assertEquals(iniciais.size(), hospedes.size()),
                  () -> assertSame(inelegivel, hospedes.get(0),
                        "O hospede que nao atende as condicoes deve ser mantido"),
                  () -> assertInstanceOf(HospedeVip.class, ultimo,
                        "O hospede que atende as condicoes deve ser promovido"),
                  () -> assertEquals("111", HospedeFactory.cpf(ultimo))
            );
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(PercentualInvalidoProvider.class)
        @Order(4)
        @DisplayName("4. Percentual invalido deve lancar DadoInvalidoException e manter o hospede")
        void test4(Double percentual) throws Throwable {
            Hospede apto = hospedeApto("Ana", "111");
            Hotel hotel = hotel(apto);

            assertLanca(DadoInvalidoException.class,
                  () -> invocar(hotel, "promoverParaVip", PROMOVER, "111", percentual));
            assertMesmosHospedes(List.of(apto), HotelFactory.hospedes(hotel));
        }

        // Cenario unico: a busca reutiliza buscarPorCpf, cujos casos de borda ja tem bloco proprio
        @Test
        @Order(5)
        @DisplayName("5. Deve lancar HospedeNaoEncontradoException para cpf inexistente")
        void test5() throws Throwable {
            Hotel hotel = hotel(hospedeApto("Ana", "111"));

            assertLanca(HospedeNaoEncontradoException.class,
                  () -> invocar(hotel, "promoverParaVip", PROMOVER, "999", 15.0));
        }

        @ParameterizedTest(name = "{index}. {argumentSetName}")
        @ArgumentsSource(MediaClassicosProvider.class)
        @Order(6)
        @DisplayName("6. A media de gasto deve considerar apenas os hospedes classicos")
        void test6(Object[] vips, Integer quantidadeVips) throws Throwable {
            // Ana: 3 x 140.0 = 420.0 | unica classica -> media 420.0 -> 70% = 294.0
            Hospede ana = HospedeFactory.build("Ana", "333",
                  ReservaFactory.finalizada(101, 1, 100.0),
                  ReservaFactory.finalizada(102, 1, 100.0),
                  ReservaFactory.finalizada(103, 1, 100.0));
            Object[] iniciais = Arrays.copyOf(vips, vips.length + 1);
            iniciais[vips.length] = ana;
            Hotel hotel = hotel(iniciais);

            invocar(hotel, "promoverParaVip", PROMOVER, "333", 15.0);

            List<Object> hospedes = HotelFactory.hospedes(hotel);
            Object ultimo = hospedes.get(hospedes.size() - 1);

            assertEquals(quantidadeVips + 1, hospedes.size());
            for (int i = 0; i < quantidadeVips; i++) {
                assertSame(vips[i], hospedes.get(i));
            }

            assertAll(
                  () -> assertInstanceOf(HospedeVip.class, ultimo,
                        "Os hospedes Vip nao devem entrar na media"),
                  () -> assertEquals("333", HospedeFactory.cpf(ultimo))
            );
        }
    }
}
