package school.sptech.factory;

import school.sptech.Executivo;
import school.sptech.util.ObjectFieldBuilder;

public class ExecutivoFactory {

    public static Object build(String nome, String documento, Integer quarto, Integer dias,
          Double consumo, Boolean vip, Boolean internet, Boolean empresaConveniada)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Executivo.class)
              .with("nome", nome)
              .with("documento", documento)
              .with("quarto", quarto)
              .with("dias", dias)
              .with("consumo", consumo)
              .with("vip", vip)
              .with("internet", internet)
              .with("empresaConveniada", empresaConveniada)
              .build();
    }
}
