package school.sptech.provider.aluno;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class CalcularSituacaoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("média 8.5 e frequência 90% deve ser APROVADO", 8.0, 9.0, 90, "APROVADO"),
        Arguments.of("média exatamente 6 e frequência exatamente 75% deve ser APROVADO", 6.0, 6.0, 75, "APROVADO"),
        Arguments.of("média 4.5 deve ser RECUPERACAO", 5.0, 4.0, 80, "RECUPERACAO"),
        Arguments.of("média exatamente 4 deve ser RECUPERACAO", 4.0, 4.0, 100, "RECUPERACAO"),
        Arguments.of("média 3.5 deve ser REPROVADO", 3.0, 4.0, 100, "REPROVADO"),
        Arguments.of("média 10 com frequência 74% deve ser REPROVADO", 10.0, 10.0, 74, "REPROVADO"),
        Arguments.of("média de recuperação com frequência 50% deve ser REPROVADO", 5.0, 5.0, 50, "REPROVADO")
    );
  }
}
