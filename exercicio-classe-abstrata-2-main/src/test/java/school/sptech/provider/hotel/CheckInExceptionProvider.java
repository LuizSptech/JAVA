package school.sptech.provider.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.QuartoInvalidoException;
import school.sptech.exceptions.QuartoOcupadoException;
import school.sptech.exceptions.ValorInvalidoException;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.TuristaFactory;

public class CheckInExceptionProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              cenario(null, 10, ValorInvalidoException.class,
                    "Deve lançar ValorInvalidoException quando o hóspede é null"),
              cenario(true, 10, QuartoOcupadoException.class,
                    "Deve lançar QuartoOcupadoException quando o quarto já está ocupado"),
              cenario(true, null, QuartoInvalidoException.class,
                    "Deve lançar QuartoInvalidoException quando o quarto é null"),
              cenario(true, 0, QuartoInvalidoException.class,
                    "Deve lançar QuartoInvalidoException quando o quarto é zero"),
              cenario(true, -1, QuartoInvalidoException.class,
                    "Deve lançar QuartoInvalidoException quando o quarto é negativo")
        );
    }

    private Arguments cenario(Boolean comHospede, Integer quarto, Class<?> exception,
          String descricao) throws ReflectiveOperationException {
        Object leo = ExecutivoFactory.build("Leo", "222", 10, 1, 0.0, true, false, false);
        Object bia = comHospede == null ? null
              : TuristaFactory.build("Bia", "333", null, 1, 0.0, true, false);
        return Arguments.of(new ArrayList<>(List.of(leo)), bia, quarto, exception, descricao);
    }
}
