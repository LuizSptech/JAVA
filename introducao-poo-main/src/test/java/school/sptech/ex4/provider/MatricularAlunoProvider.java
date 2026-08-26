package school.sptech.ex4.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class MatricularAlunoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        // quantidadeAlunosMatriculados (inicial), capacidadeMaxima, quantidade (parametro),
        // quantidadeAlunosMatriculados (esperado)
        return Stream.of(
                Arguments.of(20, 30, 5, 25),
                Arguments.of(25, 30, 10, 25),
                Arguments.of(20, 30, -5, 20),
                Arguments.of(25, 30, 5, 30),
                Arguments.of(30, 30, 1, 30)
        );
    }
}
