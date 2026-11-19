package school.sptech.provider.natacao;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.PlanoFactory;

public class GetVagasDisponiveisProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
              Arguments.of(PlanoFactory.natacao("A", 150.0, "TARDE", 2, 3, 0), 6,
                    "Capacidade 6 e nenhum inscrito: deve retornar 6"),
              Arguments.of(PlanoFactory.natacao("B", 150.0, "TARDE", 2, 3, 5), 1,
                    "Capacidade 6 e 5 inscritos: deve retornar 1"),
              Arguments.of(PlanoFactory.natacao("C", 150.0, "TARDE", 1, 2, 2), 0,
                    "Capacidade 2 e 2 inscritos (lotada): deve retornar 0"),
              Arguments.of(PlanoFactory.natacao("D", 150.0, "TARDE", 4, 5, 7), 13,
                    "Capacidade 20 e 7 inscritos: deve retornar 13")
        );
    }
}
