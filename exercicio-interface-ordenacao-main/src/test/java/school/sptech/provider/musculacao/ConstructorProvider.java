package school.sptech.provider.musculacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        Class<DadoInvalidoException> invalido = DadoInvalidoException.class;

        return Stream.of(
              Arguments.of("Musculação Livre", 100.0, true, null, "Dados válidos com acesso 24h: deve criar o plano"),
              Arguments.of("Musculação Básica", 0.0, false, null, "Preço base zero: deve criar o plano"),
              Arguments.of(null, 100.0, true, invalido, "Nome nulo: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("", 100.0, true, invalido, "Nome vazio: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("   ", 100.0, true, invalido, "Nome só com espaços: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Musculação", null, true, invalido, "Preço base nulo: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Musculação", -1.0, true, invalido, "Preço base negativo: deve lançar DadoInvalidoException (regra herdada de Plano)")
        );
    }
}
