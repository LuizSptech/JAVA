package school.sptech.provider;

import java.util.List;
import school.sptech.factory.AcademiaFactory;
import school.sptech.factory.AlunoFactory;
import school.sptech.factory.AvaliacaoFisicaFactory;
import school.sptech.factory.PlanoFactory;

/**
 * Mesmo cenário dos exemplos do README.
 *
 * <pre>
 * Plano              Mensalidade  Vagas
 * Musculação Livre   120.00       -        (24h)
 * Natação Manhã      135.00       0        (1 raia x 2 alunos, 2 inscritos)
 * Natação Noite      172.50       6        (2 raias x 3 alunos, 0 inscritos)
 * Personal Ana       250.00       0        (1 sessão, 1 agendada)
 * Bioimpedância      -            1        (avaliação: limite 3, 2 agendadas)
 *
 * Aluno   Plano
 * Carla   Musculação Livre
 * Bruno   Natação Manhã
 * Ana     Natação Manhã
 * Diego   Personal Ana
 * </pre>
 */
public class AcademiaFixture {

    public final Object musculacaoLivre;
    public final Object natacaoManha;
    public final Object natacaoNoite;
    public final Object personalAna;
    public final Object bioimpedancia;

    public final Object carla;
    public final Object bruno;
    public final Object ana;
    public final Object diego;

    public final Object academia;

    public AcademiaFixture() throws ReflectiveOperationException {
        musculacaoLivre = PlanoFactory.musculacao("Musculação Livre", 100.0, true);
        natacaoManha = PlanoFactory.natacao("Natação Manhã", 150.0, "MANHA", 1, 2, 2);
        natacaoNoite = PlanoFactory.natacao("Natação Noite", 150.0, "NOITE", 2, 3, 0);
        personalAna = PlanoFactory.personal("Personal Ana", 200.0, "Ana", 1, 1);
        bioimpedancia = AvaliacaoFisicaFactory.build("Bioimpedância", "Dra. Lia", 80.0, 3, 2);

        carla = AlunoFactory.build("Carla", musculacaoLivre);
        bruno = AlunoFactory.build("Bruno", natacaoManha);
        ana = AlunoFactory.build("Ana", natacaoManha);
        diego = AlunoFactory.build("Diego", personalAna);

        academia = AcademiaFactory.build("SPTech Fit",
              List.of(musculacaoLivre, natacaoManha, natacaoNoite, personalAna),
              List.of(carla, bruno, ana, diego),
              List.of(bioimpedancia));
    }

    public List<Object> planos() {
        return List.of(musculacaoLivre, natacaoManha, natacaoNoite, personalAna);
    }

    public List<Object> alunos() {
        return List.of(carla, bruno, ana, diego);
    }
}
