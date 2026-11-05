package school.sptech.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.Formato;
import school.sptech.Livro;
import school.sptech.Raridade;
import school.sptech.util.ObjectFieldBuilder;

public class LivroFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Livro> clazz = Livro.class;
        Class<?> superClazz = clazz.getSuperclass();

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCamposSuper = {"nome", "raridade"};
        String[] nomeCampos = {"editora", "autor", "qtdPaginas", "formato"};

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

    public static Object build(String nome, String raridade, String editora, String autor,
          Integer qtdPaginas, String formato) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Livro.class)
              .with("nome", nome)
              .with("raridade", Raridade.valueOf(raridade))
              .with("editora", editora)
              .with("autor", autor)
              .with("qtdPaginas", qtdPaginas)
              .with("formato", Formato.valueOf(formato))
              .build();
    }
}
