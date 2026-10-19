package school.sptech.factory;

import school.sptech.Celular;
import school.sptech.util.ObjectFieldBuilder;

public class CelularFactory {

    public static Object build(String nome, Double preco, Double peso, Integer garantiaMeses,
          String marca) throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Celular.class)
              .with("nome", nome)
              .with("preco", preco)
              .with("peso", peso)
              .with("garantiaMeses", garantiaMeses)
              .with("marca", marca)
              .build();
    }
}
