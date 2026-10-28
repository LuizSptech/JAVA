package school.sptech.provider.exceptions;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.HospedeForaDoHotelException;
import school.sptech.exceptions.QuartoInvalidoException;
import school.sptech.exceptions.QuartoOcupadoException;
import school.sptech.exceptions.ValorInvalidoException;

public class ExceptionsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              Arguments.of(ValorInvalidoException.class, RuntimeException.class,
                    "ValorInvalidoException deve ser unchecked"),
              Arguments.of(QuartoInvalidoException.class, ValorInvalidoException.class,
                    "QuartoInvalidoException deve herdar de ValorInvalidoException"),
              Arguments.of(HospedeForaDoHotelException.class, RuntimeException.class,
                    "HospedeForaDoHotelException deve ser unchecked"),
              Arguments.of(QuartoOcupadoException.class, Exception.class,
                    "QuartoOcupadoException deve ser checked")
        );
    }
}
