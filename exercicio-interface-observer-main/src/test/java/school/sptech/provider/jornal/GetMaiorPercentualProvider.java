package school.sptech.provider.jornal;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JornalFactory;

public class GetMaiorPercentualProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        ObservadoresFixture f = new ObservadoresFixture();

        Object j1 = JornalFactory.build("Jornal A", List.of());
        Object j2 = JornalFactory.build("Jornal B", List.of(f.l1, f.l2));
        Object j3 = JornalFactory.build("Jornal C", List.of(f.l1, f.l2, f.ej1, f.ej2));
        Object j4 = JornalFactory.build("Jornal D", List.of(f.l3, f.et1, f.l4, f.ej3, f.ej2, f.et2));
        Object j5 = JornalFactory.build("Jornal E", List.of(f.l4, f.ej3, f.et4, f.ej5));
        Object j6 = JornalFactory.build("Jornal F", List.of(f.l5, f.ej4, f.et3));

        return Stream.of(
              Arguments.of(j1, null, "Jornal sem observadores"),
              Arguments.of(j2, null, "Jornal apenas com leitores"),
              Arguments.of(j3, f.ej2, "Leitores e estatísticas de jornalista (0% e 83,33%)"),
              Arguments.of(j4, f.ej2, "Maior percentual (83,33%) no meio da lista"),
              Arguments.of(j5, f.ej3, "Maior percentual (40%) na primeira estatística"),
              Arguments.of(j6, f.et3, "Estatística de tema (40%) maior que a de jornalista (33,33%)")
        );
    }
}
