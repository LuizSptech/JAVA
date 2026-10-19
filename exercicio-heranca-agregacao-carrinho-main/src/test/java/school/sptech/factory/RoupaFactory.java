package school.sptech.factory;

import school.sptech.Roupa;
import school.sptech.util.ObjectFieldBuilder;

public class RoupaFactory {

    public static Object build(String nome, Double preco, Double peso, String cor, Double tamanho,
          String material) throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Roupa.class)
              .with("nome", nome)
              .with("preco", preco)
              .with("peso", peso)
              .with("cor", cor)
              .with("tamanho", tamanho)
              .with("material", material)
              .build();
    }
}
