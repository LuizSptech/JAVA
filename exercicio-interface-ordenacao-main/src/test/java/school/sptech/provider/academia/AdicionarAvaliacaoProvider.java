package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.factory.AvaliacaoFisicaFactory;
import school.sptech.provider.AcademiaFixture;

public class AdicionarAvaliacaoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        Object postural = AvaliacaoFisicaFactory.build("Avaliação Postural", "Dr. Rui", 60.0, 2, 0);
        AcademiaFixture f2 = new AcademiaFixture();
        AcademiaFixture f3 = new AcademiaFixture();

        return Stream.of(
              Arguments.of(f1.academia, postural, null, List.of(f1.bioimpedancia, postural),
                    "Avaliação com nome novo: deve ser adicionada ao final da lista"),
              Arguments.of(f2.academia, null, DadoInvalidoException.class, List.of(f2.bioimpedancia),
                    "Avaliação nula: deve lançar DadoInvalidoException e a lista não deve mudar"),
              Arguments.of(f3.academia, AvaliacaoFisicaFactory.build("BIOIMPEDÂNCIA", "Dr. Rui", 60.0, 2, 0),
                    DadoInvalidoException.class, List.of(f3.bioimpedancia),
                    "Nome já cadastrado, em outra caixa (\"BIOIMPEDÂNCIA\"): deve lançar DadoInvalidoException e a lista não deve mudar")
        );
    }
}
