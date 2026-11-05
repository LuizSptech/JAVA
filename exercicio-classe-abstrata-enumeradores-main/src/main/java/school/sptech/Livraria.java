package school.sptech;

import java.util.ArrayList;
import java.util.List;

public class Livraria {
 private String nome;
 private List<Produto> produtos;


    public Livraria(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }


    public void adicionar(Produto produto){
        produtos.add(produto);
    }

    public void remover(String nome){
        for (Produto prod : produtos){
            if (prod.getNome().equalsIgnoreCase(nome)){
                produtos.remove(prod);
                return;
            }
        }
    }

    public Double calcularTotalLivros(){
        List<Produto> lista = new ArrayList<>();
        for (Produto prod : produtos){
            if (prod instanceof Livro l){
                lista.add(l);
            }
        }
        Double total = 0.0;
        for (Produto li : lista){
            total += li.calcularPreco();
        }
        return total;
    }

    public List<Livro> buscarLivrosPorFormato(Formato formato){
        List<Livro> lista = new ArrayList<>();
        if (produtos.isEmpty()){
            return lista;
        }
        for (Produto prod: produtos){
            if (prod instanceof Livro liv){
                lista.add(liv);
            }
        }

        List<Livro> livroEspecifico = new ArrayList<>();
        for (Livro li : lista){
            if (li.getFormato() == formato){
                livroEspecifico.add(li);
            }
        }
        return livroEspecifico;
    }


    public List<Produto> buscarPorRaridade(Raridade raridade){
        List<Produto> lista = new ArrayList<>();
        if (produtos.isEmpty()){
            return lista;
        }


        List<Produto> raridadeEspecifica = new ArrayList<>();
        for (Produto li : produtos){
            if (li.getRaridade().equals(raridade)){
                raridadeEspecifica.add(li);
            }
        }
        return raridadeEspecifica;
    }


    public Boolean existePorNome(String nome){
        for (Produto prod : produtos){
            if (prod.getNome().equalsIgnoreCase(nome)){
                return true;
            }
        }
        return false;
    }


    public List<JogoTabuleiro> buscarPorQtdPecasMaior(Integer qtd) {
    List<JogoTabuleiro> jogo  = new ArrayList<>();

    for (Produto prod : produtos){
        if (prod instanceof JogoTabuleiro j){
            jogo.add(j);
        }
    }
    List<JogoTabuleiro> ma = new ArrayList<>();
    for (JogoTabuleiro jos : jogo){
        if (jos.getQtdPecas() > qtd){
            ma.add(jos);
        }
    }
    return ma;
    }


}
