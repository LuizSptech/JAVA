package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.exception.NaoEncontradoException;
import school.sptech.exception.VagaIndisponivelException;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AlunoFactory;
import school.sptech.provider.AcademiaFixture;

public class AgendarProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        AcademiaFixture f2 = new AcademiaFixture();
        AcademiaFixture f3 = new AcademiaFixture();
        AcademiaFixture f4 = new AcademiaFixture();
        AcademiaFixture f5 = new AcademiaFixture();
        Object eva = AlunoFactory.build("Eva", f5.natacaoNoite, List.of(f5.natacaoNoite));
        Object academiaComEva = AcademiaFactory.build("SPTech Fit", f5.planos(), List.of(eva), List.of());
        AcademiaFixture f6 = new AcademiaFixture();
        AcademiaFixture f7 = new AcademiaFixture();

        // academia, aluno, agendável, exceção, objeto aluno, agendamentos esperados, contador, valor esperado, cenário
        return Stream.of(
              Arguments.of(f1.academia, "Carla", f1.natacaoNoite, null, f1.carla, List.of(f1.natacaoNoite),
                    "inscritos", 1,
                    "Natação com vagas: deve reservar a vaga (inscritos de 0 para 1) e adicionar a turma aos agendamentos da Carla"),
              Arguments.of(f2.academia, "ANA", f2.bioimpedancia, null, f2.ana, List.of(f2.bioimpedancia),
                    "agendadas", 3,
                    "Avaliação com vaga e nome do aluno em outra caixa (\"ANA\"): deve reservar a vaga (agendadas de 2 para 3) e adicionar a avaliação aos agendamentos da Ana"),
              Arguments.of(f3.academia, "Carla", null, DadoInvalidoException.class, f3.carla, List.of(),
                    null, null,
                    "Agendável nulo: deve lançar DadoInvalidoException"),
              Arguments.of(f4.academia, "Zé", f4.natacaoNoite, NaoEncontradoException.class, f4.carla, List.of(),
                    "inscritos", 0,
                    "Aluno não cadastrado: deve lançar NaoEncontradoException sem reservar a vaga"),
              Arguments.of(academiaComEva, "Eva", f5.natacaoNoite, DadoInvalidoException.class, eva,
                    List.of(f5.natacaoNoite), "inscritos", 0,
                    "Aluno já possui o agendamento: deve lançar DadoInvalidoException sem reservar outra vaga"),
              Arguments.of(f6.academia, "Carla", f6.natacaoManha, VagaIndisponivelException.class, f6.carla, List.of(),
                    "inscritos", 2,
                    "Natação lotada (2 de 2): ao tentar reservar a vaga, deve lançar VagaIndisponivelException e não adicionar o agendamento"),
              Arguments.of(f7.academia, "Carla", f7.personalAna, VagaIndisponivelException.class, f7.carla, List.of(),
                    "sessoesAgendadas", 1,
                    "Personal sem sessões livres (1 de 1): ao tentar reservar a vaga, deve lançar VagaIndisponivelException e não adicionar o agendamento")
        );
    }
}
