package school.sptech.factory;

import school.sptech.Desenvolvedor;
import school.sptech.especialistas.DesenvolvedorMobile;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import school.sptech.util.ObjectFieldBuilder;

public class DesenvolvedorMobileFactory {

  public static Object build(String nome, Integer qtdHoras, Double valorHora,
       String plataforma, String linguagem, Integer horasPrototipacao) throws ReflectiveOperationException {
    Object obj = new ObjectFieldBuilder(DesenvolvedorMobile.class)
          .with("nome", nome)
          .with("qtdHoras", qtdHoras)
          .with("valorHora", valorHora)
          .with("plataforma", plataforma)
          .with("linguagem", linguagem)
          .with("horasPrototipacao", horasPrototipacao)
          .build();

    return obj;
  }
}
