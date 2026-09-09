package school.sptech.ex03.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * Retorno: 1 se a data informada (parametro) for maior que a data do objeto,
 * 0 se forem iguais, -1 se a data informada for menor.
 * Qualquer parametro null -> retorna null.
 */
public class CompararDatasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // diaObj, mesObj, anoObj, diaParam, mesParam, anoParam, esperado
        return Stream.of(
              Arguments.of(10, 6, 2020, 10, 6, 2020, 0),      // iguais
              Arguments.of(10, 6, 2020, 10, 6, 2021, 1),      // ano do parametro maior
              Arguments.of(10, 6, 2020, 10, 6, 2019, -1),     // ano do parametro menor
              Arguments.of(10, 6, 2020, 10, 7, 2020, 1),      // mesmo ano, mes maior
              Arguments.of(10, 6, 2020, 10, 5, 2020, -1),     // mesmo ano, mes menor
              Arguments.of(10, 6, 2020, 11, 6, 2020, 1),      // mesmo ano/mes, dia maior
              Arguments.of(10, 6, 2020, 9, 6, 2020, -1),      // mesmo ano/mes, dia menor
              Arguments.of(1, 1, 2020, 31, 12, 2019, -1),     // ano domina (2019 < 2020)
              Arguments.of(31, 12, 2020, 1, 1, 2021, 1),      // ano domina (2021 > 2020)
              Arguments.of(15, 6, 2020, 15, 6, 2020, 0),      // iguais (outro valor)
              Arguments.of(10, 6, 2020, null, 6, 2020, null),  // dia null -> null
              Arguments.of(10, 6, 2020, 10, null, 2020, null), // mes null -> null
              Arguments.of(10, 6, 2020, 10, 6, null, null),    // ano null -> null
              Arguments.of(10, 6, 2020, null, null, null, null) // todos null -> null
        );
    }
}
