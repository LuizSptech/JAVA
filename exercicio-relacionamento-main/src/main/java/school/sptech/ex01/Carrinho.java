package school.sptech.ex01;

import java.util.List;

public class Carrinho {

    private String cliente;
    private List<Produto> produtos;

    public Integer getQuantidade() {
        return produtos.size();
    }

    public void adicionar(Produto p) {
        produtos.add(p);
    }

    public Boolean existsPorNome(String nome) {
        for (Produto produto : this.produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return true;
            }
        }
        return false;
    }

    public Integer getQuantidadePorCategoria(String nome) {
        Integer contador = 0;
        for (Produto produto : produtos) {
            if (produto.getCategoria().equalsIgnoreCase(nome)) {
                contador++;
            }
        }
        return contador;
    }

    public void limpar() {
        produtos.removeAll(produtos);
    }

    public void removerPorNome(String nome) {
        if (nome == null) {
            return;
        }
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
                produtos.remove(i);
            }
        }
    }

    public Produto getPorNome(String nome) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getNome().equalsIgnoreCase(nome)) {
                return produtos.get(i);
            }
        }
        return null;
    }



    public Double getValorTotal(){
        Double valorTotal = 0.0;
        for (Produto produto : this.produtos) {
            valorTotal += produto.getPreco();
        }
        return valorTotal;
    }
}