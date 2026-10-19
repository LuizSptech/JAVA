package school.sptech.factory;

import java.util.List;
import school.sptech.Carrinho;
import school.sptech.util.ObjectFieldBuilder;

public class CarrinhoFactory {

    public static Object build(Integer id, List<?> produtos) throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Carrinho.class)
              .with("id", id)
              .with("produtos", produtos)
              .build();
    }
}
