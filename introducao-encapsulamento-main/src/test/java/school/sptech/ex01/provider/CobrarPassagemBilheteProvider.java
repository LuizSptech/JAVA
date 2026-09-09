package school.sptech.ex01.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CobrarPassagemBilheteProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // qtdPassageirosIni, valorPassagem, estudante, saldoIni, bloqueado, saldoEsp, qtdPassageirosEsp
        return Stream.of(
              Arguments.of(0, 5.0, false, 200.0, false, 195.0, 1),
              Arguments.of(55, 4.40, false, 4.40, false, 0.0, 56),   // saldo == passagem (limite inclusivo)
              Arguments.of(10, 4.40, false, 4.39, false, 4.39, 10),  // saldo == passagem - 0.01 -> nao paga
              Arguments.of(29, 10.0, true, 50.0, false, 45.0, 30),   // estudante paga metade
              Arguments.of(0, 10.0, true, 5.0, false, 0.0, 1),       // estudante: saldo == meia exata
              Arguments.of(0, 10.0, true, 6.0, false, 1.0, 1),       // estudante: saldo entre meia e cheia
              Arguments.of(0, 10.0, true, 4.0, false, 4.0, 0),       // estudante: saldo < meia -> nao paga
              Arguments.of(29, 10.0, false, 999.0, true, 999.0, 29), // bilhete bloqueado
              Arguments.of(29, 10.0, true, 50.0, true, 50.0, 29),    // bloqueado + estudante
              Arguments.of(0, 5.0, false, 0.0, false, 0.0, 0),       // saldo zero
              Arguments.of(0, 4.40, false, 10.0, false, 5.60, 1),    // valores decimais
              Arguments.of(99, 1.0, false, 100.0, false, 99.0, 100)  // acumula qtdPassageiros
        );
    }
}
