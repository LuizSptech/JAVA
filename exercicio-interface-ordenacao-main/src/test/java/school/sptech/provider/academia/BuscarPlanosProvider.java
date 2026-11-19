package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.provider.AcademiaFixture;

public class BuscarPlanosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f = new AcademiaFixture();

        return Stream.of(
              Arguments.of(f.academia, "NATAÇÃO", List.of(f.natacaoManha, f.natacaoNoite),
                    "Texto em outra caixa (\"NATAÇÃO\"): deve ignorar maiúsculas e retornar as duas turmas de natação, na ordem de cadastro"),
              Arguments.of(f.academia, "livre", List.of(f.musculacaoLivre), "Parte do nome (\"livre\"): deve encontrar pelo trecho e retornar a Musculação Livre"),
              Arguments.of(f.academia, "a", f.planos(), "Texto presente em todos os nomes (\"a\"): deve retornar todos os planos"),
              Arguments.of(f.academia, "Bioimpedância", List.of(), "Nome de uma avaliação (\"Bioimpedância\"): deve procurar só nos planos e retornar lista vazia"),
              Arguments.of(f.academia, "Yoga", List.of(), "Nenhum plano contém o texto (\"Yoga\"): deve retornar lista vazia"),
              Arguments.of(f.academia, null, List.of(), "Texto nulo: deve retornar lista vazia, sem lançar exception")
        );
    }
}
