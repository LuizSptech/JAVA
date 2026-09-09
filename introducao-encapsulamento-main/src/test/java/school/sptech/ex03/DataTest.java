package school.sptech.ex03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import school.sptech.ex03.provider.CompararDatasProvider;
import school.sptech.ex03.provider.DefinirDataProvider;
import school.sptech.ex03.provider.FormatarDataProvider;
import school.sptech.util.ObjectFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static school.sptech.util.ReflectionUtil.assertAllFieldsPrivate;
import static school.sptech.util.ReflectionUtil.assertGetter;
import static school.sptech.util.ReflectionUtil.assertMethodReturnType;
import static school.sptech.util.ReflectionUtil.assertNoSetter;
import static school.sptech.util.ReflectionUtil.assertPrivateField;
import static school.sptech.util.ReflectionUtil.assertPublicMethod;
import static school.sptech.util.ReflectionUtil.getField;

@DisplayName("Data")
class DataTest {

    private final Class<Data> clazz = Data.class;

    private Data novaData(Integer dia, Integer mes, Integer ano) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Data.class)
              .with("dia", dia)
              .with("mes", mes)
              .with("ano", ano)
              .build();
    }

    @Nested
    @DisplayName("Atributos")
    class AtributosTest {

        @Test
        @DisplayName("Deve conter os atributos com os nomes e tipos corretos")
        void validarAtributos() {
            assertPrivateField(clazz, "dia", Integer.class);
            assertPrivateField(clazz, "mes", Integer.class);
            assertPrivateField(clazz, "ano", Integer.class);
        }
    }

    @Nested
    @DisplayName("Encapsulamento")
    class EncapsulamentoTest {

        @Test
        @DisplayName("Todos os atributos devem ser privados")
        void atributosPrivados() {
            assertAllFieldsPrivate(clazz);
        }

        @Test
        @DisplayName("Deve possuir apenas getters publicos (sem setters)")
        void gettersSemSetters() {
            assertGetter(clazz, "dia", Integer.class);
            assertGetter(clazz, "mes", Integer.class);
            assertGetter(clazz, "ano", Integer.class);
            assertNoSetter(clazz, "dia", Integer.class);
            assertNoSetter(clazz, "mes", Integer.class);
            assertNoSetter(clazz, "ano", Integer.class);
        }
    }

    @Nested
    @DisplayName("Metodos")
    class MetodosTest {

        @Test
        @DisplayName("Deve conter os metodos publicos com as assinaturas e retornos corretos")
        void validarMetodos() {
            assertPublicMethod(clazz, "definirData", Integer.class, Integer.class, Integer.class);
            assertMethodReturnType(clazz, "definirData", void.class,
                  Integer.class, Integer.class, Integer.class);
            assertPublicMethod(clazz, "formatarData");
            assertMethodReturnType(clazz, "formatarData", String.class);
            assertPublicMethod(clazz, "compararDatas", Integer.class, Integer.class, Integer.class);
            assertMethodReturnType(clazz, "compararDatas", Integer.class,
                  Integer.class, Integer.class, Integer.class);
        }
    }

    @Nested
    @DisplayName("definirData(Integer, Integer, Integer)")
    class DefinirDataTest {

        @ParameterizedTest(name = "definirData({0}, {1}, {2}) -> dia={3}, mes={4}, ano={5}")
        @ArgumentsSource(DefinirDataProvider.class)
        @DisplayName("Deve atualizar os atributos apenas quando a data for totalmente valida")
        void deveDefinirData(Integer diaP, Integer mesP, Integer anoP,
              Integer diaEsp, Integer mesEsp, Integer anoEsp) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "definirData", Integer.class, Integer.class, Integer.class);
            Method metodo = clazz.getDeclaredMethod("definirData",
                  Integer.class, Integer.class, Integer.class);
            metodo.setAccessible(true);

            Data data = novaData(10, 6, 2010);

            invocar(metodo, data, diaP, mesP, anoP);

            assertEquals(diaEsp, getField(data, "dia"),
                  "definirData nao tratou o atributo dia conforme esperado");
            assertEquals(mesEsp, getField(data, "mes"),
                  "definirData nao tratou o atributo mes conforme esperado");
            assertEquals(anoEsp, getField(data, "ano"),
                  "definirData nao tratou o atributo ano conforme esperado");
        }
    }

    @Nested
    @DisplayName("formatarData()")
    class FormatarDataTest {

        @ParameterizedTest(name = "dia={0}, mes={1}, ano={2} -> \"{3}\"")
        @ArgumentsSource(FormatarDataProvider.class)
        @DisplayName("Deve retornar a data no formato DD/MM/AAAA")
        void deveFormatarData(Integer dia, Integer mes, Integer ano, String esperado)
              throws ReflectiveOperationException {

            assertPublicMethod(clazz, "formatarData");
            assertMethodReturnType(clazz, "formatarData", String.class);
            Method metodo = clazz.getDeclaredMethod("formatarData");
            metodo.setAccessible(true);

            Data data = novaData(dia, mes, ano);

            Object retorno = invocar(metodo, data);

            assertEquals(esperado, retorno,
                  "formatarData() deveria retornar exatamente \"" + esperado + "\"");
        }
    }

    @Nested
    @DisplayName("compararDatas(Integer, Integer, Integer)")
    class CompararDatasTest {

        @ParameterizedTest(name = "obj=({0},{1},{2}) vs param=({3},{4},{5}) -> {6}")
        @ArgumentsSource(CompararDatasProvider.class)
        @DisplayName("Deve comparar a data informada com a data do objeto")
        void deveCompararDatas(Integer diaObj, Integer mesObj, Integer anoObj,
              Integer diaP, Integer mesP, Integer anoP, Integer esperado) throws ReflectiveOperationException {

            assertPublicMethod(clazz, "compararDatas", Integer.class, Integer.class, Integer.class);
            Method metodo = clazz.getDeclaredMethod("compararDatas",
                  Integer.class, Integer.class, Integer.class);
            metodo.setAccessible(true);

            Data data = novaData(diaObj, mesObj, anoObj);

            Object retorno = invocar(metodo, data, diaP, mesP, anoP);

            assertEquals(esperado, retorno,
                  "compararDatas() nao retornou o valor esperado");
        }
    }

    private static Object invocar(Method metodo, Object alvo, Object... args) {
        try {
            return metodo.invoke(alvo, args);
        } catch (InvocationTargetException e) {
            fail("O metodo '" + metodo.getName() + "' lancou " + e.getCause()
                  + " para os dados do cenario.");
        } catch (IllegalAccessException e) {
            fail("Nao foi possivel invocar o metodo '" + metodo.getName() + "': " + e.getMessage());
        }
        return null;
    }
}
