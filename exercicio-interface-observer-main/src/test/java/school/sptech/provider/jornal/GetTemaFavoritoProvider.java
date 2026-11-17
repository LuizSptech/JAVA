package school.sptech.provider.jornal;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JornalFactory;

public class GetTemaFavoritoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        ObservadoresFixture f = new ObservadoresFixture();

        Object j1 = JornalFactory.build("Jornal A", List.of());
        Object j2 = JornalFactory.build("Jornal B", List.of(f.ej1, f.et2));
        Object j3 = JornalFactory.build("Jornal C", List.of(f.ej1, f.ej2, f.l6, f.l7, f.l8, f.l9, f.l10, f.ej4, f.ej5, f.ej3));
        Object j4 = JornalFactory.build("Jornal D", List.of(f.l3, f.ej2, f.et1, f.l4, f.ej3, f.l2, f.et2, f.l1));
        Object j5 = JornalFactory.build("Jornal E", List.of(f.l4, f.ej3, f.et2, f.et4, f.ej5, f.l6, f.l9, f.l3));
        Object j6 = JornalFactory.build("Jornal F", List.of(f.l5, f.ej4, f.et3));

        return Stream.of(
              Arguments.of(j1, null, "Jornal sem observadores"),
              Arguments.of(j2, null, "Jornal sem leitores"),
              Arguments.of(j3, "TECNOLOGIA", "TECNOLOGIA favorito de 2 entre 5 leitores"),
              Arguments.of(j4, "TECNOLOGIA", "Leitores intercalados com estatísticas"),
              Arguments.of(j5, "POLITICA", "POLITICA favorito de 2 entre 4 leitores"),
              Arguments.of(j6, "ESPORTE", "Jornal com um único leitor (ESPORTE)")
        );
    }
}
