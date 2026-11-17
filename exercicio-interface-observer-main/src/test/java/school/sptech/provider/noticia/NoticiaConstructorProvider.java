package school.sptech.provider.noticia;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.NoticiaInvalidaException;
import school.sptech.Tema;
import school.sptech.util.EnumConstantMapping;

public class NoticiaConstructorProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        Tema tecnologia = EnumConstantMapping.getEnumConstant(Tema.class, "TECNOLOGIA");
        Tema esporte = EnumConstantMapping.getEnumConstant(Tema.class, "ESPORTE");
        Class<NoticiaInvalidaException> invalida = NoticiaInvalidaException.class;

        return Stream.of(
              // válidos
              Arguments.of("Novo framework de JS!", tecnologia, "Bob Burnquist", 3, null,
                    "Notícia válida de TECNOLOGIA"),
              Arguments.of("Tecnologia 5G chegando", tecnologia, "Steve Caballero", 10, null,
                    "Notícia válida com 10 minutos de leitura"),
              Arguments.of("Final do campeonato", esporte, "Rayssa Leal", 1, null,
                    "Notícia válida com 1 minuto de leitura (limite)"),

              // título inválido
              Arguments.of(null, esporte, "Tony Hawk", 3, invalida, "Título nulo"),
              Arguments.of("", esporte, "Tony Hawk", 3, invalida, "Título vazio"),

              // tema inválido
              Arguments.of("Novas manobras radicais", null, "Rodrigo TX", 4, invalida,
                    "Tema nulo"),

              // autor inválido
              Arguments.of("Campeonato de skate 2024", esporte, null, 5, invalida, "Autor nulo"),
              Arguments.of("Campeonato de skate 2024", esporte, "", 5, invalida, "Autor vazio"),

              // minutos de leitura inválidos
              Arguments.of("Lançamento de novo game", tecnologia, "Jeff King", 0, invalida,
                    "Minutos de leitura igual a zero"),
              Arguments.of("Lançamento de novo game", tecnologia, "Jeff King", -2, invalida,
                    "Minutos de leitura negativo"),
              Arguments.of("Inovações em IA", tecnologia, "Mark Gonzales", null, invalida,
                    "Minutos de leitura nulo")
        );
    }
}
