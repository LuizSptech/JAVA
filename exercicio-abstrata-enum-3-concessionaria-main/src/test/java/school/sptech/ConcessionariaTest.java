package school.sptech;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.exceptions.DocumentoInvalidoException;
import school.sptech.exceptions.RequisicaoDeFinanciamentoInvalidaException;
import school.sptech.exceptions.ValorInexistenteException;
import school.sptech.factory.ConcessionariaFactory;
import school.sptech.factory.EmpresaFactory;
import school.sptech.factory.FinanciamentoFactory;
import school.sptech.factory.FinanciamentoFake;
import school.sptech.factory.PessoaFactory;
import school.sptech.factory.VeiculoFactory;
import school.sptech.financiamento.Financiamento;
import school.sptech.financiamento.FinanciamentoPF;
import school.sptech.financiamento.FinanciamentoPJ;
import school.sptech.provider.concessionaria.BuscarPorCpfProvider;
import school.sptech.provider.concessionaria.BuscarPorNomeOuRazaoSocialProvider;
import school.sptech.provider.concessionaria.CnpjInvalidoProvider;
import school.sptech.provider.concessionaria.CpfInvalidoProvider;
import school.sptech.provider.concessionaria.RequisicaoDeFinanciamentoInvalidaProvider;
import school.sptech.provider.concessionaria.TotalValorAPagarProvider;
import school.sptech.provider.concessionaria.TotalValorPagoProvider;
import school.sptech.util.EqualsUtil;
import school.sptech.util.ExceptionUtil;

@DisplayName("Concessionaria")
public class ConcessionariaTest {

    static Object getCampo(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Field campo = Concessionaria.class.getDeclaredField(nomeCampo);
        campo.trySetAccessible();
        return campo.get(obj);
    }

    static List<?> getFinanciamentos(Object concessionaria) throws ReflectiveOperationException {
        return (List<?>) getCampo(concessionaria, "financiamentos");
    }

    static Object concessionariaVazia() throws ReflectiveOperationException {
        return ConcessionariaFactory.build("SPTech Motors", new ArrayList<>());
    }

    static Object veiculoValido() throws ReflectiveOperationException {
        return VeiculoFactory.build("AAA1111", "Fusca", "Wolkswagen", 10_000.0, 0, 0);
    }

    static Method metodo(String nome, Class<?>... parametros) throws NoSuchMethodException {
        return Concessionaria.class.getDeclaredMethod(nome, parametros);
    }

    static List<Method> metodosEsperados() {
        List<Method> metodos = new ArrayList<>();
        Object[][] assinaturas = {
              {"financiarVeiculo", new Class<?>[]{Pessoa.class, Veiculo.class, Integer.class}},
              {"financiarVeiculo", new Class<?>[]{Empresa.class, Veiculo.class, Integer.class}},
              {"buscarPorCpf", new Class<?>[]{String.class}},
              {"buscarPorNomeOuRazaoSocial", new Class<?>[]{String.class}},
              {"pagarParcela", new Class<?>[]{Double.class, String.class}},
              {"totalValorPago", new Class<?>[]{}},
              {"totalValorAPagar", new Class<?>[]{}}
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
    @DisplayName("Concessionaria 1. Atributos")
    class AtributosTest {

        @Test
        @DisplayName("1. Validar Atributos")
        void test1() {
            Class<Concessionaria> clazz = Concessionaria.class;

            assertAll(
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("nome"),
                        "Deve possuir o atributo nome"),
                  () -> assertDoesNotThrow(() -> clazz.getDeclaredField("financiamentos"),
                        "Deve possuir o atributo financiamentos")
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 2. Métodos")
    class MetodosTest {

        @Test
        @DisplayName("1. Validar Métodos")
        void test1() {
            assertAll(
                  () -> assertDoesNotThrow(
                        () -> metodo("financiarVeiculo", Pessoa.class, Veiculo.class,
                              Integer.class),
                        "Deve possuir o método financiarVeiculo(Pessoa, Veiculo, Integer)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("financiarVeiculo", Empresa.class, Veiculo.class,
                              Integer.class),
                        "Deve possuir o método financiarVeiculo(Empresa, Veiculo, Integer)"),
                  () -> assertDoesNotThrow(() -> metodo("buscarPorCpf", String.class),
                        "Deve possuir o método buscarPorCpf(String)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("buscarPorNomeOuRazaoSocial", String.class),
                        "Deve possuir o método buscarPorNomeOuRazaoSocial(String)"),
                  () -> assertDoesNotThrow(
                        () -> metodo("pagarParcela", Double.class, String.class),
                        "Deve possuir o método pagarParcela(Double, String)"),
                  () -> assertDoesNotThrow(() -> metodo("totalValorPago"),
                        "Deve possuir o método totalValorPago()"),
                  () -> assertDoesNotThrow(() -> metodo("totalValorAPagar"),
                        "Deve possuir o método totalValorAPagar()")
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 3. Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("1. Atributos Privados")
        void test1() {
            Field[] campos = Concessionaria.class.getDeclaredFields();

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
    }

    @Nested
    @DisplayName("Concessionaria 4. Método - financiarVeiculo (Pessoa)")
    class MetodoFinanciarVeiculoPessoaTest {

        Method financiarVeiculo() throws NoSuchMethodException {
            return metodo("financiarVeiculo", Pessoa.class, Veiculo.class, Integer.class);
        }

        @Test
        @DisplayName("Deve lançar ValorInexistenteException quando pessoa, veículo ou parcelas forem nulos")
        void test1() throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object pessoa = PessoaFactory.build("Nome", "12345678901");
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, null, veiculo, 10)),
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, pessoa, null, 10)),
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, pessoa, veiculo, null)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @ParameterizedTest(name = "{index} - parcelas = {0}")
        @ArgumentsSource(RequisicaoDeFinanciamentoInvalidaProvider.class)
        @DisplayName("Deve lançar RequisicaoDeFinanciamentoInvalidaException quando a quantidade de parcelas for inválida")
        void test2(Integer parcelas) throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object pessoa = PessoaFactory.build("Pessoa teste", "12345678901");
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(
                        RequisicaoDeFinanciamentoInvalidaException.class,
                        () -> metodo.invoke(obj, pessoa, veiculo, parcelas)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @ParameterizedTest(name = "{index} - cpf = \"{0}\"")
        @ArgumentsSource(CpfInvalidoProvider.class)
        @DisplayName("Deve lançar DocumentoInvalidoException quando o CPF for inválido")
        void test3(String cpf) throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object pessoa = PessoaFactory.build("Nome", cpf);
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(DocumentoInvalidoException.class,
                        () -> metodo.invoke(obj, pessoa, veiculo, 10)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @Test
        @DisplayName("Deve adicionar o financiamento na lista de financiamentos")
        void test4() throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object pessoa = PessoaFactory.build("Nome", "12345678901");
            Object veiculo = veiculoValido();

            ExceptionUtil.assertNaoLanca(() -> metodo.invoke(obj, pessoa, veiculo, 10));

            List<?> financiamentos = getFinanciamentos(obj);
            assertEquals(1, financiamentos.size(),
                  "Deve adicionar o financiamento na lista de financiamentos");

            Object financiamento = financiamentos.get(0);
            Field fieldVeiculo = Financiamento.class.getDeclaredField("veiculo");
            fieldVeiculo.trySetAccessible();
            Field fieldPessoa = FinanciamentoPF.class.getDeclaredField("pessoa");
            fieldPessoa.trySetAccessible();

            assertAll(
                  () -> assertTrue(FinanciamentoPF.class.isInstance(financiamento),
                        "O financiamento de uma pessoa deve ser um FinanciamentoPF"),
                  () -> assertSame(veiculo, fieldVeiculo.get(financiamento),
                        "O veículo do financiamento deve ser o mesmo veículo passado como parâmetro"),
                  () -> assertSame(pessoa, fieldPessoa.get(financiamento),
                        "A pessoa do financiamento deve ser a mesma pessoa passada como parâmetro")
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 5. Método - financiarVeiculo (Empresa)")
    class MetodoFinanciarVeiculoEmpresaTest {

        Method financiarVeiculo() throws NoSuchMethodException {
            return metodo("financiarVeiculo", Empresa.class, Veiculo.class, Integer.class);
        }

        @Test
        @DisplayName("Deve lançar ValorInexistenteException quando empresa, veículo ou parcelas forem nulos")
        void test1() throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object empresa = EmpresaFactory.build("Nome", "12345678901234", "MEI");
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, null, veiculo, 10)),
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, empresa, null, 10)),
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, empresa, veiculo, null)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @ParameterizedTest(name = "{index} - parcelas = {0}")
        @ArgumentsSource(RequisicaoDeFinanciamentoInvalidaProvider.class)
        @DisplayName("Deve lançar RequisicaoDeFinanciamentoInvalidaException quando a quantidade de parcelas for inválida")
        void test2(Integer parcelas) throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object empresa = EmpresaFactory.build("Empresa teste", "12345678901234", "MEI");
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(
                        RequisicaoDeFinanciamentoInvalidaException.class,
                        () -> metodo.invoke(obj, empresa, veiculo, parcelas)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @ParameterizedTest(name = "{index} - cnpj = \"{0}\"")
        @ArgumentsSource(CnpjInvalidoProvider.class)
        @DisplayName("Deve lançar DocumentoInvalidoException quando o CNPJ for inválido")
        void test3(String cnpj) throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object empresa = EmpresaFactory.build("Nome", cnpj, "MEI");
            Object veiculo = veiculoValido();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(DocumentoInvalidoException.class,
                        () -> metodo.invoke(obj, empresa, veiculo, 10)),
                  () -> assertTrue(getFinanciamentos(obj).isEmpty(),
                        "Nenhum financiamento deve ser adicionado")
            );
        }

        @Test
        @DisplayName("Deve adicionar o financiamento na lista de financiamentos")
        void test4() throws ReflectiveOperationException {
            Method metodo = financiarVeiculo();
            Object obj = concessionariaVazia();
            Object empresa = EmpresaFactory.build("Nome", "12345678901234", "MEI");
            Object veiculo = veiculoValido();

            ExceptionUtil.assertNaoLanca(() -> metodo.invoke(obj, empresa, veiculo, 10));

            List<?> financiamentos = getFinanciamentos(obj);
            assertEquals(1, financiamentos.size(),
                  "Deve adicionar o financiamento na lista de financiamentos");

            Object financiamento = financiamentos.get(0);
            Field fieldVeiculo = Financiamento.class.getDeclaredField("veiculo");
            fieldVeiculo.trySetAccessible();
            Field fieldEmpresa = FinanciamentoPJ.class.getDeclaredField("empresa");
            fieldEmpresa.trySetAccessible();

            assertAll(
                  () -> assertTrue(FinanciamentoPJ.class.isInstance(financiamento),
                        "O financiamento de uma empresa deve ser um FinanciamentoPJ"),
                  () -> assertSame(veiculo, fieldVeiculo.get(financiamento),
                        "O veículo do financiamento deve ser o mesmo veículo passado como parâmetro"),
                  () -> assertSame(empresa, fieldEmpresa.get(financiamento),
                        "A empresa do financiamento deve ser a mesma empresa passada como parâmetro")
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 6. Método - buscarPorCpf")
    class MetodoBuscarPorCpfTest {

        @ParameterizedTest(name = "{index} - {0}, busca por {2}")
        @ArgumentsSource(BuscarPorCpfProvider.class)
        @DisplayName("Deve retornar os financiamentos feitos pelo CPF informado")
        void test1(String nome, List<?> financiamentos, String busca, List<?> expected)
              throws ReflectiveOperationException {
            Method metodo = metodo("buscarPorCpf", String.class);

            Object obj = ConcessionariaFactory.build(nome, new ArrayList<>(financiamentos));
            Object resposta = metodo.invoke(obj, busca);

            assertAll(
                  () -> assertNotNull(resposta, "Não deve retornar null"),
                  () -> EqualsUtil.assertListEquals(expected, (List<?>) resposta)
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 7. Método - buscarPorNomeOuRazaoSocial")
    class MetodoBuscarPorNomeOuRazaoSocialTest {

        @ParameterizedTest(name = "{index} - {0}, busca por \"{2}\"")
        @ArgumentsSource(BuscarPorNomeOuRazaoSocialProvider.class)
        @DisplayName("Deve retornar os financiamentos cujo nome ou razão social contém a busca")
        void test1(String nome, List<?> financiamentos, String busca, List<?> expected)
              throws ReflectiveOperationException {
            Method metodo = metodo("buscarPorNomeOuRazaoSocial", String.class);

            Object obj = ConcessionariaFactory.build(nome, new ArrayList<>(financiamentos));
            Object resposta = metodo.invoke(obj, busca);

            assertAll(
                  () -> assertNotNull(resposta, "Não deve retornar null"),
                  () -> EqualsUtil.assertListEquals(expected, (List<?>) resposta)
            );
        }
    }

    @Nested
    @DisplayName("Concessionaria 8. Método - pagarParcela")
    class MetodoPagarParcelaTest {

        Method pagarParcela() throws NoSuchMethodException {
            return metodo("pagarParcela", Double.class, String.class);
        }

        @Test
        @DisplayName("Deve lançar ValorInexistenteException quando valor ou placa forem nulos")
        void test1() throws ReflectiveOperationException {
            Method metodo = pagarParcela();
            Object obj = concessionariaVazia();

            assertAll(
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, 10.0, null)),
                  () -> ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                        () -> metodo.invoke(obj, null, "AAA1111"))
            );
        }

        @Test
        @DisplayName("Deve lançar ValorInexistenteException quando não existir veículo com a placa")
        void test2() throws ReflectiveOperationException {
            Method metodo = pagarParcela();
            Object obj = concessionariaVazia();

            ExceptionUtil.assertLancaExatamente(ValorInexistenteException.class,
                  () -> metodo.invoke(obj, 10.0, "AAA1111"));
        }

        @Test
        @DisplayName("Deve chamar o método pagarParcela apenas do financiamento da placa informada")
        void test3() throws ReflectiveOperationException {
            Method metodo = pagarParcela();
            Object veiculo = VeiculoFactory.build("AAA1111", "modelo", "marca", 1000.0, 0, 0);
            Object outroVeiculo = VeiculoFactory.build("BBB2222", "modelo", "marca", 1000.0, 0, 0);

            Object financiamento = FinanciamentoFactory.buildComPagarParcelaFake(10, 0, veiculo);
            Object outroFinanciamento = FinanciamentoFactory.buildComPagarParcelaFake(10, 0,
                  outroVeiculo);
            Object obj = ConcessionariaFactory.build("SPTech Motors",
                  new ArrayList<>(List.of(outroFinanciamento, financiamento)));

            FinanciamentoFake.PagarParcelaFake.CHAMADAS.set(0);

            ExceptionUtil.assertNaoLanca(() -> metodo.invoke(obj, 10.0, "AAA1111"));

            assertEquals(1, FinanciamentoFake.PagarParcelaFake.CHAMADAS.get(),
                  "Deve chamar o método pagarParcela do financiamento da placa informada");
        }
    }

    @Nested
    @DisplayName("Concessionaria 9. Método - totalValorPago")
    class MetodoTotalValorPagoTest {

        @ParameterizedTest(name = "{index} - {0}, total pago esperado {2}")
        @ArgumentsSource(TotalValorPagoProvider.class)
        @DisplayName("Deve somar o valor pago de todos os financiamentos")
        void test1(String nome, List<?> financiamentos, Double expected)
              throws ReflectiveOperationException {
            Method metodo = metodo("totalValorPago");

            Object obj = ConcessionariaFactory.build(nome, new ArrayList<>(financiamentos));
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "totalValorPago deve retornar o total pago em todos os financiamentos");
        }
    }

    @Nested
    @DisplayName("Concessionaria 10. Método - totalValorAPagar")
    class MetodoTotalValorAPagarTest {

        @ParameterizedTest(name = "{index} - {0}, total a pagar esperado {2}")
        @ArgumentsSource(TotalValorAPagarProvider.class)
        @DisplayName("Deve somar o valor a pagar de todos os financiamentos")
        void test1(String nome, List<?> financiamentos, Double expected)
              throws ReflectiveOperationException {
            Method metodo = metodo("totalValorAPagar");

            Object obj = ConcessionariaFactory.build(nome, new ArrayList<>(financiamentos));
            Object resposta = metodo.invoke(obj);

            assertEquals(expected, (Double) resposta, 0.01,
                  "totalValorAPagar deve retornar o total a pagar em todos os financiamentos");
        }
    }
}
