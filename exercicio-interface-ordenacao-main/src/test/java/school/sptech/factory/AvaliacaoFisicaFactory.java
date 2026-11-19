package school.sptech.factory;

import school.sptech.AvaliacaoFisica;
import school.sptech.util.ObjectFieldBuilder;

public class AvaliacaoFisicaFactory {

    public static Object build(String nome, String profissional, Double preco, Integer limiteDiario,
          Integer agendadas) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(AvaliacaoFisica.class)
              .with("nome", nome)
              .with("profissional", profissional)
              .with("preco", preco)
              .with("limiteDiario", limiteDiario)
              .with("agendadas", agendadas)
              .build();
    }
}
