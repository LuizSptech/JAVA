package school.sptech.factory;

import school.sptech.Jornal;
import school.sptech.util.ObjectFieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class JornalFactory {

    public static Object build(String nome, List<?> observadores) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Jornal.class)
            .with("nome", nome)
            .with("observadores", new ArrayList<>(observadores))
            .build();
    }
}
