package school.sptech.provider.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.HospedeForaDoHotelException;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.FamiliaFactory;
import school.sptech.factory.TuristaFactory;

public class TrocarQuartosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object ana = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, false);
        Object leo = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);

        Object ana2 = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, false);
        Object silva = FamiliaFactory.build("Silva", "333", 303, 1, 0.0, 2);

        Object leo4 = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);
        Object silva2 = FamiliaFactory.build("Silva", "333", 303, 1, 0.0, 2);

        Object ana3 = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, false);
        Object leo3 = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);

        Object leo5 = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);
        Object bia = TuristaFactory.build("Bia", "444", 404, 1, 0.0, false, false);

        return Stream.of(
              Arguments.of(new ArrayList<>(List.of(ana, leo)), ana, leo, true, 202, 101,
                    "Deve trocar os quartos de dois hóspedes do hotel"),
              Arguments.of(new ArrayList<>(List.of(ana2)), ana2, silva, HospedeForaDoHotelException.class, 101, 303,
                    "Deve lançar HospedeForaDoHotelException quando o segundo hóspede não está no hotel"),
              Arguments.of(new ArrayList<>(List.of(leo4)), silva2, leo4, HospedeForaDoHotelException.class, 303, 202,
                    "Deve lançar HospedeForaDoHotelException quando o primeiro hóspede não está no hotel"),
              Arguments.of(new ArrayList<>(List.of(ana3, leo3)), ana3, ana3, false, 101, 101,
                    "Não deve trocar quando os dois hóspedes são o mesmo objeto"),
              Arguments.of(new ArrayList<>(List.of(leo5)), bia, bia, HospedeForaDoHotelException.class, 404, 404,
                    "Deve lançar HospedeForaDoHotelException mesmo quando os dois são o mesmo hóspede fora do hotel")
        );
    }
}
