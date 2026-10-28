package school.sptech.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.Retangulo;
import school.sptech.util.ObjectFieldBuilder;

public class RetanguloFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<Retangulo> clazz = Retangulo.class;
    Class<?> superClazz = clazz.getSuperclass();

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "cor", "espessura" };
    String[] nomeCampos = { "base", "altura" };

    for (String campoNome : nomeCamposSuper) {
      Field campo = superClazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    for (String campoNome : nomeCampos) {
      Field campo = clazz.getDeclaredField(campoNome);
      campo.trySetAccessible();

      mapCampos.put(campoNome, campo);
    }

    return mapCampos;
  }

  public static Object build(String cor, Integer espessura, Double base, Double altura) throws ReflectiveOperationException {
   return new ObjectFieldBuilder<>(Retangulo.class)
         .with("cor", cor)
         .with("espessura", espessura)
         .with("base", base)
         .with("altura", altura)
         .build();
  }
}
