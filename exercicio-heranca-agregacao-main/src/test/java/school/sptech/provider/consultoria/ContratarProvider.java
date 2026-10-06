package school.sptech.provider.consultoria;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.List;
import java.util.stream.Stream;

import static school.sptech.factory.DesenvolvedorFactory.build;
import static school.sptech.factory.DesenvolvedorMobileFactory.build;
import static school.sptech.factory.DesenvolvedorWebFactory.build;

public class ContratarProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Object d1 = build("Paulo", 32, 58.7);
    Object d2 = build("Lívia", 47, 67.3);
    Object d3 = build("Elisa", 34, 45.2);
    Object d4 = build("Yasmin", 45, 49.8);
    Object d5 = build("Tiago", 40, 64.4);

    Object d6 = build("Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12);
    Object d7 = build("Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18);
    Object d8 = build("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15);
    Object d9 = build("Gustavo", 45, 59.5, "Svelte", "Flask", "Oracle", 13);
    Object d10 = build("Sofia", 40, 61.3, "Ember", "Laravel", "SQL Server", 16);

    Object d11 = build("Leonardo", 50, 44.7, "iOS", "Swift", 10);
    Object d12 = build("Valentina", 30, 67.4, "Android", "Java", 19);
    Object d13 = build("Joaquim", 37, 52.9, "iOS", "Swift", 14);
    Object d14 = build("Alice", 42, 46.5, "Android", "Kotlin", 11);
    Object d15 = build("Rafael", 39, 68.1, "iOS", "Swift", 17);

    return Stream.of(
        Arguments.of("Deve contratar quando há vagas e a consultoria está vazia", "Consultoria 1", 10, List.of(), d1, List.of(d1)),
        Arguments.of("Não deve contratar quando a consultoria não possui vagas (vagas = 0)", "Consultoria 2", 0, List.of(), d1, List.of()),
        Arguments.of("Deve contratar quando ainda resta uma vaga (1 de 2 ocupadas)", "Consultoria 3", 2, List.of(d1), d2, List.of(d1, d2)),
        Arguments.of("Não deve contratar quando todas as vagas estão ocupadas (3 de 3) - vagas é a capacidade máxima", "Consultoria 4", 3, List.of(d1, d2, d3), d4, List.of(d1, d2, d3)),
        Arguments.of("Deve contratar mantendo a ordem de contratação (2 de 5 ocupadas)", "Consultoria 5", 5, List.of(d1, d2), d3, List.of(d1, d2, d3)),
        Arguments.of("Deve contratar desenvolvedor web (polimorfismo)", "Consultoria 6", 3, List.of(d1), d6, List.of(d1, d6)),
        Arguments.of("Deve contratar desenvolvedor mobile (polimorfismo)", "Consultoria 7", 2, List.of(d6), d11, List.of(d6, d11))
    );
  }
}
