package school.sptech.provider.consultoria;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.DesenvolvedorMobileFactory;
import school.sptech.factory.DesenvolvedorWebFactory;

import java.util.List;
import java.util.stream.Stream;

import static school.sptech.factory.DesenvolvedorFactory.build;

public class BuscarTipoComMaiorMediaSalarialProvider implements ArgumentsProvider {

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

    // Médias iguais: web 1500.0, mobile 1500.0, comum 1500.0
    Object t1 = DesenvolvedorWebFactory.build("Igor", 10, 100.0, "React", "Node", "MySQL", 0);
    Object t2 = DesenvolvedorWebFactory.build("Nina", 10, 200.0, "React", "Node", "MySQL", 0);
    Object t3 = DesenvolvedorMobileFactory.build("Caio", 20, 100.0, "iOS", "Swift", 0);
    Object t4 = DesenvolvedorMobileFactory.build("Vera", 10, 100.0, "iOS", "Swift", 0);
    Object t5 = build("Rita", 10, 150.0);
    Object t6 = build("Hugo", 10, 100.0);

    // Maior salário individual x maior média
    // web: 10000.0, 1000.0, 1000.0 (média 4000.0)
    // mobile: 5000.0, 5000.0, 5000.0 (média 5000.0)
    // comum: 9000.0, 100.0 (média 4550.0)
    Object w1 = DesenvolvedorWebFactory.build("Helena", 100, 100.0, "React", "Node", "MySQL", 0);
    Object w2 = DesenvolvedorWebFactory.build("Bruno", 10, 100.0, "React", "Node", "MySQL", 0);
    Object w3 = DesenvolvedorWebFactory.build("Clara", 10, 100.0, "React", "Node", "MySQL", 0);
    Object m1 = DesenvolvedorMobileFactory.build("Otavio", 50, 100.0, "iOS", "Swift", 0);
    Object m2 = DesenvolvedorMobileFactory.build("Luana", 50, 100.0, "iOS", "Swift", 0);
    Object m3 = DesenvolvedorMobileFactory.build("Felipe", 50, 100.0, "iOS", "Swift", 0);
    Object c1 = build("Diego", 90, 100.0);
    Object c2 = build("Lara", 1, 100.0);

    return Stream.of(
        Arguments.of("Consultoria vazia deve retornar null", "Consultoria 1", 10, List.of(), null),
        Arguments.of("Apenas desenvolvedores comuns deve retornar \"comum\"", "Consultoria 2", 10, List.of(d1, d2, d3, d4, d5), "comum"),
        Arguments.of("Apenas desenvolvedores web deve retornar \"web\"", "Consultoria 3", 10, List.of(d6, d7), "web"),
        Arguments.of("Apenas desenvolvedores mobile deve retornar \"mobile\"", "Consultoria 4", 10, List.of(d11, d12), "mobile"),
        Arguments.of("Os três tipos com média web maior deve retornar \"web\"", "Consultoria 5", 10, List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15), "web"),
        Arguments.of("Web e mobile com média mobile maior deve retornar \"mobile\"", "Consultoria 6", 10, List.of(d6, d12, d15), "mobile"),
        Arguments.of("Os três tipos com média comum maior deve retornar \"comum\"", "Consultoria 7", 10, List.of(p1, d1, d6, d12), "comum"),
        Arguments.of("Comum e web com média comum maior deve retornar \"comum\"", "Consultoria 8", 10, List.of(p1, d6), "comum"),
        Arguments.of("Deve comparar as médias, não a soma dos salários: retorna \"web\"", "Consultoria 9", 10, List.of(d1, d2, d3, d4, d5, d6), "web"),
        Arguments.of("Deve comparar as médias, não o maior salário individual: retorna \"mobile\"", "Consultoria 10", 10, List.of(w1, w2, w3, m1, m2, m3, c1, c2), "mobile"),
        Arguments.of("Médias web e mobile iguais (sem comuns) deve retornar null", "Consultoria 11", 10, List.of(t1, t2, t3, t4), null),
        Arguments.of("Médias dos três tipos iguais deve retornar null", "Consultoria 12", 10, List.of(t1, t2, t3, t4, t5), null),
        Arguments.of("Empate na maior média (web e mobile), mesmo com comum menor, deve retornar null", "Consultoria 13", 10, List.of(t1, t2, t3, t4, t6), null),
        Arguments.of("Empate na maior média (comum e web), mesmo com mobile menor, deve retornar null", "Consultoria 14", 10, List.of(t5, t1, t2, t4), null)
    );
  }
}
