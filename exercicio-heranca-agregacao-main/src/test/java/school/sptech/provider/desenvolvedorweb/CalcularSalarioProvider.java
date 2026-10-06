package school.sptech.provider.desenvolvedorweb;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
    return Stream.of(
        Arguments.of("Bianca: 32h x R$ 68.7 + 12h de mentoria x R$ 300.0 = R$ 5798.4", "Bianca", 32, 68.7, "React", "Django", "PostgreSQL", 12, 5798.4),
        Arguments.of("Enzo: 47h x R$ 65.2 + 18h de mentoria x R$ 300.0 = R$ 8464.4", "Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18, 8464.4),
        Arguments.of("Mariana: 34h x R$ 52.8 + 15h de mentoria x R$ 300.0 = R$ 6295.2", "Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15, 6295.2),
        Arguments.of("Gustavo: 45h x R$ 59.5 + 13h de mentoria x R$ 300.0 = R$ 6577.5", "Gustavo", 45, 59.5, "Svelte", "Flask", "Oracle", 13, 6577.5),
        Arguments.of("Sofia: 40h x R$ 61.3 + 16h de mentoria x R$ 300.0 = R$ 7252.0", "Sofia", 40, 61.3, "Ember", "Laravel", "SQL Server", 16, 7252.0),
        Arguments.of("Leonardo: 50h x R$ 44.7 + 10h de mentoria x R$ 300.0 = R$ 5235.0", "Leonardo", 50, 44.7, "Next.js", ".NET Core", "Redis", 10, 5235.0),
        Arguments.of("Valentina: 30h x R$ 67.4 + 19h de mentoria x R$ 300.0 = R$ 7722.0", "Valentina", 30, 67.4, "React", "Spring Boot", "Cassandra", 19, 7722.0),
        Arguments.of("Joaquim: 37h x R$ 52.9 + 14h de mentoria x R$ 300.0 = R$ 6157.3", "Joaquim", 37, 52.9, "Vue", "Node.js", "MySQL", 14, 6157.3),
        Arguments.of("Alice: 42h x R$ 46.5 + 11h de mentoria x R$ 300.0 = R$ 5253.0", "Alice", 42, 46.5, "React", "Phoenix", "PostgreSQL", 11, 5253.0),
        Arguments.of("Rafael: 39h x R$ 68.1 + 17h de mentoria x R$ 300.0 = R$ 7755.9", "Rafael", 39, 68.1, "Svelte", "Django", "MariaDB", 17, 7755.9),
        Arguments.of("Heitor: 40h x R$ 50.0 + 0h de mentoria = R$ 2000.0 (sem horas de mentoria)", "Heitor", 40, 50.0, "React", "Node.js", "MySQL", 0, 2000.0)
    );
  }
}
