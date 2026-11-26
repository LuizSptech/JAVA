package school.sptech;

import school.sptech.exceptions.DadoInvalidoException;
import school.sptech.exceptions.EstoqueInsuficienteException;

public class Produto {
private String nome;
private Double preco;
private Integer estoque;

    public Produto(String nome, Double preco, Integer estoque) {
        if (nome == null || nome.isBlank()){
            throw new DadoInvalidoException("Nome invalido");

        }
        if (preco == null || preco <= 0.0){
            throw new DadoInvalidoException("preco invalido");

        }
        if (estoque == null || estoque < 0) {
            throw new DadoInvalidoException("Não tem no estoque");
        }
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public void vender(Integer quantidade) throws EstoqueInsuficienteException {
        if (quantidade == null || quantidade <= 0){
            throw new DadoInvalidoException("Quantidade invalida");
        }
        if (quantidade > estoque){
            throw new EstoqueInsuficienteException("Quantidade maior que estoque");
        }
        estoque -= quantidade;
    }



    public void repor(Integer quantidade){
        if (quantidade == null || quantidade <= 0){
            throw new DadoInvalidoException("Quantidade invalida");
        }
        estoque += quantidade;
    }


    public Integer getGarantia(){
        return 3;
    }

    public Double calcularFrete(){
        return 30.0;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
