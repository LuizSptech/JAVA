package school.sptech.provider.hotel;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static school.sptech.factory.ReservaFactory.ativa;
import static school.sptech.factory.ReservaFactory.finalizada;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.HospedeFactory;
import school.sptech.factory.HospedeVipFactory;

public class QuartoOcupadoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("ocupado por hospede classico", HospedeFactory.build("Bruno", "222", ativa(101, 2, 100.0)), 101),
              argumentSet("ocupado por hospede Vip", HospedeVipFactory.build("Diego", "444", 10.0,
                    finalizada(101, 2, 100.0), ativa(305, 2, 100.0)), 305),
              argumentSet("ocupado na segunda reserva do hospede", HospedeFactory.build("Bruno", "222",
                    ativa(205, 1, 90.0), ativa(206, 1, 90.0)), 206)
        );
    }
}
