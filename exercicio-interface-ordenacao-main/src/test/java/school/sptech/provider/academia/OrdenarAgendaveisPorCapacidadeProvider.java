package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AvaliacaoFisicaFactory;
import school.sptech.factory.PlanoFactory;
import school.sptech.provider.AcademiaFixture;

public class OrdenarAgendaveisPorCapacidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();

        Object personalBruno = PlanoFactory.personal("Personal Bruno", 200.0, "Bruno", 4, 0);
        Object tarde = PlanoFactory.natacao("Natação Tarde", 150.0, "TARDE", 2, 2, 0);
        Object noite = PlanoFactory.natacao("Natação Noite", 150.0, "NOITE", 2, 3, 0);
        Object postural = AvaliacaoFisicaFactory.build("Avaliação Postural", "Dr. Rui", 60.0, 4, 0);
        Object empate = AcademiaFactory.build("SPTech Fit", List.of(personalBruno, tarde, noite), List.of(),
              List.of(postural));

        Object lotada = PlanoFactory.natacao("Natação Lotada", 150.0, "MANHA", 2, 3, 6);
        Object vazia = AvaliacaoFisicaFactory.build("Bioimpedância", "Dra. Lia", 80.0, 3, 0);
        Object capacidadeVsVagas = AcademiaFactory.build("SPTech Fit", List.of(lotada), List.of(), List.of(vazia));

        AcademiaFixture f2 = new AcademiaFixture();
        Object soMusculacao = AcademiaFactory.build("SPTech Fit", List.of(f2.musculacaoLivre), List.of(), List.of());

        return Stream.of(
              Arguments.of(f1.academia, List.of(f1.natacaoNoite, f1.bioimpedancia, f1.natacaoManha, f1.personalAna),
                    "Capacidades diferentes: deve retornar Natação Noite (6), Bioimpedância (3), Natação Manhã (2), Personal Ana (1), sem a Musculação"),
              Arguments.of(empate, List.of(noite, postural, tarde, personalBruno),
                    "Empate de capacidade: deve desempatar pelo nome, Natação Noite (6) e depois os três com 4 de A a Z (Avaliação Postural, Natação Tarde, Personal Bruno)"),
              Arguments.of(capacidadeVsVagas, List.of(lotada, vazia),
                    "Turma lotada (capacidade 6) e avaliação vazia (capacidade 3): deve ordenar pela capacidade total, não pelas vagas livres"),
              Arguments.of(soMusculacao, List.of(), "Só Musculação (não agendável): deve retornar lista vazia")
        );
    }
}
