package school.sptech.provider.estatistica;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EstatisticaFactory;
import school.sptech.factory.NoticiaFactory;

public class ReceberPublicacaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {

        Object n1 = NoticiaFactory.build("Titulo 1", "TECNOLOGIA", "Vagner Moura", 10);
        Object n2 = NoticiaFactory.build("Titulo 2", "ESPORTE", "Neymar Jr", 5);
        Object n3 = NoticiaFactory.build("Titulo 3", "POLITICA", "Paulo Souza", 8);
        Object n4 = NoticiaFactory.build("Titulo 4", "TECNOLOGIA", "Ada Lovelace", 12);
        Object n5 = NoticiaFactory.build("Titulo 5", "ESPORTE", "Marta Silva", 7);

        return Stream.of(
              Arguments.of(EstatisticaFactory.build(0), 0, n1, 1),
              Arguments.of(EstatisticaFactory.build(2), 2, n2, 3),
              Arguments.of(EstatisticaFactory.build(5), 5, n3, 6),
              Arguments.of(EstatisticaFactory.build(10), 10, n4, 11),
              Arguments.of(EstatisticaFactory.build(20), 20, n5, 21)
        );
    }
}
