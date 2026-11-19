package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.AcademiaFixture;

public class CalcularFaturamentoMensalProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        AcademiaFixture f2 = new AcademiaFixture();
        Object semPlano = AlunoFactory.build("Eva", null);
        Object comAlunoSemPlano = AcademiaFactory.build("SPTech Fit", f2.planos(), List.of(f2.carla, semPlano), List.of());
        AcademiaFixture f3 = new AcademiaFixture();
        Object natacaoDupla = AcademiaFactory.build("SPTech Fit", f3.planos(), List.of(f3.bruno, f3.ana), List.of());

        return Stream.of(
              Arguments.of(f1.academia, 640.0, "Quatro alunos matriculados: deve somar a mensalidade de cada um, 120.00 + 135.00 + 135.00 + 250.00 = 640.00"),
              Arguments.of(natacaoDupla, 270.0, "Dois alunos no mesmo plano: deve somar a mensalidade duas vezes, 135.00 + 135.00 = 270.00"),
              Arguments.of(comAlunoSemPlano, 120.0, "Aluno sem plano: deve ficar fora da soma, total 120.00"),
              Arguments.of(AcademiaFactory.vazia(), 0.0, "Academia sem alunos: deve retornar 0.00")
        );
    }
}
