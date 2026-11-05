package school.sptech.factory;

import school.sptech.Livraria;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import school.sptech.util.ObjectFieldBuilder;

public class LivrariaFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Livraria> clazz = Livraria.class;

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCampos = { "nome", "produtos" };

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(String nome, List<?> produtos) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Livraria.class)
        .with("nome", nome)
        .with("produtos", produtos)
        .build();
  }
}
