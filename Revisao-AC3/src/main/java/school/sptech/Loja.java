package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.EstoqueInsuficienteException;
import school.sptech.exceptions.ProdutoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nome;
    private List<Produto> produtos = new ArrayList<>();


    public Loja(String nome) {
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException("Nome invalido");
        }

        this.nome = nome;
    }


    public void adicionarProduto(Produto produto){
        if (produto == null || produto.getNome().isBlank()){
            throw new ProdutoNaoEncontradoException("Não encontrado");
        }
        produtos.add(produto);
    }
    public Produto buscarPorNome(String nome){
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException("Nome null");
        }
        Produto prod = null;
        for (Produto produto : produtos) {
            String a = produto.getNome();
            if (a.equalsIgnoreCase(nome)){
                prod = produto;
            }
        }
        if (prod == null){
            throw new ProdutoNaoEncontradoException("oloco");
        }
        return prod;
    }


    
}
