package school.sptech.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReflectionUtil {

    public static Object getField(Object obj, String nomeCampo)
          throws ReflectiveOperationException {
        Field campo = obj.getClass().getDeclaredField(nomeCampo);
        campo.setAccessible(true);
        return campo.get(obj);
    }

    /**
     * Verifica se a classe possui o atributo com o nome e o tipo esperados,
     * com mensagens de erro que dizem exatamente o que está errado.
     */
    public static void assertField(Class<?> clazz, String nomeCampo, Class<?> tipoEsperado) {
        Field campo;

        try {
            campo = clazz.getDeclaredField(nomeCampo);
        } catch (NoSuchFieldException e) {
            fail("Atributo '" + nomeCampo + "' não encontrado na classe " + clazz.getSimpleName()
                  + ". Verifique se o nome está escrito exatamente como no enunciado.");
            return;
        }

        assertEquals(tipoEsperado, campo.getType(),
              "Atributo '" + nomeCampo + "' da classe " + clazz.getSimpleName()
                    + " deveria ser do tipo " + tipoEsperado.getSimpleName()
                    + ", mas foi declarado como " + campo.getType().getSimpleName()
                    + ". Lembre-se: utilize apenas tipos wrapper.");
    }

    /**
     * Verifica se a classe possui o método com o nome e os parâmetros esperados,
     * com mensagens de erro que dizem exatamente o que está errado.
     */
    public static void assertMethod(Class<?> clazz, String nomeMetodo, Class<?>... parametros) {
        try {
            clazz.getDeclaredMethod(nomeMetodo, parametros);
        } catch (NoSuchMethodException e) {
            String assinatura = nomeMetodo + "(" + Arrays.stream(parametros)
                  .map(Class::getSimpleName)
                  .collect(Collectors.joining(", ")) + ")";

            fail("Método '" + assinatura + "' não encontrado na classe " + clazz.getSimpleName()
                  + ". Verifique se o nome, os parâmetros e a ordem deles estão exatamente como no enunciado.");
        }
    }
}
