package school.sptech.factory;

import school.sptech.JogoTabuleiro;
import school.sptech.Raridade;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.util.ObjectFieldBuilder;

public class JogoTabuleiroFactory {

  static Map<String, Field> campos() throws ReflectiveOperationException {
    Class<JogoTabuleiro> clazz = JogoTabuleiro.class;
    Class<?> superClazz = clazz.getSuperclass();

    Map<String, Field> mapCampos = new HashMap<>();
    String[] nomeCamposSuper = { "nome", "raridade" };
    String[] nomeCampos = { "qtdPecas" };

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

  public static Object build(String nome, String raridade, Integer qtdPecas) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(JogoTabuleiro.class)
        .with("nome", nome)
        .with("raridade", Raridade.valueOf(raridade))
        .with("qtdPecas", qtdPecas)
        .build();
  }
}
