package school.sptech.util;

public class CampoUtil {

    public static Object campo(Object objeto, String nomeCampo) throws ReflectiveOperationException {
        return new ObjectFieldMapping<>(objeto.getClass()).get(nomeCampo).get(objeto);
    }
}
