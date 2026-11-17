package school.sptech.factory;

import school.sptech.Noticia;
import school.sptech.util.ObjectFieldBuilder;

public class NoticiaFactory {

  public static Object build(String titulo, String tema, String autor, Integer minutosLeitura) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Noticia.class)
        .with("titulo", titulo)
        .withEnum("tema", tema)
        .with("autor", autor)
        .with("minutosLeitura", minutosLeitura)
        .build();
  }
}
