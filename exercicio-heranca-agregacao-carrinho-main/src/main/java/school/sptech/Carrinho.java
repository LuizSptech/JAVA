package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private Integer id;
    private List<Produto> produtos = new ArrayList<>();


    public void adicionarProduto(Produto produto){
        if (produto.getPreco() == null ||  produto.getPeso() == null ||produto.getNome() == null){
        return;
        }
        if (produto.getPreco() <= 0.0 || produto.getPeso() <= 0.0 || produto.getNome().isBlank()){
            return;
        }
        produtos.add(produto);
    }

    public void removerProduto(String nome){
     List<Produto> sobra = new ArrayList<>();
     for (Produto prods : produtos){
         if (!prods.getNome().equalsIgnoreCase(nome)){
             sobra.add(prods);
         }
     }
     produtos.clear();
     produtos.addAll(sobra);
    }

    public Produto buscarProdutoPorNome(String nome){
        for (Produto prods : produtos){
            if (prods.getNome().equalsIgnoreCase(nome)){
                return prods;
            }
        }
        return null;
    }


    public Double calcularValorTotal(){
        if (produtos.isEmpty()){
            return 0.0;
        }
        Double total = 0.0;
        for (Produto prods : produtos){
            total += prods.calcularPrecoFinal();
        }
        return total;
    }

    public Double calcularTotalFrete(){
        if (produtos.isEmpty()){
            return 0.0;
        }
        Double total = 0.0;
        for (Produto prods : produtos){
            total += prods.calcularFrete();
        }
        return total;
    }
    public List<Celular> buscarCelularesPorMarca(String marca){
       List<Celular> phones = new ArrayList<>();
         for (Produto prods : produtos){
            if (prods instanceof Celular cell){
                if (cell.getMarca().equalsIgnoreCase(marca)){
                    phones.add(cell);
                }
            }
        }
         return phones;
    }

    public List<Celular> buscarCelularesForaDaGarantia(Integer mesesDeUso){
        List<Celular> garantiaNePai = new ArrayList<>();
        for (Produto prods : produtos){
            if (prods instanceof Celular cell){
                if (cell.getGarantiaMeses() < mesesDeUso){
                    garantiaNePai.add(cell);

                }            }
        }
        return garantiaNePai;
    }

    public Produto buscarProdutoMaisBarato(){
        if (produtos.isEmpty()){
            return null;
        }
        Produto menorPreco = produtos.getFirst();
        for (Produto produto : produtos) {
            if (produto.calcularPrecoFinal() < menorPreco.calcularPrecoFinal()){
                menorPreco = produto;
            }
        }
        return menorPreco;
    }
    public Produto buscarProdutoMaisCaro(){
        if (produtos.isEmpty()){
            return null;
        }
        Produto maiorPreco = produtos.getFirst();
        for (Produto produto : produtos) {
            if (produto.calcularPrecoFinal() > maiorPreco.calcularPrecoFinal()){
                maiorPreco = produto;
            }
        }
        return maiorPreco;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
