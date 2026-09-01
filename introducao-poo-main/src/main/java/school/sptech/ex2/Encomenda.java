package school.sptech.ex2;

public class Encomenda {
    String tamanho;
    String enderecoRemetente;
    String enderecoDestinatario;
    Double distancia;
    Double valorProduto;

    Double calcularFrete(){
        Double freteTotal = 0.0;
        Double valorAdicional = 0.0;

        if (tamanho.equals("P")){
            freteTotal += valorProduto * 0.01;
        }
        else if (tamanho.equals("M")){
            freteTotal += valorProduto * 0.03;
        }else {
            freteTotal += valorProduto * 0.05;
        }

        if (distancia <= 50.0){
            valorAdicional = 3.0;
        }
        else if (distancia <= 200.0){
            valorAdicional = 5.0;
        }else {
            valorAdicional = 7.0;
        }
            freteTotal += valorAdicional;
        return freteTotal;
    }

    Double aplicarCupomDeDesconto(Integer desconto){

        Double descontin = valorProduto * (desconto/100.0);
        valorProduto = valorProduto - descontin;
        return valorProduto;
    }

    Double valorTotalDaEncomenda(){
        calcularFrete();
         valorProduto +=

    }
}
