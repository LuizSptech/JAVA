package school.sptech;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContaCorrente {
    private String titular;
    private String agencia;
    private String numero;
    private List<Operacao> operacoes = new ArrayList<>();



    public  void adicionarOperacao(String categoria, String descricao, Double valor){
        if (categoria == null || categoria.isBlank()){
            return;
        }
        if (descricao == null || descricao.isBlank()){
            return;
        }
        if (valor == null || valor <= 0.0){
            return;

        }
        Operacao opera = new Operacao(categoria,descricao,valor);
        operacoes.add(opera);


    }

    public Double obterSaldo(){
        Double valorTotal = 0.0;
        for (Operacao operacoe : operacoes) {
            valorTotal += operacoe.getValor();
        }
        if (operacoes.size() < 0){
            return 0.0;
        }
        return valorTotal;
    }

    public List<Operacao> buscarOperacoesPorCategoria(String categoria){
     String cat = categoria.toLowerCase();
     String gato;
     List<Operacao> devolve = new ArrayList<>();
        for (Operacao operacoe : operacoes) {
            gato = operacoe.getCategoria();
            if (cat.equals(gato.toLowerCase())){
                devolve.add(operacoe);
            };
        }
        return devolve;
    }

    public List<Operacao> buscarOperacoesPorValor(Double valor){
        List<Operacao> devolve = new ArrayList<>();
        for (Operacao operacoe : operacoes){
            if (operacoe.getValor().equals(valor)){
                devolve.add(operacoe);
            }
        }
        return devolve;
    }


    public List<Operacao> buscarOperacoesSaida(){
        List<Operacao> devolve = new ArrayList<>();
        for (Operacao operacoe : operacoes){
            if (operacoe.getValor() < 0.0){
                devolve.add(operacoe);
            }
        }
        return devolve;
    }

    public List<Operacao> buscarOperacoesPorDescricao(String descricao){
        List<Operacao> devolve = new ArrayList<>();
        if (descricao == null){
            return devolve;
        }
        String cat = descricao.toLowerCase();
        String gato;
        for (Operacao operacoe : operacoes){
            gato = operacoe.getDescricao();
            if (gato.toLowerCase().contains(cat)){
                devolve.add(operacoe);
            };
        }
        return devolve;
    }

    public Double buscarMenorValor() {
        if (operacoes.isEmpty()){
            return 0.0;
        }
       Double menorValor = operacoes.getFirst().getValor();
       for (Operacao operacoe : operacoes){
           if (operacoe.getValor() < menorValor){
               menorValor = operacoe.getValor();
           }
       }
       return menorValor;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Operacao> getOperacoes() {
        return operacoes;
    }


}
