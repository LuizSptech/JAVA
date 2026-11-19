package school.sptech.provider.avaliacao;

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
              Arguments.of("Bioimpedância", "Dra. Lia", 80.0, 3, null, "Dados válidos: deve criar a avaliação"),
              Arguments.of("Avaliação Gratuita", "Dr. Rui", 0.0, 1, null, "Preço zero e limite diário 1: deve criar a avaliação"),
              Arguments.of("Bioimpedância", "Dra. Lia", 80.0, null, invalido, "Limite diário nulo: deve lançar DadoInvalidoException"),
              Arguments.of("Bioimpedância", "Dra. Lia", 80.0, 0, invalido, "Limite diário zero: deve lançar DadoInvalidoException")
        );
    }
}
