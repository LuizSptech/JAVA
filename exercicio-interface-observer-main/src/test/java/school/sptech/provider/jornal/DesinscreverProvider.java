package school.sptech.provider.jornal;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JornalFactory;

public class DesinscreverProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        ObservadoresFixture f = new ObservadoresFixture();

        Object j1 = JornalFactory.build("Jornal A", List.of());
        Object j2 = JornalFactory.build("Jornal B", List.of(f.l1, f.l2, f.ej1));
        Object j3 = JornalFactory.build("Jornal C", List.of(f.l3, f.ej2, f.et1, f.l4, f.ej3, f.et2));
        Object j4 = JornalFactory.build("Jornal D", List.of(f.l4, f.ej3, f.et2, f.et4, f.ej5));
        Object j5 = JornalFactory.build("Jornal E", List.of(f.l5, f.ej4, f.et3));

        return Stream.of(
              Arguments.of(j1, f.l1, List.of(), "Jornal vazio (nada muda)"),
              Arguments.of(j2, f.et4, List.of(f.l1, f.l2, f.ej1), "Observador não inscrito (nada muda)"),
              Arguments.of(j3, f.l4, List.of(f.l3, f.ej2, f.et1, f.ej3, f.et2), "Leitor na quarta posição"),
              Arguments.of(j4, f.ej3, List.of(f.l4, f.et2, f.et4, f.ej5), "Estatística de jornalista na segunda posição"),
              Arguments.of(j5, f.ej4, List.of(f.l5, f.et3), "Estatística de jornalista entre leitor e estatística de tema")
        );
    }
}
