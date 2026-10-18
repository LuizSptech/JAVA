package school.sptech.factory;

import school.sptech.Secretaria;
import school.sptech.util.ObjectFieldBuilder;

public class SecretariaFactory {

    public static Object build() throws ReflectiveOperationException {
        return new ObjectFieldBuilder(Secretaria.class).build();
    }
}
