package school.sptech.provider.natacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.Turno;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.util.EnumConstantMapping;

public class ConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        Turno manha = EnumConstantMapping.getEnumConstant(Turno.class, "MANHA");
        Class<DadoInvalidoException> invalido = DadoInvalidoException.class;

        return Stream.of(
              Arguments.of("Natação Manhã", 150.0, manha, 4, 5, null, "Dados válidos (4 raias x 5 alunos): deve criar a turma"),
              Arguments.of("Natação Manhã", 150.0, manha, 1, 1, null, "Turma mínima (1 raia x 1 aluno): deve criar a turma"),
              Arguments.of("", 150.0, manha, 4, 5, invalido, "Nome vazio: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Natação", -1.0, manha, 4, 5, invalido, "Preço base negativo: deve lançar DadoInvalidoException (regra herdada de Plano)"),
              Arguments.of("Natação", 150.0, manha, null, 5, invalido, "Raias nulo: deve lançar DadoInvalidoException"),
              Arguments.of("Natação", 150.0, manha, 0, 5, invalido, "Zero raias: deve lançar DadoInvalidoException"),
              Arguments.of("Natação", 150.0, manha, 4, null, invalido, "Alunos por raia nulo: deve lançar DadoInvalidoException"),
              Arguments.of("Natação", 150.0, manha, 4, -1, invalido, "Alunos por raia negativo: deve lançar DadoInvalidoException")
        );
    }
}
