package school.sptech.util;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.function.Executable;

public class EncapsulamentoUtil {

    public static void assertPossuiAtributos(Class<?> clazz, String... nomes) {
        Stream<Executable> validacoes = Arrays.stream(nomes)
              .map(nome -> () -> assertDoesNotThrow(() -> clazz.getDeclaredField(nome),
                    String.format("%s deve possuir o atributo %s", clazz.getSimpleName(), nome)));

        assertAll(validacoes);
    }

    public static void assertAtributosPrivados(Class<?> clazz) {
        Field[] campos = clazz.getDeclaredFields();

        Stream<Executable> validacoes = Arrays.stream(campos)
              .filter(campo -> !campo.isSynthetic())
              .map(campo -> () -> assertTrue(Modifier.isPrivate(campo.getModifiers()),
                    String.format("%s deve ser privado", campo.getName())));

        assertAll(validacoes);
    }

    public static void assertApenasGetters(Class<?> clazz) {
        Field[] campos = Arrays.stream(clazz.getDeclaredFields())
              .filter(campo -> !campo.isSynthetic())
              .toArray(Field[]::new);

        Stream<Executable> validacoesGetter = Arrays.stream(campos)
              .map(campo -> () -> {
                  String getName = String.format("get%s", StringUtils.capitalize(campo.getName()));
                  assertDoesNotThrow(() -> {
                      Method getter = clazz.getDeclaredMethod(getName);
                      assertTrue(Modifier.isPublic(getter.getModifiers()),
                            String.format("Getter %s deve ser público", getName));
                  }, String.format("Deve possuir o getter %s", getName));
              });

        Stream<Executable> validacoesSetter = Arrays.stream(campos)
              .map(campo -> () -> {
                  String setName = String.format("set%s", StringUtils.capitalize(campo.getName()));
                  assertThrows(NoSuchMethodException.class,
                        () -> clazz.getDeclaredMethod(setName, campo.getType()),
                        String.format("%s não deve possuir setter", campo.getName()));
              });

        assertAll(Stream.concat(validacoesGetter, validacoesSetter));
    }

    public static void assertPossuiMetodo(Class<?> clazz, String nome, Class<?>... parametros) {
        assertDoesNotThrow(() -> clazz.getDeclaredMethod(nome, parametros),
              String.format("%s deve possuir o método %s", clazz.getSimpleName(), nome));
    }
}
