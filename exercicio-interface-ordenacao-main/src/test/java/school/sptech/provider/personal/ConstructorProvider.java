package school.sptech.provider.personal;

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
              Arguments.of("Personal Ana", 200.0, "Ana", 3, null, "Dados válidos (3 sessões por semana): deve criar o plano"),
              Arguments.of("Personal Ana", 200.0, "Ana", 1, null, "Uma sessão por semana (mínimo): deve criar o plano"),
              Arguments.of(null, 200.0, "Ana", 3, invalido, "Nome nulo: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Personal", null, "Ana", 3, invalido, "Preço base nulo: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Personal", 200.0, "Ana", null, invalido, "Sessões por semana nulo: deve lançar DadoInvalidoException"),
              Arguments.of("Personal", 200.0, "Ana", 0, invalido, "Zero sessões por semana: deve lançar DadoInvalidoException")
        );
    }
}
