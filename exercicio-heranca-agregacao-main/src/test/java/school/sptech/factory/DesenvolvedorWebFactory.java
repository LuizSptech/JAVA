package school.sptech.factory;

import school.sptech.especialistas.DesenvolvedorWeb;
import school.sptech.util.ObjectFieldBuilder;

public class DesenvolvedorWebFactory {

    public static Object build(String nome, Integer qtdHoras, Double valorHora,
          String frontend, String backend, String sgbd, Integer horasMentoria)
          throws ReflectiveOperationException {
        Object obj = new ObjectFieldBuilder<>(DesenvolvedorWeb.class)
              .with("nome", nome)
              .with("qtdHoras", qtdHoras)
              .with("valorHora", valorHora)
              .with("frontend", frontend)
              .with("backend", backend)
              .with("sgbd", sgbd)
              .with("horasMentoria", horasMentoria)
              .build();

        return obj;
    }
}
