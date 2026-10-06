package school.sptech.factory;

import school.sptech.Desenvolvedor;
import school.sptech.util.ObjectFieldBuilder;

public class DesenvolvedorFactory {

    public static Object build(String nome, Integer qtdHoras, Double valorHora)
          throws ReflectiveOperationException {
        Object obj = new ObjectFieldBuilder<>(Desenvolvedor.class)
              .with("nome", nome)
              .with("qtdHoras", qtdHoras)
              .with("valorHora", valorHora)
              .build();

        return obj;
    }
}
