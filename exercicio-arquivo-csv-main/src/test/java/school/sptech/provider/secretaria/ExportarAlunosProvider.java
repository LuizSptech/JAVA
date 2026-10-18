package school.sptech.provider.secretaria;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AlunoFactory;

public class ExportarAlunosProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("dois alunos", List.of(ana(), bruno()), List.of(
            "ra;nome;nota1;nota2;frequencia",
            "001;Ana;8.00;9.00;90",
            "002;Bruno;5.00;4.00;80")),
        Arguments.of("nota com mais de 2 casas decimais deve ser arredondada", List.of(AlunoFactory.build("010", "Joana Dias", 7.256, 6.5, 100)), List.of(
            "ra;nome;nota1;nota2;frequencia",
            "010;Joana Dias;7.26;6.50;100")),
        Arguments.of("lista vazia deve exportar só o cabeçalho", List.of(), List.of(
            "ra;nome;nota1;nota2;frequencia"))
    );
  }
}
