package school.sptech.provider.desenvolvedormobile;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class CalcularSalarioProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    return Stream.of(
        Arguments.of("Bianca: 32h x R$ 68.7 + 12h de prototipação x R$ 200.0 = R$ 4598.4", "Bianca", 32, 68.7, "Android", "Kotlin", 12, 4598.4),
        Arguments.of("Enzo: 47h x R$ 65.2 + 18h de prototipação x R$ 200.0 = R$ 6664.4", "Enzo", 47, 65.2, "iOS", "Swift", 18, 6664.4),
        Arguments.of("Mariana: 34h x R$ 52.8 + 15h de prototipação x R$ 200.0 = R$ 4795.2", "Mariana", 34, 52.8, "Android", "Java", 15, 4795.2),
        Arguments.of("Gustavo: 45h x R$ 59.5 + 13h de prototipação x R$ 200.0 = R$ 5277.5", "Gustavo", 45, 59.5, "iOS", "Swift", 13, 5277.5),
        Arguments.of("Sofia: 40h x R$ 61.3 + 16h de prototipação x R$ 200.0 = R$ 5652.0", "Sofia", 40, 61.3, "Android", "Kotlin", 16, 5652.0),
        Arguments.of("Leonardo: 50h x R$ 44.7 + 10h de prototipação x R$ 200.0 = R$ 4235.0", "Leonardo", 50, 44.7, "iOS", "Swift", 10, 4235.0),
        Arguments.of("Valentina: 30h x R$ 67.4 + 19h de prototipação x R$ 200.0 = R$ 5822.0", "Valentina", 30, 67.4, "Android", "Java", 19, 5822.0),
        Arguments.of("Joaquim: 37h x R$ 52.9 + 14h de prototipação x R$ 200.0 = R$ 4757.3", "Joaquim", 37, 52.9, "iOS", "Swift", 14, 4757.3),
        Arguments.of("Alice: 42h x R$ 46.5 + 11h de prototipação x R$ 200.0 = R$ 4153.0", "Alice", 42, 46.5, "Android", "Kotlin", 11, 4153.0),
        Arguments.of("Rafael: 39h x R$ 68.1 + 17h de prototipação x R$ 200.0 = R$ 6055.9", "Rafael", 39, 68.1, "iOS", "Swift", 17, 6055.9),
        Arguments.of("Heitor: 40h x R$ 50.0 + 0h de prototipação = R$ 2000.0 (sem horas de prototipação)", "Heitor", 40, 50.0, "iOS", "Swift", 0, 2000.0)
    );
  }
}
