package school.sptech.factory;

import school.sptech.Produto;
import school.sptech.util.ObjectFieldBuilder;

public class ProdutoFactory {

    public static Object build(String nome, Double preco, Double peso)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Produto.class)
              .with("nome", nome)
              .with("preco", preco)
              .with("peso", peso)
              .build();
    }
}
