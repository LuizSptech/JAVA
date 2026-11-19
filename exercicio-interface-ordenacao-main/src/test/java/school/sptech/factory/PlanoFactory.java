package school.sptech.factory;

import school.sptech.Musculacao;
import school.sptech.Natacao;
import school.sptech.Personal;
import school.sptech.util.ObjectFieldBuilder;

public class PlanoFactory {

    public static Object musculacao(String nome, Double precoBase, Boolean vinteQuatroHoras)
          throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Musculacao.class)
              .with("nome", nome)
              .with("precoBase", precoBase)
              .with("vinteQuatroHoras", vinteQuatroHoras)
              .build();
    }

    public static Object natacao(String nome, Double precoBase, String turno, Integer raias,
          Integer alunosPorRaia, Integer inscritos) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Natacao.class)
              .with("nome", nome)
              .with("precoBase", precoBase)
              .withEnum("turno", turno)
              .with("raias", raias)
              .with("alunosPorRaia", alunosPorRaia)
              .with("inscritos", inscritos)
              .build();
    }

    public static Object personal(String nome, Double precoBase, String instrutor,
          Integer sessoesSemana, Integer sessoesAgendadas) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Personal.class)
              .with("nome", nome)
              .with("precoBase", precoBase)
              .with("instrutor", instrutor)
              .with("sessoesSemana", sessoesSemana)
              .with("sessoesAgendadas", sessoesAgendadas)
              .build();
    }
}
