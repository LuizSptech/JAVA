package school.sptech.factory;

import school.sptech.Leitor;
import school.sptech.util.ObjectFieldBuilder;

import java.util.ArrayList;
import java.util.List;

public class LeitorFactory {

  public static Object build(String nome, String email, List<?> publicacoesRecebidas, List<?> publicacoesFavoritas, String temaFavorito) throws ReflectiveOperationException {
    return new ObjectFieldBuilder<>(Leitor.class)
        .with("nome", nome)
        .with("email", email)
        .with("publicacoesRecebidas", new ArrayList<>(publicacoesRecebidas))
        .with("publicacoesFavoritas", new ArrayList<>(publicacoesFavoritas))
        .withEnum("temaFavorito", temaFavorito)
        .build();
  }
}
