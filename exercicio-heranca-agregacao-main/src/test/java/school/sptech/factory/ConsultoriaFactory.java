package school.sptech.factory;

import java.util.List;
import school.sptech.Consultoria;
import school.sptech.util.ObjectFieldBuilder;

public class ConsultoriaFactory {

    public static Object build(String nome, Integer vagas, List<?> desenvolvedores)
          throws ReflectiveOperationException {
        Object obj = new ObjectFieldBuilder<>(Consultoria.class)
              .with("nome", nome)
              .with("vagas", vagas)
              .with("desenvolvedores", desenvolvedores)
              .build();

        return obj;
    }
}
