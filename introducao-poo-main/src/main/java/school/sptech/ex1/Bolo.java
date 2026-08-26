package school.sptech.ex1;

public class Bolo {
    String sabor;
    Double valor;
    Integer quantidadeVendida;
    Integer quantidedeEmEstoque;


    Boolean venderBolo(Integer qtdDesejada){
        if (qtdDesejada == 0){
            return false;
        }
        if (qtdDesejada > quantidedeEmEstoque){
            return false;
        }
        quantidedeEmEstoque -= qtdDesejada;
        quantidadeVendida += qtdDesejada;
        return true;

    }
    Boolean aumentarEstoque(Integer qtdAdicionada){
        if (qtdAdicionada < 0 ){
            return false;
        }
        quantidedeEmEstoque += qtdAdicionada;
        return true;
    }

    Integer quantidedeDisponivel(){
        return quantidedeEmEstoque;
    }

    Integer totalVendido(){
        return quantidadeVendida;
    }


}
