package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.AcademiaFixture;

public class GetNomesAlunosOrdenadosProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f = new AcademiaFixture();
        Object prefixos = AcademiaFactory.build("SPTech Fit", List.of(),
              List.of(AlunoFactory.build("Ana Clara", null), AlunoFactory.build("Alice", null),
                    AlunoFactory.build("Ana", null)), List.of());

        return Stream.of(
              Arguments.of(f.academia, List.of("Ana", "Bruno", "Carla", "Diego"),
                    "Cadastro como Carla, Bruno, Ana, Diego: deve retornar Ana, Bruno, Carla, Diego"),
              Arguments.of(prefixos, List.of("Alice", "Ana", "Ana Clara"),
                    "Nomes com o mesmo começo: o mais curto deve vir antes (Ana antes de Ana Clara)"),
              Arguments.of(AcademiaFactory.vazia(), List.of(), "Academia sem alunos: deve retornar lista vazia")
        );
    }
}
