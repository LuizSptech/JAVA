package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.NaoEncontradoException;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.AcademiaFixture;

public class MatricularProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        AcademiaFixture f2 = new AcademiaFixture();
        Object eva = AlunoFactory.build("Eva", null);
        Object academiaComEva = AcademiaFactory.build("SPTech Fit", f2.planos(), List.of(eva), List.of());
        AcademiaFixture f3 = new AcademiaFixture();
        AcademiaFixture f4 = new AcademiaFixture();

        return Stream.of(
              Arguments.of(f1.academia, "carla", "NATAÇÃO NOITE", f1.carla, f1.natacaoNoite, null,
                    "Aluna já matriculada e nomes em outra caixa: deve trocar o plano da Carla de Musculação Livre para Natação Noite"),
              Arguments.of(academiaComEva, "Eva", "Personal Ana", eva, f2.personalAna, null,
                    "Aluna sem plano: deve passar a ter o Personal Ana"),
              Arguments.of(f3.academia, "Zé", "Natação Noite", f3.carla, f3.musculacaoLivre, NaoEncontradoException.class,
                    "Aluno não cadastrado: deve lançar NaoEncontradoException"),
              Arguments.of(f4.academia, "Carla", "Yoga", f4.carla, f4.musculacaoLivre, NaoEncontradoException.class,
                    "Plano não cadastrado: deve lançar NaoEncontradoException e a Carla deve manter o plano atual")
        );
    }
}
