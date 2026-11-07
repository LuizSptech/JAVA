package school.sptech.provider.financiamento;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.FinanciamentoFake;
import school.sptech.provider.veiculo.VeiculoArgumentHelper;

public class CalcularValorAPagarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws ReflectiveOperationException {
        List<Arguments> baseTests = VeiculoArgumentHelper.veiculosValorFinal;
        if (baseTests.isEmpty()) {
            throw new IllegalArgumentException(
                  "Para calcular o valor a pagar, a classe Veiculo deve estar completa");
        }
        int parcelas = 100;
        int[] parcelasPagas = {1, 12, 120, 240, 600};

        return baseTests.stream()
              .flatMap(base -> {
                  Object veiculo = base.get()[0];
                  Double valorFinal = (Double) base.get()[1];
                  return Arrays.stream(parcelasPagas)
                        .mapToObj(qtdparcelasPagas -> {
                            Double valorFinanciamento = valorFinal * FinanciamentoFake.PORCENTAGEM_FAKE;
                            Double valorParcela = valorFinanciamento / parcelas;
                            Double valorAPagar = valorFinanciamento - (valorParcela * qtdparcelasPagas);
                            return Arguments.of(
                                  parcelas, qtdparcelasPagas, veiculo, valorAPagar
                            );
                        });
              });
    }
}
