package school.sptech.provider.secretaria;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ExportarPorSituacaoProvider implements ArgumentsProvider {

  private static final String CABECALHO = "ra;nome;media;situacao";

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("alunos nas três situações", List.of(ana(), bruno(), carla(), diego(), elisa()),
            List.of(CABECALHO, "001;Ana;8.50;APROVADO", "005;Elisa;7.00;APROVADO"),
            List.of(CABECALHO, "002;Bruno;4.50;RECUPERACAO"),
            List.of(CABECALHO, "003;Carla;10.00;REPROVADO", "004;Diego;2.50;REPROVADO")),
        Arguments.of("só aprovados: os outros arquivos ficam só com cabeçalho", List.of(elisa()),
            List.of(CABECALHO, "005;Elisa;7.00;APROVADO"),
            List.of(CABECALHO),
            List.of(CABECALHO)),
        Arguments.of("lista vazia: três arquivos só com cabeçalho", List.of(),
            List.of(CABECALHO),
            List.of(CABECALHO),
            List.of(CABECALHO))
    );
  }
}
