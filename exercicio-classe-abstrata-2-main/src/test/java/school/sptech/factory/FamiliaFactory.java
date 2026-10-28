package school.sptech.factory;

import school.sptech.Familia;
import school.sptech.util.ObjectFieldBuilder;

public class FamiliaFactory {

    public static Object build(String nome, String documento, Integer quarto, Integer dias,
          Double consumo, Integer qtdCriancas)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Familia.class)
              .with("nome", nome)
              .with("documento", documento)
              .with("quarto", quarto)
              .with("dias", dias)
              .with("consumo", consumo)
              .with("qtdCriancas", qtdCriancas)
              .build();
    }
}
