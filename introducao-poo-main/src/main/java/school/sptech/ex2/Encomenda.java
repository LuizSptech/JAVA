package school.sptech.ex2;

public class Encomenda {
    String tamanho;
    String enderecoRementente;
    String enderecoDestinatario;
    Double distancia;
    Double valorProduto;

    Double calcularFrete(){
        Double freteTotal;
        Double valorAdicional;

        if (tamanho.equals("P")){
            freteTotal += valorProduto * 0.01;
        }
        if (tamanho.equals("M")){
            freteTotal += valorProduto * 0.03;
        }else {
            freteTotal += valorProduto * 0.05;
        }

        if (distancia <= 50.0){
            valorAdicional = 3.0;
        }
        if (distancia <= 200.0){
            valorAdicional = 5.0;
        }else {
            valorAdicional = 7.0;
        }
            freteTotal += valorAdicional;
        return freteTotal;
    }

    void aplicarCupomDeDesconto(Integer desconto){
        valorProduto = valorProduto * (desconto / 100);
    }

    Double valorTotalDaEncomenda(){
        calcularFrete();
        Double montante = valorProduto;
        return montante;
    }
}
