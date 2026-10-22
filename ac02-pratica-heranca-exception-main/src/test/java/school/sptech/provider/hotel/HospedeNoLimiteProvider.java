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

public class HospedeNoLimiteProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("classico com 3 reservas ativas", HospedeFactory.build("Ana", "111",
                    ativa(101, 2, 100.0), ativa(102, 2, 100.0), ativa(103, 2, 100.0)), "111"),
              argumentSet("classico com limite 1", HospedeFactory.buildComLimite("Ana", "111", 1,
                    finalizada(201, 2, 100.0), ativa(101, 2, 100.0)), "111"),
              argumentSet("Vip com 10 reservas ativas", HospedeVipFactory.build("Bruno", "222", 10.0,
                    ativa(101, 1, 100.0), ativa(102, 1, 100.0), ativa(103, 1, 100.0),
                    ativa(104, 1, 100.0), ativa(105, 1, 100.0), ativa(106, 1, 100.0),
                    ativa(107, 1, 100.0), ativa(108, 1, 100.0), ativa(109, 1, 100.0),
                    ativa(110, 1, 100.0)), "222")
        );
    }
}
