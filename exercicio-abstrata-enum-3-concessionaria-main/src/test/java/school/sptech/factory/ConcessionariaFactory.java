package school.sptech.factory;

import java.util.List;
import school.sptech.Concessionaria;
import school.sptech.util.ObjectFieldBuilder;

public class ConcessionariaFactory {

    public static Object build(String nome, List<?> financiamentos)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Concessionaria.class)
              .with("nome", nome)
              .with("financiamentos", financiamentos)
              .build();
    }
}
