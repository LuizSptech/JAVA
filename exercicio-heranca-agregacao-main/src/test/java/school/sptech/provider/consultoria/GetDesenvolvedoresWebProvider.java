package school.sptech.provider.consultoria;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.DesenvolvedorMobileFactory;
import school.sptech.factory.DesenvolvedorWebFactory;

import java.util.List;
import java.util.stream.Stream;

import static school.sptech.factory.DesenvolvedorFactory.build;

public class GetDesenvolvedoresWebProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Object d1 = build("Paulo", 32, 58.7);
    Object d2 = build("Lívia", 47, 67.3);
    Object d3 = build("Elisa", 34, 45.2);

    Object d6 = DesenvolvedorWebFactory.build("Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12);
    Object d7 = DesenvolvedorWebFactory.build("Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18);
    Object d8 = DesenvolvedorWebFactory.build("Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15);
    Object d10 = DesenvolvedorWebFactory.build("Sofia", 40, 61.3, "Ember", "Laravel", "SQL Server", 16);

    Object d11 = DesenvolvedorMobileFactory.build("Leonardo", 50, 44.7, "iOS", "Swift", 10);
    Object d12 = DesenvolvedorMobileFactory.build("Valentina", 30, 67.4, "Android", "Java", 19);
    Object d13 = DesenvolvedorMobileFactory.build("Joaquim", 37, 52.9, "iOS", "Swift", 14);

    return Stream.of(
        Arguments.of("Consultoria vazia deve retornar lista vazia", "Consultoria 1", 10, List.of(), List.of()),
        Arguments.of("Apenas desenvolvedores comuns deve retornar lista vazia", "Consultoria 2", 10, List.of(d1, d2, d3), List.of()),
        Arguments.of("Apenas desenvolvedores mobile deve retornar lista vazia", "Consultoria 3", 10, List.of(d11, d12, d13), List.of()),
        Arguments.of("Apenas desenvolvedores web deve retornar todos", "Consultoria 4", 10, List.of(d6, d7, d8, d10), List.of(d6, d7, d8, d10)),
        Arguments.of("Tipos misturados deve retornar apenas os web, na ordem da lista", "Consultoria 5", 10, List.of(d1, d6, d11, d7, d12), List.of(d6, d7)),
        Arguments.of("Web no meio e no fim da lista deve retornar apenas os web", "Consultoria 6", 10, List.of(d11, d8, d2, d10), List.of(d8, d10))
    );
  }
}
