package school.sptech.ex1;

public class TesteConfeitaria {

  public static void main(String[] args) {
    Bolo n1 = new Bolo();
    n1.sabor = "chocolate";
    n1.valor = 12.2;
    n1.quantidadeVendida = 5;
    n1.quantidadeEmEstoque = 3;



    n1.venderBolo(1);
    n1.aumentarEstoque(5);
    n1.quantidadeDisponivel();
    n1.totalVendido();
  }
}
