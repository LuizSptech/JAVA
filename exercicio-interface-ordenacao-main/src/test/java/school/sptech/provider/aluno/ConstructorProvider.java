package school.sptech.provider.aluno;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
              Arguments.of("Ana", null, "Nome simples: deve criar o aluno"),
              Arguments.of("João Araújo", null, "Nome com acento e espaço: deve criar o aluno")
        );
    }
}
