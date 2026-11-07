package school.sptech.provider.veiculo;

import static school.sptech.provider.veiculo.VeiculoArgumentHelper.veiculosValorFinal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class VeiculoCalcularValorFinalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        if (veiculosValorFinal.isEmpty()) {
            throw new IllegalArgumentException(
                  "Para calcular o final do veiculo, a classe deve estar completa");
        }
        return veiculosValorFinal.stream();
    }
}
