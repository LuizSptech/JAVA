package school.sptech.provider.academia;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.PlanoFactory;
import school.sptech.provider.AcademiaFixture;

public class OrdenarPlanosPorMensalidadeProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        AcademiaFixture f = new AcademiaFixture();
        Object embaralhada = AcademiaFactory.build("SPTech Fit",
              List.of(f.personalAna, f.natacaoNoite, f.musculacaoLivre, f.natacaoManha), List.of(), List.of());
        Object zumba = PlanoFactory.musculacao("Zumba", 90.0, false);
        Object livre = PlanoFactory.musculacao("Academia Livre", 100.0, true);
        Object basico = PlanoFactory.musculacao("Básico", 80.0, false);
        Object decrescente = AcademiaFactory.build("SPTech Fit", List.of(livre, zumba, basico), List.of(),
              List.of());

        return Stream.of(
              Arguments.of(embaralhada, List.of(f.musculacaoLivre, f.natacaoManha, f.natacaoNoite, f.personalAna),
                    "Cadastro embaralhado: deve ordenar pela mensalidade, Musculação (120.00), Manhã (135.00), Noite (172.50), Personal (250.00)"),
              Arguments.of(decrescente, List.of(basico, zumba, livre),
                    "Cadastro em ordem decrescente: deve inverter, Básico (80.00), Zumba (90.00), Academia Livre (120.00 com 24h)"),
              Arguments.of(AcademiaFactory.vazia(), List.of(), "Academia sem planos: deve retornar lista vazia")
        );
    }
}
