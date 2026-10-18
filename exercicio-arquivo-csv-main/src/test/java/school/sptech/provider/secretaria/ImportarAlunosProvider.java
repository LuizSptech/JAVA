package school.sptech.provider.secretaria;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ImportarAlunosProvider implements ArgumentsProvider {

  private static final String CABECALHO = "ra;nome;nota1;nota2;frequencia\n";

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("CSV válido (com acentos)", CABECALHO
                + "001;Ana;8.0;9.0;90\n"
                + "002;Bruno;5.5;4.25;80\n"
                + "009;João Araújo;7.0;6.5;95\n",
            List.of("001|Ana|8.0|9.0|90", "002|Bruno|5.5|4.25|80", "009|João Araújo|7.0|6.5|95")),
        Arguments.of("linhas em branco devem ser ignoradas", CABECALHO
                + "001;Ana;8.0;9.0;90\n"
                + "\n"
                + "   \n"
                + "003;Carla;10.0;10.0;60\n"
                + "\n",
            List.of("001|Ana|8.0|9.0|90", "003|Carla|10.0|10.0|60")),
        Arguments.of("linhas inválidas devem ser ignoradas", CABECALHO
                + "001;Ana;8.0;9.0;90\n"
                + "002;Bruno;5.0;4.0\n"
                + "003;Carla;abc;10.0;60\n"
                + "004;Diego;2.0;3.0;noventa\n"
                + "005;Elisa;7.0;7.0;100;extra\n"
                + "006;Felipe;6.0;6.0;75\n",
            List.of("001|Ana|8.0|9.0|90", "006|Felipe|6.0|6.0|75")),
        Arguments.of("espaços extras devem ser removidos", CABECALHO
                + " 001 ; Ana Souza ; 8.0 ; 9.0 ; 90 \n",
            List.of("001|Ana Souza|8.0|9.0|90")),
        Arguments.of("CSV só com cabeçalho deve retornar lista vazia", CABECALHO, List.of())
    );
  }
}
