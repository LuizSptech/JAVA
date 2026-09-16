package school.sptech.ex01.util;

import java.util.HashMap;
import java.util.Map;
import org.mockito.Mockito;
import school.sptech.ex01.Produto;

public class ProdutoMock {

  public static Produto novo(String nome, String categoria, Double preco) {
    Map<String, Object> valores = new HashMap<>();
    valores.put("getNome", nome);
    valores.put("getCategoria", categoria);
    valores.put("getPreco", preco);

    return Mockito.mock(Produto.class, invocation -> {
      String metodo = invocation.getMethod().getName();
      if (valores.containsKey(metodo)) {
        return valores.get(metodo);
      }
      return Mockito.RETURNS_DEFAULTS.answer(invocation);
    });
  }
}
