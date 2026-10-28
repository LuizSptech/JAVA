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

public class CheckOutProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object ana = TuristaFactory.build("Ana", "111", 101, 3, 300.0, true, false);
        Object leo = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);

        Object silva = FamiliaFactory.build("Silva", "333", 303, 6, 0.0, 4);
        Object leo2 = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);

        return Stream.of(
              Arguments.of(
                    new ArrayList<>(List.of(ana, leo)),
                    ana, List.of(leo), 6330.0,
                    "Deve remover o turista, setar o quarto como null e retornar o total da estadia"
              ),
              Arguments.of(
                    new ArrayList<>(List.of(leo2, silva)),
                    silva, List.of(leo2), 8550.0,
                    "Deve retornar o total da estadia da família já com o desconto de longa permanência"
              )
        );
    }
}
