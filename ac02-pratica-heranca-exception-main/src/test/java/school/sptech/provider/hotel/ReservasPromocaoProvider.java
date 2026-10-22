package school.sptech.provider.hotel;

import static org.junit.jupiter.params.provider.Arguments.argumentSet;
import static school.sptech.factory.ReservaFactory.ativa;
import static school.sptech.factory.ReservaFactory.finalizada;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class ReservasPromocaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              argumentSet("3 reservas finalizadas", new Object[]{
                    finalizada(101, 2, 100.0),
                    finalizada(202, 7, 150.0),
                    finalizada(101, 12, 90.0)
              }, 3),
              argumentSet("5 reservas finalizadas", new Object[]{
                    finalizada(101, 1, 100.0),
                    finalizada(102, 2, 110.0),
                    finalizada(103, 3, 120.0),
                    finalizada(104, 4, 130.0),
                    finalizada(105, 5, 140.0)
              }, 5),
              argumentSet("reservas ativas e finalizadas (mantem o estado)", new Object[]{
                    finalizada(101, 2, 100.0),
                    ativa(202, 7, 150.0),
                    finalizada(202, 3, 120.0),
                    ativa(303, 12, 90.0)
              }, 4),
              argumentSet("12 reservas finalizadas (mais que o limite do Vip)", new Object[]{
                    finalizada(101, 1, 100.0), finalizada(102, 1, 100.0), finalizada(103, 1, 100.0),
                    finalizada(104, 1, 100.0), finalizada(105, 1, 100.0), finalizada(106, 1, 100.0),
                    finalizada(107, 1, 100.0), finalizada(108, 1, 100.0), finalizada(109, 1, 100.0),
                    finalizada(110, 1, 100.0), finalizada(111, 1, 100.0), finalizada(112, 1, 100.0)
              }, 12),
              argumentSet("3 reservas ativas", new Object[]{
                    ativa(101, 2, 100.0),
                    ativa(102, 3, 120.0),
                    ativa(103, 4, 90.0)
              }, 3),
              argumentSet("reserva ativa no inicio da lista", new Object[]{
                    ativa(303, 5, 200.0),
                    finalizada(101, 2, 100.0),
                    finalizada(102, 2, 100.0)
              }, 3),
              argumentSet("1 ativa e 11 finalizadas (mais que o limite do Vip)", new Object[]{
                    finalizada(101, 1, 100.0), finalizada(102, 1, 100.0), finalizada(103, 1, 100.0),
                    finalizada(104, 1, 100.0), finalizada(105, 1, 100.0), ativa(106, 2, 150.0),
                    finalizada(107, 1, 100.0), finalizada(108, 1, 100.0), finalizada(109, 1, 100.0),
                    finalizada(110, 1, 100.0), finalizada(111, 1, 100.0), finalizada(112, 1, 100.0)
              }, 12),
              argumentSet("varias reservas do mesmo quarto", new Object[]{
                    finalizada(101, 1, 100.0),
                    finalizada(101, 2, 100.0),
                    ativa(101, 3, 100.0)
              }, 3)
        );
    }
}
