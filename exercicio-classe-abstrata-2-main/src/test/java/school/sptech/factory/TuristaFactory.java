package school.sptech.factory;

import school.sptech.Turista;
import school.sptech.util.ObjectFieldBuilder;

public class TuristaFactory {

    public static Object build(String nome, String documento, Integer quarto, Integer dias,
          Double consumo, Boolean guiaTuristico, Boolean altaTemporada)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Turista.class)
              .with("nome", nome)
              .with("documento", documento)
              .with("quarto", quarto)
              .with("dias", dias)
              .with("consumo", consumo)
              .with("guiaTuristico", guiaTuristico)
              .with("altaTemporada", altaTemporada)
              .build();
    }
}
