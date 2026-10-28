package school.sptech.factory;

import java.util.List;
import school.sptech.Hotel;
import school.sptech.util.ObjectFieldBuilder;

public class HotelFactory {

    public static Object build(String nome, List<?> hospedes) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Hotel.class)
              .with("nome", nome)
              .with("hospedes", hospedes)
              .build();
    }
}
