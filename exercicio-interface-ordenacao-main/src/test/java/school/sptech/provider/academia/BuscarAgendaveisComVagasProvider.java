package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AcademiaFactory;
import school.sptech.provider.AcademiaFixture;

public class BuscarAgendaveisComVagasProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f1 = new AcademiaFixture();
        AcademiaFixture f2 = new AcademiaFixture();
        Object todosLotados = AcademiaFactory.build("SPTech Fit",
              List.of(f2.musculacaoLivre, f2.natacaoManha, f2.personalAna), List.of(), List.of());

        return Stream.of(
              Arguments.of(f1.academia, List.of(f1.natacaoNoite, f1.bioimpedancia),
                    "Natação Noite (6 vagas) e Bioimpedância (1 vaga): deve retornar os planos primeiro e depois as avaliações"),
              Arguments.of(todosLotados, List.of(),
                    "Só agendáveis lotados e Musculação (não agendável): deve retornar lista vazia"),
              Arguments.of(AcademiaFactory.vazia(), List.of(), "Academia sem planos e sem avaliações: deve retornar lista vazia")
        );
    }
}
