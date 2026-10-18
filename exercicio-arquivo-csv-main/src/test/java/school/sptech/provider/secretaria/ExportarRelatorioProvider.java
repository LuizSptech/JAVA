package school.sptech.provider.secretaria;

import static school.sptech.provider.AlunosFixture.*;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ExportarRelatorioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("cinco alunos, um em cada situação", List.of(ana(), bruno(), carla(), diego(), elisa()), List.of(
            "ra;nome;media;situacao",
            "001;Ana;8.50;APROVADO",
            "002;Bruno;4.50;RECUPERACAO",
            "003;Carla;10.00;REPROVADO",
            "004;Diego;2.50;REPROVADO",
            "005;Elisa;7.00;APROVADO")),
        Arguments.of("ordem da lista recebida deve ser mantida", List.of(diego(), bruno()), List.of(
            "ra;nome;media;situacao",
            "004;Diego;2.50;REPROVADO",
            "002;Bruno;4.50;RECUPERACAO")),
        Arguments.of("lista vazia deve exportar só o cabeçalho", List.of(), List.of(
            "ra;nome;media;situacao"))
    );
  }
}
