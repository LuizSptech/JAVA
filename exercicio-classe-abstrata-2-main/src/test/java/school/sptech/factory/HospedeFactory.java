package school.sptech.factory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import school.sptech.Hospede;

public class HospedeFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Hospede> superClazz = Hospede.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] superCampos = {"nome", "documento", "quarto", "dias", "consumo"};

        for (String campoNome : superCampos) {
            Field campo = superClazz.getDeclaredField(campoNome);
            campo.trySetAccessible();
            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static class CalcularDiariaInterceptor {

        private final Double diaria;

        public CalcularDiariaInterceptor(Double diaria) {
            this.diaria = diaria;
        }

        @RuntimeType
        public Double calcularFake() {
            return diaria;
        }
    }

    public static Hospede construir(String nome, String documento, Integer quarto,
          Integer dias, Double consumo) throws Throwable {
        Class<? extends Hospede> dynamicType = new ByteBuddy()
              .subclass(Hospede.class)
              .make()
              .load(Hospede.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
              .getLoaded();

        try {
            return dynamicType
                  .getConstructor(String.class, String.class, Integer.class, Integer.class,
                        Double.class)
                  .newInstance(nome, documento, quarto, dias, consumo);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public static Hospede build(String nome, String documento, Integer quarto, Integer dias,
          Double consumo, Double diaria)
          throws ReflectiveOperationException {
        Class<? extends Hospede> dynamicType = new ByteBuddy()
              .subclass(Hospede.class)
              .defineMethod("calcularDiaria", Double.class, Modifier.PUBLIC)
              .intercept(MethodDelegation.to(new CalcularDiariaInterceptor(diaria)))
              .make()
              .load(Hospede.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
              .getLoaded();

        Objenesis objenesis = new ObjenesisStd();
        Hospede hospede = objenesis.newInstance(dynamicType);

        Map<String, Field> mapCampos = campos();
        mapCampos.get("nome").set(hospede, nome);
        mapCampos.get("documento").set(hospede, documento);
        mapCampos.get("quarto").set(hospede, quarto);
        mapCampos.get("dias").set(hospede, dias);
        mapCampos.get("consumo").set(hospede, consumo);

        return hospede;
    }
}
