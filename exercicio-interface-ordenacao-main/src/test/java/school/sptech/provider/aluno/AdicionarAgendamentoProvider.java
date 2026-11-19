package school.sptech.provider.aluno;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exception.DadoInvalidoException;
import school.sptech.factory.AlunoFactory;
import school.sptech.factory.AvaliacaoFisicaFactory;
import school.sptech.factory.PlanoFactory;

public class AdicionarAgendamentoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Object natacao = PlanoFactory.natacao("Natação", 150.0, "TARDE", 2, 3, 0);
        Object avaliacao = AvaliacaoFisicaFactory.build("Bioimpedância", "Dra. Lia", 80.0, 3, 0);
        Class<DadoInvalidoException> invalido = DadoInvalidoException.class;

        return Stream.of(
              Arguments.of(AlunoFactory.build("Ana", null, List.of()), natacao, List.of(natacao), null,
                    "Aluno sem agendamentos: deve adicionar a natação como único agendamento"),
              Arguments.of(AlunoFactory.build("Bruno", null, List.of(natacao)), avaliacao, List.of(natacao, avaliacao), null,
                    "Aluno com a natação agendada: deve adicionar a avaliação ao final da lista"),
              Arguments.of(AlunoFactory.build("Carla", null, List.of(natacao)), null, List.of(natacao), invalido,
                    "Agendável nulo: deve lançar DadoInvalidoException e a lista não deve mudar"),
              Arguments.of(AlunoFactory.build("Diego", null, List.of(avaliacao)), avaliacao, List.of(avaliacao), invalido,
                    "Agendamento repetido: deve lançar DadoInvalidoException e a lista não deve mudar")
        );
    }
}
