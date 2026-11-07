package school.sptech.provider.financiamento.pj;

import static school.sptech.provider.veiculo.VeiculoArgumentHelper.veiculosValorFinal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.provider.veiculo.VeiculoArgumentHelper;

public class CalcularValorFinanciamentoPjProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws ReflectiveOperationException {
        if (VeiculoArgumentHelper.veiculosValorPj.isEmpty()) {
            throw new IllegalArgumentException(
                  "Para calcular o valor do financiamento pj, a classe veiculo deve estar completa");
        }
        return VeiculoArgumentHelper.veiculosValorPj.stream();
    }
}
