package school.sptech.ex01.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class RecarregarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // qtdConsultasIni, qtdRecargasIni, saldoIni, bloqueado, valorRecarga, saldoEsp, qtdRecargasEsp
        return Stream.of(
              Arguments.of(0, 0, 200.0, false, 150.0, 350.0, 1),
              Arguments.of(34, 20, 8.0, false, 5.0, 13.0, 21),     // recarga == minimo (inclusivo)
              Arguments.of(92, 83, 50.0, false, 4.99, 50.0, 83),   // abaixo do minimo -> nada muda
              Arguments.of(92, 83, 50.0, false, 2.0, 50.0, 83),    // abaixo do minimo
              Arguments.of(92, 83, 50.0, false, 0.0, 50.0, 83),    // recarga zero
              Arguments.of(92, 83, 50.0, true, 100.0, 50.0, 83),   // bloqueado -> nada muda
              Arguments.of(92, 83, 50.0, true, 2.0, 50.0, 83),     // bloqueado e abaixo do minimo
              Arguments.of(0, 0, 8.0, false, 1000.0, 1008.0, 1),   // recarga grande
              Arguments.of(0, 0, 8.0, false, 10.50, 18.50, 1),     // recarga decimal
              Arguments.of(92, 83, 50.0, false, null, 50.0, 83)    // valor null -> nada muda, sem excecao
        );
    }
}
