package school.sptech.provider.leitor;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.LeitorFactory;
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

        // cada cenário usa um leitor próprio, para não depender da ordem de execução
        Object l1 = LeitorFactory.build("Ana", "ana@email.com", List.of(), List.of(), "TECNOLOGIA");
        Object l2 = LeitorFactory.build("Bruno", "bruno@email.com", List.of(), List.of(), "ESPORTE");
        Object l3 = LeitorFactory.build("Carla", "carla@email.com", List.of(n5), List.of(), "POLITICA");
        Object l4 = LeitorFactory.build("Daniel", "daniel@email.com", List.of(n1, n3), List.of(n1), "TECNOLOGIA");
        Object l5 = LeitorFactory.build("Eva", "eva@email.com", List.of(n2, n4), List.of(n2), "ESPORTE");
        Object l6 = LeitorFactory.build("Fábio", "fabio@email.com", List.of(n1), List.of(n1), "TECNOLOGIA");

        return Stream.of(
              Arguments.of(l1, n1, List.of(n1), List.of(n1),
                    "Leitor sem notícias recebe notícia de TECNOLOGIA (favorito)"),
              Arguments.of(l2, n2, List.of(n2), List.of(n2),
                    "Leitor sem notícias recebe notícia de ESPORTE (favorito)"),
              Arguments.of(l3, n3, List.of(n5, n3), List.of(n3),
                    "Leitor sem favoritas recebe notícia de POLITICA (favorito)"),
              Arguments.of(l4, n4, List.of(n1, n3, n4), List.of(n1, n4),
                    "Leitor com uma favorita recebe notícia de TECNOLOGIA (favorito)"),
              Arguments.of(l5, n5, List.of(n2, n4, n5), List.of(n2, n5),
                    "Leitor com uma favorita recebe notícia de ESPORTE (favorito)"),
              Arguments.of(l6, n3, List.of(n1, n3), List.of(n1),
                    "Leitor de TECNOLOGIA recebe notícia de POLITICA (fora do favorito)")
        );
    }
}
