package school.sptech.provider;

import school.sptech.factory.AlunoFactory;

public class AlunosFixture {

  // média 8.5 - APROVADO
  public static Object ana() throws ReflectiveOperationException {
    return AlunoFactory.build("001", "Ana", 8.0, 9.0, 90);
  }

  // média 4.5 - RECUPERACAO
  public static Object bruno() throws ReflectiveOperationException {
    return AlunoFactory.build("002", "Bruno", 5.0, 4.0, 80);
  }

  // média 10.0 - REPROVADO (frequência)
  public static Object carla() throws ReflectiveOperationException {
    return AlunoFactory.build("003", "Carla", 10.0, 10.0, 60);
  }

  // média 2.5 - REPROVADO
  public static Object diego() throws ReflectiveOperationException {
    return AlunoFactory.build("004", "Diego", 2.0, 3.0, 90);
  }

  // média 7.0 - APROVADO
  public static Object elisa() throws ReflectiveOperationException {
    return AlunoFactory.build("005", "Elisa", 7.0, 7.0, 100);
  }
}
