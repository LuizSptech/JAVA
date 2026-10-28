package school.sptech.provider.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.FamiliaFactory;
import school.sptech.factory.TuristaFactory;

public class AplicarCortesiasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object turistaAltaSemGuia = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, true);
        Object turistaAltaComGuia = TuristaFactory.build("Bia", "222", 102, 2, 0.0, true, true);
        Object turistaBaixaSemGuia = TuristaFactory.build("Caio", "333", 103, 2, 0.0, false, false);
        Object executivoConveniadoSemInternet =
              ExecutivoFactory.build("Leo", "444", 201, 1, 0.0, true, false, true);
        Object executivoConveniadoComInternet =
              ExecutivoFactory.build("Kai", "555", 202, 1, 0.0, false, true, true);
        Object executivoNaoConveniadoSemInternet =
              ExecutivoFactory.build("Mia", "666", 203, 1, 0.0, false, false, false);
        Object familia = FamiliaFactory.build("Silva", "777", 301, 3, 0.0, 4);

        Object turistaSozinho = TuristaFactory.build("Rui", "888", 104, 1, 0.0, false, true);
        Object executivoSozinho = ExecutivoFactory.build("Gil", "999", 204, 1, 0.0, false, false, true);
        Object familiaSozinha = FamiliaFactory.build("Souza", "000", 302, 1, 0.0, 3);

        return Stream.of(
              Arguments.of(
                    new ArrayList<>(List.of(turistaAltaSemGuia, executivoNaoConveniadoSemInternet,
                          familia, turistaAltaComGuia, executivoConveniadoSemInternet,
                          turistaBaixaSemGuia, executivoConveniadoComInternet)),
                    2,
                    List.of(
                          List.of(turistaAltaSemGuia, "guiaTuristico", true),
                          List.of(turistaAltaComGuia, "guiaTuristico", true),
                          List.of(turistaBaixaSemGuia, "guiaTuristico", false),
                          List.of(executivoConveniadoSemInternet, "internet", true),
                          List.of(executivoConveniadoComInternet, "internet", true),
                          List.of(executivoNaoConveniadoSemInternet, "internet", false),
                          List.of(familia, "qtdCriancas", 4)
                    ),
                    "Deve aplicar a cortesia apenas em quem atende à regra do seu tipo"
              ),
              Arguments.of(
                    new ArrayList<>(List.of(turistaSozinho, familiaSozinha, executivoSozinho)),
                    2,
                    List.of(
                          List.of(turistaSozinho, "guiaTuristico", true),
                          List.of(executivoSozinho, "internet", true),
                          List.of(familiaSozinha, "qtdCriancas", 3)
                    ),
                    "Deve tratar cada tipo de hóspede, mesmo com a família no meio da lista"
              ),
              Arguments.of(
                    new ArrayList<>(),
                    0,
                    List.of(),
                    "Deve retornar zero quando o hotel está vazio"
              )
        );
    }
}
