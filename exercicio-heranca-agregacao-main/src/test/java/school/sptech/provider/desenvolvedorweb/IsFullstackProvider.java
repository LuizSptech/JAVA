package school.sptech.provider.desenvolvedorweb;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class IsFullstackProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Sem backend deve retornar false", "Bianca", 32, 68.7, "React", null, "PostgreSQL", 12, false),
        Arguments.of("Com frontend, backend e sgbd deve retornar true", "Enzo", 47, 65.2, "Vue", "Express", "MySQL", 18, true),
        Arguments.of("Com frontend, backend e sgbd deve retornar true", "Mariana", 34, 52.8, "Angular", "Rails", "MongoDB", 15, true),
        Arguments.of("Sem sgbd deve retornar false", "Gustavo", 45, 59.5, "Svelte", "Flask", null, 13, false),
        Arguments.of("Sem frontend deve retornar false", "Sofia", 40, 61.3, null, "Laravel", "SQL Server", 16, false),
        Arguments.of("Com frontend, backend e sgbd deve retornar true", "Leonardo", 50, 44.7, "Next.js", ".NET Core", "Redis", 10, true),
        Arguments.of("Sem backend e sem sgbd deve retornar false", "Valentina", 30, 67.4, "React", null, null, 19, false),
        Arguments.of("Sem frontend deve retornar false", "Joaquim", 37, 52.9, null, "Node.js", "MySQL", 14, false),
        Arguments.of("Com frontend, backend e sgbd deve retornar true", "Alice", 42, 46.5, "React", "Spring Boot", "PostgreSQL", 11, true),
        Arguments.of("Com frontend, backend e sgbd deve retornar true", "Rafael", 39, 68.1, "Svelte", "Django", "MariaDB", 17, true),
        Arguments.of("Sem frontend, backend e sgbd deve retornar false", "Heitor", 40, 50.0, null, null, null, 5, false)
    );
  }
}
