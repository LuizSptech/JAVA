package school.sptech.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import school.sptech.Imagem;
import school.sptech.util.ObjectFieldBuilder;

public class ImagemFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Imagem> clazz = Imagem.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = {"figuras"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(List<?> figuras) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Imagem.class)
              .with("figuras", figuras)
              .build();
    }
}
