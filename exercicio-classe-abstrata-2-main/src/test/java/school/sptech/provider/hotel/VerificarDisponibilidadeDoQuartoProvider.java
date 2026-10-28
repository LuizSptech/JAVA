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

public class VerificarDisponibilidadeDoQuartoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object ana = TuristaFactory.build("Ana", "111", 10, 2, 100.0, false, false);
        Object leo = ExecutivoFactory.build("Leo", "222", 20, 1, 0.0, true, true, false);
        Object silva = FamiliaFactory.build("Silva", "333", 30, 1, 0.0, 1);
        List<Object> hospedes = List.of(ana, leo, silva);

        return Stream.of(
              Arguments.of(new ArrayList<>(hospedes), 10, false, "Deve retornar false para um quarto ocupado"),
              Arguments.of(new ArrayList<>(hospedes), 30, false, "Deve retornar false para um quarto ocupado por uma família"),
              Arguments.of(new ArrayList<>(hospedes), 40, true, "Deve retornar true para um quarto disponível")
        );
    }
}
