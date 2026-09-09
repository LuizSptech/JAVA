package school.sptech.ex01.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CobrarPassagemDinheiroProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // qtdPassageirosIni, valorPassagem, dinheiro, qtdPassageirosEsp
        return Stream.of(
              Arguments.of(0, 5.0, 10.0, 1),          // dinheiro > passagem
              Arguments.of(36, 4.40, 4.40, 37),       // dinheiro == passagem (limite inclusivo)
              Arguments.of(36, 5.50, 5.49, 36),       // dinheiro == passagem - 0.01 -> nao paga
              Arguments.of(10, 5.0, 0.0, 10),         // dinheiro zero
              Arguments.of(0, 4.40, 100.0, 1),        // dinheiro muito acima
              Arguments.of(2, 4.40, 4.40, 3),         // valores decimais exatos
              Arguments.of(99, 1.0, 2.0, 100),        // acumula qtdPassageiros
              Arguments.of(7, 5.0, null, 7)           // dinheiro null -> nao paga, sem excecao
        );
    }
}
