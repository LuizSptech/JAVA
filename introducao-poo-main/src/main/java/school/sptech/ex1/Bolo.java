package school.sptech.ex1;

public class Bolo {
    String sabor;
    Double valor;
    Integer quantidadeVendida;
    Integer quantidadeEmEstoque;


    Boolean venderBolo(Integer qtdDesejada){
        if (qtdDesejada == null || qtdDesejada <= 0){
            System.out.println("precisa ser maior que 1");
            return false;
        }
        if (qtdDesejada > quantidadeEmEstoque){
            System.out.println("Não temos o suficiente");
            return false;
        }
        quantidadeEmEstoque -= qtdDesejada;
        quantidadeVendida += qtdDesejada;
        System.out.println("Bolo vendido com sucesso " );
        return true;


    }
    Boolean aumentarEstoque(Integer qtdAdicionada){
        if (qtdAdicionada < 0 ){
            System.out.println("Precisa ser maior que 1");
            return false;
        }
        quantidadeEmEstoque += qtdAdicionada;
        System.out.println("Quantidade adicionada " + quantidadeEmEstoque);
        return true;
    }

    Integer quantidadeDisponivel(){
        System.out.println(quantidadeEmEstoque);
        return quantidadeEmEstoque;
    }

    Double totalVendido(){
        Double montante = valor * quantidadeVendida;
        System.out.println(montante);
        return montante;
    }


}
