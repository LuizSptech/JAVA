package school.sptech.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Helpers de reflexão para os testes. Todas as verificações estruturais passam por aqui, com
 * mensagens de erro em PT-BR que dizem exatamente o que está errado. Nada aqui chama construtor
 * ou método diretamente, de modo que o módulo de teste compila mesmo com as classes de
 * {@code src/main} ainda vazias.
 */
public class ReflectionUtil {

    private ReflectionUtil() {
    }

    /**
     * Lê o valor de um atributo (mesmo privado) de um objeto já construído.
     */
    public static Object getField(Object obj, String nomeCampo) throws ReflectiveOperationException {
        Field campo = obj.getClass().getDeclaredField(nomeCampo);
        campo.setAccessible(true);
        return campo.get(obj);
    }

    /**
     * Verifica se a classe possui o atributo com o nome e o tipo esperados.
     */
    public static void assertField(Class<?> clazz, String nomeCampo, Class<?> tipoEsperado) {
        Field campo = buscarCampo(clazz, nomeCampo);

        assertEquals(tipoEsperado, campo.getType(),
              "Atributo '" + nomeCampo + "' da classe " + clazz.getSimpleName()
                    + " deveria ser do tipo " + tipoEsperado.getSimpleName()
                    + ", mas foi declarado como " + campo.getType().getSimpleName()
                    + ". Lembre-se: utilize apenas tipos wrapper.");
    }

    /**
     * Verifica se a classe possui o atributo com o nome e o tipo esperados e se ele é privado.
     */
    public static void assertPrivateField(Class<?> clazz, String nomeCampo, Class<?> tipoEsperado) {
        assertField(clazz, nomeCampo, tipoEsperado);

        Field campo = buscarCampo(clazz, nomeCampo);
        assertTrue(Modifier.isPrivate(campo.getModifiers()),
              "Atributo '" + nomeCampo + "' da classe " + clazz.getSimpleName()
                    + " deveria ser 'private'. As classes precisam seguir as regras de encapsulamento.");
    }

    /**
     * Verifica que TODOS os atributos declarados na classe são privados.
     */
    public static void assertAllFieldsPrivate(Class<?> clazz) {
        for (Field campo : clazz.getDeclaredFields()) {
            if (campo.isSynthetic()) {
                continue;
            }
            assertTrue(Modifier.isPrivate(campo.getModifiers()),
                  "Atributo '" + campo.getName() + "' da classe " + clazz.getSimpleName()
                        + " deveria ser 'private'. As classes precisam seguir as regras de encapsulamento.");
        }
    }

    /**
     * Verifica se existe um getter público ({@code get<Campo>()}) que retorna o tipo do atributo.
     */
    public static void assertGetter(Class<?> clazz, String nomeCampo, Class<?> tipoCampo) {
        String nomeGetter = "get" + StringUtils.capitalize(nomeCampo);
        Method getter;

        try {
            getter = clazz.getDeclaredMethod(nomeGetter);
        } catch (NoSuchMethodException e) {
            fail("Getter '" + nomeGetter + "()' não encontrado na classe " + clazz.getSimpleName()
                  + ". Cada atributo precisa de um getter para ser acessado.");
            return;
        }

        assertTrue(Modifier.isPublic(getter.getModifiers()),
              "Getter '" + nomeGetter + "()' da classe " + clazz.getSimpleName()
                    + " deveria ser 'public'.");
        assertEquals(tipoCampo, getter.getReturnType(),
              "Getter '" + nomeGetter + "()' da classe " + clazz.getSimpleName()
                    + " deveria retornar " + tipoCampo.getSimpleName()
                    + ", mas retorna " + getter.getReturnType().getSimpleName() + ".");
    }

    /**
     * Verifica se existe um setter público ({@code set<Campo>(tipo)}) com retorno {@code void}.
     */
    public static void assertSetter(Class<?> clazz, String nomeCampo, Class<?> tipoCampo) {
        String nomeSetter = "set" + StringUtils.capitalize(nomeCampo);
        Method setter;

        try {
            setter = clazz.getDeclaredMethod(nomeSetter, tipoCampo);
        } catch (NoSuchMethodException e) {
            fail("Setter '" + nomeSetter + "(" + tipoCampo.getSimpleName() + ")' não encontrado na classe "
                  + clazz.getSimpleName() + ". Cada atributo precisa de um setter para ser alterado.");
            return;
        }

        assertTrue(Modifier.isPublic(setter.getModifiers()),
              "Setter '" + nomeSetter + "(" + tipoCampo.getSimpleName() + ")' da classe "
                    + clazz.getSimpleName() + " deveria ser 'public'.");
        assertEquals(void.class, setter.getReturnType(),
              "Setter '" + nomeSetter + "(" + tipoCampo.getSimpleName() + ")' da classe "
                    + clazz.getSimpleName() + " deveria ter retorno 'void'.");
    }

    /**
     * Verifica que NÃO existe um setter ({@code set<Campo>(tipo)}) para o atributo.
     */
    public static void assertNoSetter(Class<?> clazz, String nomeCampo, Class<?> tipoCampo) {
        String nomeSetter = "set" + StringUtils.capitalize(nomeCampo);
        try {
            clazz.getDeclaredMethod(nomeSetter, tipoCampo);
            fail("A classe " + clazz.getSimpleName() + " NÃO deveria ter o setter '" + nomeSetter
                  + "(" + tipoCampo.getSimpleName() + ")'. O enunciado pede apenas getters para esse atributo.");
        } catch (NoSuchMethodException esperado) {
            // ok: o setter realmente não existe
        }
    }

    /**
     * Verifica se a classe possui o método com o nome e os parâmetros esperados (nome e ordem exatos).
     */
    public static void assertMethod(Class<?> clazz, String nomeMetodo, Class<?>... parametros) {
        try {
            clazz.getDeclaredMethod(nomeMetodo, parametros);
        } catch (NoSuchMethodException e) {
            fail("Método '" + assinatura(nomeMetodo, parametros) + "' não encontrado na classe "
                  + clazz.getSimpleName()
                  + ". Verifique se o nome, os parâmetros e a ordem deles estão exatamente como no enunciado.");
        }
    }

    /**
     * Verifica se o método existe (nome + parâmetros) e é público.
     */
    public static void assertPublicMethod(Class<?> clazz, String nomeMetodo, Class<?>... parametros) {
        assertMethod(clazz, nomeMetodo, parametros);

        Method metodo = buscarMetodo(clazz, nomeMetodo, parametros);
        assertTrue(Modifier.isPublic(metodo.getModifiers()),
              "Método '" + assinatura(nomeMetodo, parametros) + "' da classe " + clazz.getSimpleName()
                    + " deveria ser 'public'.");
    }

    /**
     * Verifica se o método existe (nome + parâmetros) e retorna o tipo esperado.
     */
    public static void assertMethodReturnType(Class<?> clazz, String nomeMetodo,
          Class<?> tipoRetornoEsperado, Class<?>... parametros) {
        assertMethod(clazz, nomeMetodo, parametros);

        Method metodo = buscarMetodo(clazz, nomeMetodo, parametros);
        assertEquals(tipoRetornoEsperado, metodo.getReturnType(),
              "Método '" + assinatura(nomeMetodo, parametros) + "' da classe " + clazz.getSimpleName()
                    + " deveria retornar " + tipoRetornoEsperado.getSimpleName()
                    + ", mas retorna " + metodo.getReturnType().getSimpleName() + ".");
    }

    /**
     * Verifica se a classe possui um construtor público com os parâmetros esperados.
     */
    public static void assertPublicConstructor(Class<?> clazz, Class<?>... parametros) {
        Constructor<?> construtor;
        try {
            construtor = clazz.getDeclaredConstructor(parametros);
        } catch (NoSuchMethodException e) {
            fail("Construtor '" + assinatura(clazz.getSimpleName(), parametros) + "' não encontrado na classe "
                  + clazz.getSimpleName()
                  + ". Verifique se os parâmetros e a ordem deles estão exatamente como no enunciado.");
            return;
        }

        assertTrue(Modifier.isPublic(construtor.getModifiers()),
              "Construtor '" + assinatura(clazz.getSimpleName(), parametros) + "' da classe "
                    + clazz.getSimpleName() + " deveria ser 'public'.");
    }

    private static Field buscarCampo(Class<?> clazz, String nomeCampo) {
        try {
            return clazz.getDeclaredField(nomeCampo);
        } catch (NoSuchFieldException e) {
            fail("Atributo '" + nomeCampo + "' não encontrado na classe " + clazz.getSimpleName()
                  + ". Verifique se o nome está escrito exatamente como no enunciado.");
            throw new AssertionError("inalcançável");
        }
    }

    private static Method buscarMetodo(Class<?> clazz, String nomeMetodo, Class<?>... parametros) {
        try {
            return clazz.getDeclaredMethod(nomeMetodo, parametros);
        } catch (NoSuchMethodException e) {
            fail("Método '" + assinatura(nomeMetodo, parametros) + "' não encontrado na classe "
                  + clazz.getSimpleName() + ".");
            throw new AssertionError("inalcançável");
        }
    }

    private static String assinatura(String nome, Class<?>... parametros) {
        return nome + "(" + Arrays.stream(parametros)
              .map(Class::getSimpleName)
              .collect(Collectors.joining(", ")) + ")";
    }
}
