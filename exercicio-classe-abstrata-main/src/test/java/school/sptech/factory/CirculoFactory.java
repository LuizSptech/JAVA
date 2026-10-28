package school.sptech.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.Circulo;
import school.sptech.util.ObjectFieldBuilder;

public class CirculoFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Circulo> clazz = Circulo.class;
        Class<?> superClazz = clazz.getSuperclass();

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCamposSuper = {"cor", "espessura"};
        String[] nomeCampos = {"raio"};

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

    public static Object build(String cor, Integer espessura, Double raio)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Circulo.class)
              .with("cor", cor)
              .with("espessura", espessura)
              .with("raio", raio)
              .build();
    }
}
