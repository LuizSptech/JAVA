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

public class BuscarHospedeComMaisPontosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        // 63 pontos
        Object ana = TuristaFactory.build("Ana", "111", 101, 3, 300.0, true, false);
        // 40 pontos
        Object leo = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);
        // 62 pontos
        Object silva = FamiliaFactory.build("Silva", "333", 303, 6, 0.0, 4);

        // 40 pontos, total 2055.0
        Object kai = ExecutivoFactory.build("Kai", "444", 404, 1, 50.0, false, false, false);
        // 40 pontos, total 4045.0
        Object bia = TuristaFactory.build("Bia", "555", 505, 2, 950.0, false, false);

        // empate total: 15 pontos e total 1500.0
        Object mia = TuristaFactory.build("Mia", "666", 606, 1, 0.0, false, false);
        Object noa = TuristaFactory.build("Noa", "777", 707, 1, 0.0, false, false);

        return Stream.of(
              Arguments.of(new ArrayList<>(List.of(leo, ana, silva)), ana,
                    "Deve retornar o hóspede com mais pontos"),
              Arguments.of(new ArrayList<>(List.of(kai, bia)), bia,
                    "Em caso de empate nos pontos, deve retornar o de maior total da estadia"),
              Arguments.of(new ArrayList<>(List.of(mia, noa)), mia,
                    "Em caso de empate nos pontos e no total, deve retornar o primeiro da lista"),
              Arguments.of(new ArrayList<>(), null,
                    "Deve retornar null quando não há hóspedes")
        );
    }
}
