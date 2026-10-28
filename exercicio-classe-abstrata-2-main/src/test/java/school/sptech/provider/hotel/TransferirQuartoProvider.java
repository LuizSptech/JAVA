package school.sptech.provider.hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.HospedeForaDoHotelException;
import school.sptech.exceptions.QuartoInvalidoException;
import school.sptech.factory.ExecutivoFactory;
import school.sptech.factory.FamiliaFactory;
import school.sptech.factory.TuristaFactory;

public class TransferirQuartoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              cenario(105, true, 105, true, "Deve transferir o hóspede para um quarto livre"),
              cenario(202, false, 101, true, "Não deve transferir para um quarto ocupado por outro hóspede"),
              cenario(101, false, 101, true, "Não deve transferir para o próprio quarto atual"),
              cenario(0, QuartoInvalidoException.class, 101, true, "Deve lançar QuartoInvalidoException para o quarto zero"),
              cenario(null, QuartoInvalidoException.class, 101, true, "Deve lançar QuartoInvalidoException para um quarto null"),
              cenario(105, HospedeForaDoHotelException.class, 101, false, "Deve lançar HospedeForaDoHotelException quando o hóspede não está no hotel")
        );
    }

    private Arguments cenario(Integer novoQuarto, Object esperado, Integer quartoEsperado,
          Boolean hospedado, String descricao) throws ReflectiveOperationException {
        Object ana = TuristaFactory.build("Ana", "111", 101, 2, 0.0, false, false);
        Object leo = ExecutivoFactory.build("Leo", "222", 202, 1, 0.0, false, false, false);
        List<Object> hospedes = hospedado ? List.of(ana, leo) : List.of(leo);
        return Arguments.of(new ArrayList<>(hospedes), ana, novoQuarto, esperado, quartoEsperado,
              descricao);
    }
}
