package school.sptech.provider.jornal;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.NoticiaFactory;

public class PublicarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext)
          throws Exception {
        return Stream.of(
              Arguments.of(0, NoticiaFactory.build("Titulo 1", "TECNOLOGIA", "Vagner Moura", 10)),
              Arguments.of(1, NoticiaFactory.build("Titulo 2", "ESPORTE", "Neymar Jr", 5)),
              Arguments.of(3, NoticiaFactory.build("Titulo 3", "POLITICA", "Paulo Souza", 8)),
              Arguments.of(5, NoticiaFactory.build("Titulo 4", "TECNOLOGIA", "Ada Lovelace", 12)),
              Arguments.of(10, NoticiaFactory.build("Titulo 5", "ESPORTE", "Marta Silva", 7))
        );
    }
}
