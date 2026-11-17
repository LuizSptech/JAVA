package school.sptech.provider.jornal;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JornalFactory;

public class GetNoticiaMaisFavoritadaProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        ObservadoresFixture f = new ObservadoresFixture();

        // favoritas: l4 [n1] | l5 [n2] | l7 [n5] | l8 [n4] | l9 [n3] | l10 [n1, n4]
        // l1, l2, l3 e l6 não possuem favoritas
        Object j1 = JornalFactory.build("Jornal A", List.of());
        Object j2 = JornalFactory.build("Jornal B", List.of(f.ej1, f.et2, f.ej3));
        Object j3 = JornalFactory.build("Jornal C", List.of(f.l1, f.l2, f.ej1, f.l3, f.l6));
        Object j4 = JornalFactory.build("Jornal D", List.of(f.l5, f.et3, f.l2));
        Object j5 = JornalFactory.build("Jornal E", List.of(f.l4, f.l10, f.ej2, f.l7));
        Object j6 = JornalFactory.build("Jornal F", List.of(f.l7, f.et1, f.l8, f.l10));
        Object j7 = JornalFactory.build("Jornal G", List.of(f.l9, f.l4, f.ej3, f.l1, f.l10, f.l5));

        return Stream.of(
              Arguments.of(j1, null, "Jornal sem observadores"),
              Arguments.of(j2, null, "Jornal sem leitores"),
              Arguments.of(j3, null, "Leitores sem notícias favoritas"),
              Arguments.of(j4, f.n2, "Apenas um leitor com favoritas"),
              Arguments.of(j5, f.n1, "Notícia favoritada pelo primeiro leitor"),
              Arguments.of(j6, f.n4, "Notícia favoritada não é a do primeiro leitor"),
              Arguments.of(j7, f.n1, "Vários leitores com favoritas diferentes")
        );
    }
}
