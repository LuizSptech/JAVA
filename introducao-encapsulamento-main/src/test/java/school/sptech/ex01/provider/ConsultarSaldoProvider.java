package school.sptech.ex01.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConsultarSaldoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // qtdConsultasIni, qtdRecargasIni, saldoBilhete, bloqueado, retornoEsp, qtdConsultasEsp
        return Stream.of(
              Arguments.of(0, 0, 200.0, false, 200.0, 1),
              Arguments.of(0, 0, 0.0, false, 0.0, 1),
              Arguments.of(92, 83, 8.0, false, 8.0, 93),
              Arguments.of(34, 20, 50.0, true, 0.0, 34),    // bloqueado -> retorna 0 e nao conta
              Arguments.of(10, 5, 999.0, true, 0.0, 10),    // bloqueado com saldo alto
              Arguments.of(1, 1, 12.75, false, 12.75, 2)    // saldo decimal
        );
    }
}
