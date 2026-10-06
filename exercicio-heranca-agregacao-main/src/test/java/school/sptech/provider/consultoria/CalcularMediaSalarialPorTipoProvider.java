package school.sptech.provider.consultoria;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.DesenvolvedorMobileFactory;
import school.sptech.factory.DesenvolvedorWebFactory;

import java.util.List;
import java.util.stream.Stream;

import static school.sptech.factory.DesenvolvedorFactory.build;

public class CalcularMediaSalarialPorTipoProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    // Comuns - média 2279.06
    Object d1 = build("Paulo", 32, 58.7);
    Object d2 = build("Lívia", 47, 67.3);
    Object d3 = build("Elisa", 34, 45.2);
    Object d4 = build("Yasmin", 45, 49.8);
    Object d5 = build("Tiago", 40, 64.4);

    // Web - média 6877.5
    Object d6 = DesenvolvedorWebFactory.build("Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12);
    Object d7 = DesenvolvedorWebFactory.build("Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18);
    Object d8 = DesenvolvedorWebFactory.build("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15);
    Object d9 = DesenvolvedorWebFactory.build("Gustavo", 45, 59.5, "Svelte", "Flask", "Oracle", 13);
    Object d10 = DesenvolvedorWebFactory.build("Sofia", 40, 61.3, "Ember", "Laravel", "SQL Server", 16);

    // Mobile - média 5004.64
    Object d11 = DesenvolvedorMobileFactory.build("Leonardo", 50, 44.7, "iOS", "Swift", 10);
    Object d12 = DesenvolvedorMobileFactory.build("Valentina", 30, 67.4, "Android", "Java", 19);
    Object d13 = DesenvolvedorMobileFactory.build("Joaquim", 37, 52.9, "iOS", "Swift", 14);
    Object d14 = DesenvolvedorMobileFactory.build("Alice", 42, 46.5, "Android", "Kotlin", 11);
    Object d15 = DesenvolvedorMobileFactory.build("Rafael", 39, 68.1, "iOS", "Swift", 17);

    // Comum com salário alto (50000.0)
    Object p1 = build("Marcos", 100, 500.0);

    List<Object> todos = List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15);

    return Stream.of(
        Arguments.of("Média do tipo \"comum\" com os três tipos na consultoria", "Consultoria 1", 10, todos, "comum", 2279.06),
        Arguments.of("Média do tipo \"web\" com os três tipos na consultoria", "Consultoria 2", 10, todos, "web", 6877.5),
        Arguments.of("Média do tipo \"mobile\" com os três tipos na consultoria", "Consultoria 3", 10, todos, "mobile", 5004.64),
        Arguments.of("Média do tipo \"web\" deve considerar apenas os web (Bianca e Enzo)", "Consultoria 4", 10, List.of(d1, d6, d7, d11, d12), "web", 7131.4),
        Arguments.of("Média do tipo \"comum\" não deve incluir web e mobile", "Consultoria 5", 10, List.of(p1, d1, d6, d12), "comum", 25939.2),
        Arguments.of("Tipo \"comum\" sem desenvolvedores comuns na consultoria deve retornar 0.0", "Consultoria 6", 10, List.of(d6, d7, d11, d12), "comum", 0.0),
        Arguments.of("Tipo \"mobile\" sem desenvolvedores mobile na consultoria deve retornar 0.0", "Consultoria 7", 10, List.of(d1, d2, d6), "mobile", 0.0),
        Arguments.of("Consultoria vazia deve retornar 0.0", "Consultoria 8", 10, List.of(), "web", 0.0),
        Arguments.of("Tipo inválido (\"backend\") deve retornar null", "Consultoria 9", 10, todos, "backend", null)
    );
  }
}
