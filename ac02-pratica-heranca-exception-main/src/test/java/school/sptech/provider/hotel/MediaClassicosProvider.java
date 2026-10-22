package school.sptech.provider.hotel;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static school.sptech.factory.ReservaFactory.finalizada;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.HospedeVipFactory;

public class MediaClassicosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("um Vip que gastou 4770.0", new Object[]{
                    HospedeVipFactory.build("Diego", "555", 10.0, finalizada(301, 12, 500.0))
              }, 1),
              argumentSet("dois Vips que gastaram muito", new Object[]{
                    HospedeVipFactory.build("Diego", "555", 10.0, finalizada(301, 12, 500.0)),
                    HospedeVipFactory.build("Eva", "666", 0.0, finalizada(302, 15, 400.0))
              }, 2),
              argumentSet("Vip sem desconto entre classicos", new Object[]{
                    HospedeVipFactory.build("Eva", "666", 0.0, finalizada(302, 15, 400.0))
              }, 1)
        );
    }
}
