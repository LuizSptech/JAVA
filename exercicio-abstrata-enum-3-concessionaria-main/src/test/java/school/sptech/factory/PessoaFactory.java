package school.sptech.factory;

import school.sptech.Pessoa;
import school.sptech.util.ObjectFieldBuilder;

public class PessoaFactory {

    public static Object build(String nome, String cpf) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Pessoa.class)
              .with("nome", nome)
              .with("cpf", cpf)
              .build();
    }
}
