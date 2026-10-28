/*import school.sptech.ContaCorrente;

import java.util.ArrayList;
import java.util.List;

public class Banco extends ContaCorrente{
    private String nome;
    private List<ContaCorrente> membros;


    public Banco(String nome, List<ContaCorrente> membros) {
        super("Nome", "Numero");
        this.nome = nome;
        this.membros = new ArrayList<>();
    }


    public void Adicionar(ContaCorrente usuario){
        membros.add(usuario);
        Integer qtd = 0;
        for (ContaCorrente membro : membros) {
            qtd++;
        }
        System.out.println("Quantidade de usuarios: %d".formatted(qtd));
    }
}
*/