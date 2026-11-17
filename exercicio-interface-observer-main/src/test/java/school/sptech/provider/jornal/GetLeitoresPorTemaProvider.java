package school.sptech.provider.jornal;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JornalFactory;

public class GetLeitoresPorTemaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        ObservadoresFixture f = new ObservadoresFixture();

        Object j1 = JornalFactory.build("Jornal A", List.of());
        Object j2 = JornalFactory.build("Jornal B", List.of(f.l1, f.l2, f.ej1, f.ej2, f.l6, f.l7, f.l8, f.l9, f.l10));
        Object j3 = JornalFactory.build("Jornal C", List.of(f.l3, f.ej2, f.et1, f.l4, f.ej3, f.l2, f.et2, f.l5, f.l1));
        Object j4 = JornalFactory.build("Jornal D", List.of(f.l4, f.ej3, f.et2, f.et4, f.ej5, f.l6, f.l9));
        Object j5 = JornalFactory.build("Jornal E", List.of(f.l5, f.ej4, f.et3));

        return Stream.of(
              Arguments.of(j1, "TECNOLOGIA", List.of(), "Jornal sem observadores"),
              Arguments.of(j2, "ESPORTE", List.of(f.l2, f.l7), "Dois leitores de ESPORTE entre 9 observadores"),
              Arguments.of(j3, "TECNOLOGIA", List.of(f.l4, f.l1), "Dois leitores de TECNOLOGIA intercalados com estatísticas"),
              Arguments.of(j4, "POLITICA", List.of(f.l9), "Um único leitor de POLITICA"),
              Arguments.of(j5, "ENTRETENIMENTO", List.of(), "Nenhum leitor de ENTRETENIMENTO")
        );
    }
}
