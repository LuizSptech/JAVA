package school.sptech.ex03.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * Estado inicial do objeto em todos os cenarios: dia=10, mes=6, ano=2010.
 * Regra de bissexto assumida: ano % 4 == 0 (regra simples).
 * definirData e atomico: se qualquer valor for invalido, nenhum atributo muda.
 */
public class DefinirDataProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // diaParam, mesParam, anoParam, diaEsp, mesEsp, anoEsp
        return Stream.of(
              Arguments.of(15, 3, 2023, 15, 3, 2023),      // data comum valida
              Arguments.of(31, 1, 2023, 31, 1, 2023),      // janeiro tem 31
              Arguments.of(31, 4, 2023, 10, 6, 2010),      // abril so vai ate 30 -> invalido
              Arguments.of(30, 4, 2023, 30, 4, 2023),      // abril 30 -> valido
              Arguments.of(29, 2, 2020, 29, 2, 2020),      // 2020 bissexto -> valido
              Arguments.of(29, 2, 2021, 10, 6, 2010),      // 2021 nao bissexto -> invalido
              Arguments.of(28, 2, 2021, 28, 2, 2021),      // fevereiro 28 -> valido
              Arguments.of(29, 2, 2024, 29, 2, 2024),      // 2024 bissexto -> valido
              Arguments.of(0, 5, 2023, 10, 6, 2010),       // dia < 1 -> invalido
              Arguments.of(32, 5, 2023, 10, 6, 2010),      // dia > 31 -> invalido
              Arguments.of(10, 0, 2023, 10, 6, 2010),      // mes < 1 -> invalido
              Arguments.of(10, 13, 2023, 10, 6, 2010),     // mes > 12 -> invalido
              Arguments.of(10, 6, -1, 10, 6, 2010),        // ano negativo -> invalido
              Arguments.of(10, 6, 0, 10, 6, 0),            // ano zero -> valido
              Arguments.of(31, 12, 2023, 31, 12, 2023),    // dezembro 31 -> valido
              Arguments.of(31, 6, 2023, 10, 6, 2010),      // junho so vai ate 30 -> invalido
              Arguments.of(1, 1, 2023, 1, 1, 2023),        // primeiro dia do ano
              Arguments.of(15, 13, -5, 10, 6, 2010),       // tudo invalido -> nada muda
              Arguments.of(null, 6, 2023, 10, 6, 2010),    // dia null -> invalido, atomico
              Arguments.of(10, null, 2023, 10, 6, 2010),   // mes null -> invalido, atomico
              Arguments.of(15, 3, null, 10, 6, 2010),      // ano null -> invalido, atomico
              Arguments.of(null, null, null, 10, 6, 2010)  // todos null -> invalido, atomico
        );
    }
}
