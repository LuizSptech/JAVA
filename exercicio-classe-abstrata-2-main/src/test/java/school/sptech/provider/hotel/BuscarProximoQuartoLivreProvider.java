package school.sptech.provider.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.QuartoInvalidoException;
import school.sptech.exceptions.ValorInvalidoException;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.FamiliaFactory;
import school.sptech.factory.TuristaFactory;

public class BuscarProximoQuartoLivreProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        Object ana = TuristaFactory.build("Ana", "111", 1, 1, 0.0, false, false);
        Object leo = ExecutivoFactory.build("Leo", "222", 2, 1, 0.0, false, false, false);
        Object silva = FamiliaFactory.build("Silva", "333", 4, 1, 0.0, 0);
        List<Object> hospedes = List.of(ana, leo, silva);

        return Stream.of(
              Arguments.of(new ArrayList<>(hospedes), 1, 10, 3, "Deve retornar o primeiro quarto livre do intervalo"),
              Arguments.of(new ArrayList<>(hospedes), 4, 10, 5, "Deve começar a busca a partir do início informado"),
              Arguments.of(new ArrayList<>(hospedes), 5, 5, 5, "Deve considerar o próprio fim do intervalo"),
              Arguments.of(new ArrayList<>(hospedes), 1, 2, null, "Deve retornar null quando todos os quartos do intervalo estão ocupados"),
              Arguments.of(new ArrayList<>(hospedes), 3, 1, ValorInvalidoException.class, "Deve lançar ValorInvalidoException quando o início é maior que o fim"),
              Arguments.of(new ArrayList<>(hospedes), null, 10, QuartoInvalidoException.class, "Deve lançar QuartoInvalidoException quando o início é null"),
              Arguments.of(new ArrayList<>(hospedes), 1, null, QuartoInvalidoException.class, "Deve lançar QuartoInvalidoException quando o fim é null"),
              Arguments.of(new ArrayList<>(hospedes), 0, 10, QuartoInvalidoException.class, "Deve lançar QuartoInvalidoException quando o início é zero"),
              Arguments.of(new ArrayList<>(), 7, 9, 7, "Deve retornar o início quando o hotel está vazio")
        );
    }
}
