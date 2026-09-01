package school.sptech.ex3;

public class TesteEmpresa {

  public static void main(String[] args) {
        Funcionario claudio = new Funcionario();

        claudio.nome = "Claudio";
        claudio.salario = 2200.0;
        claudio.cargo = "Estagiario";

        claudio.reajustarSalario(10);
  }
}
