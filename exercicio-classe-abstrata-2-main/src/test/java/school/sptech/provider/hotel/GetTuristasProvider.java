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

public class GetTuristasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object turista1 = TuristaFactory.build("Ana", "111", 101, 1, 0.0, false, false);
        Object turista2 = TuristaFactory.build("Bia", "222", 102, 2, 50.0, true, true);
        Object executivo = ExecutivoFactory.build("Leo", "333", 201, 1, 0.0, true, false, false);
        Object familia = FamiliaFactory.build("Silva", "444", 301, 3, 0.0, 2);

        return Stream.of(
              Arguments.of(
                    new ArrayList<>(List.of(turista1, executivo, familia, turista2)),
                    List.of(turista1, turista2),
                    "Deve retornar apenas os turistas, na ordem da lista do hotel"
              ),
              Arguments.of(
                    new ArrayList<>(List.of(executivo, familia)),
                    List.of(),
                    "Deve retornar lista vazia quando não há turistas"
              ),
              Arguments.of(
                    new ArrayList<>(),
                    List.of(),
                    "Deve retornar lista vazia quando o hotel está vazio"
              )
        );
    }
}
