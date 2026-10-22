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

public class NaoPromoverProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        // O apto ("111", adicionado pelo teste) entra na media dos classicos. Os valores mantem o
        // apto promovivel e, em cada cenario, so a condicao testada impede a promocao do
        // inelegivel.
        return Stream.of(
              // menos de 3 reservas (alvo com gasto alto, para nao ser barrado pela media)
              argumentSet("nao promove quem tem menos de 3 reservas (1 finalizada)",
                    HospedeFactory.build("Bruno", "222", finalizada(301, 12, 200.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem tem menos de 3 reservas (1 ativa)",
                    HospedeFactory.build("Bruno", "222", ativa(301, 12, 250.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem tem menos de 3 reservas (2 com gasto alto)",
                    HospedeFactory.build("Bruno", "222",
                          finalizada(301, 6, 200.0), finalizada(302, 6, 200.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem tem menos de 3 reservas (2, sendo uma ativa)",
                    HospedeFactory.build("Bruno", "222",
                          finalizada(301, 6, 200.0), ativa(302, 6, 200.0)),
                    new Object[]{}, "222"),

              // ja e Vip (3 ou mais reservas e gasto alto, para nao ser barrado pelas outras regras)
              argumentSet("nao promove quem ja e Vip (3 reservas finalizadas)",
                    HospedeVipFactory.build("Bruno", "222", 10.0,
                          finalizada(301, 6, 200.0), finalizada(302, 6, 200.0),
                          finalizada(303, 6, 200.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem ja e Vip (gasto alto)",
                    HospedeVipFactory.build("Bruno", "222", 10.0,
                          finalizada(301, 12, 500.0), finalizada(302, 12, 500.0),
                          finalizada(303, 12, 500.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem ja e Vip (4 reservas)",
                    HospedeVipFactory.build("Bruno", "222", 20.0,
                          finalizada(301, 5, 200.0), finalizada(302, 5, 200.0),
                          finalizada(303, 5, 200.0), finalizada(304, 5, 200.0)),
                    new Object[]{}, "222"),
              argumentSet("nao promove quem ja e Vip (com reserva ativa)",
                    HospedeVipFactory.build("Bruno", "222", 10.0,
                          finalizada(301, 6, 200.0), finalizada(302, 6, 200.0),
                          ativa(303, 6, 200.0)),
                    new Object[]{}, "222"),

              // gasto ate 70% da media dos classicos
              argumentSet("nao promove quem gastou ate 70% da media (outro classico gastou muito)",
                    HospedeFactory.build("Ana", "333",
                          finalizada(101, 1, 100.0), finalizada(102, 1, 100.0),
                          finalizada(103, 1, 100.0)),
                    new Object[]{HospedeFactory.build("Carla", "444", finalizada(301, 12, 500.0))},
                    "333"),
              argumentSet("nao promove quem gastou ate 70% da media (so ele e o apto no hotel)",
                    HospedeFactory.build("Ana", "333",
                          finalizada(101, 1, 100.0), finalizada(102, 1, 100.0),
                          finalizada(103, 1, 100.0)),
                    new Object[]{}, "333"),
              argumentSet("nao promove quem gastou ate 70% da media (4 reservas, sendo uma ativa)",
                    HospedeFactory.build("Ana", "333",
                          ativa(101, 1, 50.0), finalizada(102, 1, 50.0),
                          finalizada(103, 1, 50.0), finalizada(104, 1, 50.0)),
                    new Object[]{HospedeFactory.build("Carla", "444", finalizada(301, 12, 500.0))},
                    "333"),
              argumentSet("nao promove quem gastou ate 70% da media (varios classicos)",
                    HospedeFactory.build("Ana", "333",
                          finalizada(101, 2, 100.0), finalizada(102, 2, 100.0),
                          finalizada(103, 2, 100.0)),
                    new Object[]{
                          HospedeFactory.build("Carla", "444", finalizada(301, 12, 500.0)),
                          HospedeFactory.build("Diego", "555", finalizada(401, 10, 400.0))
                    }, "333")
        );
    }
}
