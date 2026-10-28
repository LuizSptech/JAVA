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

public class CheckInProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object ana = TuristaFactory.build("Ana", "111", null, 2, 100.0, false, false);

        Object leo = ExecutivoFactory.build("Leo", "222", 10, 1, 0.0, true, false, false);
        Object bia = TuristaFactory.build("Bia", "333", null, 1, 0.0, true, false);

        Object silva = FamiliaFactory.build("Silva", "555", 5, 3, 0.0, 2);

        return Stream.of(
              Arguments.of(
                    new ArrayList<>(),
                    ana, 12, 12, List.of(ana),
                    "Deve fazer check-in quando o quarto está disponível"
              ),
              Arguments.of(
                    new ArrayList<>(List.of(leo)),
                    bia, 11, 11, List.of(leo, bia),
                    "Deve adicionar o hóspede no fim da lista"
              ),
              Arguments.of(
                    new ArrayList<>(List.of(silva)),
                    silva, 7, 5, List.of(silva),
                    "Não deve fazer nada quando o hóspede já está hospedado"
              )
        );
    }
}
