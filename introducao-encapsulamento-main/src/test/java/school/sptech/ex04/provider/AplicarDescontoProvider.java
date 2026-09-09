package school.sptech.ex04.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * aplicarDesconto multiplica o valor atual pelo fator do cupom:
 * idade < 0 -> invalido, nada muda;
 * idade < 3 -> valor = 0 (100%);
 * idade < 12 -> * 0.5 (50%);
 * estudante e 12..15 -> * 0.6 (40%);
 * estudante e 16..20 -> * 0.7 (30%);
 * estudante e > 20 -> * 0.8 (20%);
 * caso contrario -> nada muda.
 * Qualquer parametro null -> valor inalterado.
 */
public class AplicarDescontoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // valorIni, idade, estudante, valorEsp
        return Stream.of(
              Arguments.of(20.0, 2, false, 0.0),
              Arguments.of(20.0, 0, true, 0.0),
              Arguments.of(20.0, 2, true, 0.0),
              Arguments.of(20.0, 5, false, 10.0),
              Arguments.of(20.0, 11, false, 10.0),
              Arguments.of(20.0, 11, true, 10.0),
              Arguments.of(20.0, 3, false, 10.0),   // limite: 3 nao e "< 3"
              Arguments.of(20.0, 12, true, 12.0),
              Arguments.of(20.0, 15, true, 12.0),
              Arguments.of(20.0, 16, true, 14.0),
              Arguments.of(20.0, 20, true, 14.0),
              Arguments.of(20.0, 21, true, 16.0),
              Arguments.of(20.0, 40, true, 16.0),
              Arguments.of(20.0, 12, false, 20.0),  // nao estudante, >= 12 -> sem desconto
              Arguments.of(20.0, 18, false, 20.0),
              Arguments.of(20.0, 30, false, 20.0),
              Arguments.of(20.0, -1, false, 20.0),  // idade negativa -> invalido
              Arguments.of(20.0, -5, true, 20.0),
              Arguments.of(50.0, 10, false, 25.0),  // outro valor inicial
              Arguments.of(20.0, null, true, 20.0), // idade null -> nada muda
              Arguments.of(20.0, 15, null, 20.0),   // estudante null -> nada muda
              Arguments.of(20.0, null, null, 20.0)  // ambos null -> nada muda
        );
    }
}
