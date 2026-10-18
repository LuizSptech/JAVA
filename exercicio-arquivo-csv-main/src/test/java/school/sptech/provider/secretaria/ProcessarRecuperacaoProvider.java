package school.sptech.provider.secretaria;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ProcessarRecuperacaoProvider implements ArgumentsProvider {

  private static final String CABECALHO_ALUNOS = "ra;nome;nota1;nota2;frequencia\n";
  private static final String CABECALHO_NOTAS = "ra;notaRecuperacao\n";
  private static final String CABECALHO_SAIDA = "ra;nome;media;notaRecuperacao;mediaFinal;situacao";

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("um aprovado e um reprovado; quem não está em recuperação não aparece",
            CABECALHO_ALUNOS
                + "001;Ana;8.0;9.0;90\n"
                + "002;Bruno;5.0;4.0;80\n"
                + "003;Carla;10.0;10.0;60\n"
                + "007;Gabriela Simões;4.0;4.0;100\n",
            CABECALHO_NOTAS
                + "007;7.0\n"
                + "002;8.0\n",
            List.of(CABECALHO_SAIDA,
                "002;Bruno;4.50;8.00;6.25;APROVADO",
                "007;Gabriela Simões;4.00;7.00;5.50;REPROVADO")),

        Arguments.of("aluno sem nota de recuperação deve ficar com 0.0",
            CABECALHO_ALUNOS
                + "002;Bruno;5.0;4.0;80\n"
                + "007;Gabriela;4.0;4.0;100\n",
            CABECALHO_NOTAS
                + "002;10.0\n",
            List.of(CABECALHO_SAIDA,
                "002;Bruno;4.50;10.00;7.25;APROVADO",
                "007;Gabriela;4.00;0.00;2.00;REPROVADO")),

        Arguments.of("linhas em branco e inválidas no CSV de notas devem ser ignoradas",
            CABECALHO_ALUNOS
                + "002;Bruno;5.0;4.0;80\n",
            CABECALHO_NOTAS
                + "\n"
                + "002;abc\n"
                + "002\n"
                + "002;9.0;extra\n"
                + "002;7.5\n",
            List.of(CABECALHO_SAIDA,
                "002;Bruno;4.50;7.50;6.00;APROVADO")),

        Arguments.of("RA repetido no CSV de notas: vale a primeira nota",
            CABECALHO_ALUNOS
                + "002;Bruno;5.0;4.0;80\n",
            CABECALHO_NOTAS
                + "002;7.5\n"
                + "002;1.0\n",
            List.of(CABECALHO_SAIDA,
                "002;Bruno;4.50;7.50;6.00;APROVADO")),

        Arguments.of("RA repetido no CSV de alunos: vale o primeiro aluno",
            CABECALHO_ALUNOS
                + "002;Bruno;5.0;4.0;80\n"
                + "002;Bruno Repetido;5.0;5.0;80\n"
                + "008;Helena Conceição;5.0;5.0;90\n",
            CABECALHO_NOTAS
                + "008;6.0\n"
                + "002;6.0\n",
            List.of(CABECALHO_SAIDA,
                "002;Bruno;4.50;6.00;5.25;REPROVADO",
                "008;Helena Conceição;5.00;6.00;5.50;REPROVADO")),

        Arguments.of("ninguém em recuperação deve exportar só o cabeçalho",
            CABECALHO_ALUNOS
                + "001;Ana;8.0;9.0;90\n"
                + "004;Diego;2.0;3.0;90\n",
            CABECALHO_NOTAS
                + "001;10.0\n",
            List.of(CABECALHO_SAIDA))
    );
  }
}
