package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.PlanoFactory;
import school.sptech.provider.AcademiaFixture;

public class AdicionarPlanoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        Object natacaoTarde = PlanoFactory.natacao("Natação Tarde", 150.0, "TARDE", 2, 3, 0);
        AcademiaFixture f2 = new AcademiaFixture();
        AcademiaFixture f3 = new AcademiaFixture();
        Object vazia = AcademiaFactory.vazia();
        Object primeiro = PlanoFactory.musculacao("Musculação", 90.0, false);

        return Stream.of(
              Arguments.of(vazia, primeiro, null, List.of(primeiro),
                    "Academia sem planos: o plano deve ser adicionado e ficar como único da lista"),
              Arguments.of(f1.academia, natacaoTarde, null,
                    List.of(f1.musculacaoLivre, f1.natacaoManha, f1.natacaoNoite, f1.personalAna, natacaoTarde),
                    "Plano com nome novo: deve ser adicionado ao final da lista"),
              Arguments.of(f2.academia, null, DadoInvalidoException.class, f2.planos(),
                    "Plano nulo: deve lançar DadoInvalidoException e a lista não deve mudar"),
              Arguments.of(f3.academia, PlanoFactory.musculacao("NATAÇÃO MANHÃ", 90.0, false), DadoInvalidoException.class,
                    f3.planos(), "Nome já cadastrado, em outra caixa (\"NATAÇÃO MANHÃ\"): deve lançar DadoInvalidoException e a lista não deve mudar")
        );
    }
}
