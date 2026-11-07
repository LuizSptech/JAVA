package school.sptech.factory;

import java.util.ArrayList;
import java.util.List;
import school.sptech.Veiculo;
import school.sptech.util.ObjectFieldBuilder;

public class VeiculoFactory {

    public static Object build(String placa, String modelo, String marca, Double precoBase,
          Integer anosUso, Integer kmRodados) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(Veiculo.class)
              .with("placa", placa)
              .with("modelo", modelo)
              .with("marca", marca)
              .with("precoBase", precoBase)
              .with("anosUso", anosUso)
              .with("kmRodados", kmRodados)
              .build();
    }

    public static List<Object> veiculos = new ArrayList<>();

    static {
        try {
            veiculos.add(VeiculoFactory.build("AAA1111", "F8 Tributo", "Ferrari", 2_000_000.0, 2,
                  20_000));

            veiculos.add(VeiculoFactory.build("BBB2222", "Huracan", "Lamborghini", 1_800_000.0, 3,
                  50_000));
            veiculos.add(VeiculoFactory.build("CCC3333", "911 Carrera", "Porsche", 900_000.0, 1,
                  15_000));
            veiculos.add(
                  VeiculoFactory.build("DDD4444", "Mustang", "Ford", 300_000.0, 5, 100_000));
            veiculos.add(
                  VeiculoFactory.build("EEE5555", "Corolla", "Toyota", 150_000.0, 0, 10_000));
            veiculos.add(VeiculoFactory.build("FFF6666", "X5", "BMW", 350_000.0, 4, 80_000));
            veiculos.add(VeiculoFactory.build("GGG7777", "A6", "Audi", 250_000.0, 2, 30_000));
            veiculos.add(VeiculoFactory.build("HHH8888", "Camaro", "Chevrolet", 280_000.0, 6,
                  120_000));
            veiculos.add(VeiculoFactory.build("III9999", "Civic", "Honda", 140_000.0, 3, 25_000));
            veiculos.add(VeiculoFactory.build("JJJ0000", "370Z", "Nissan", 180_000.0, 1, 10_000));
            veiculos.add(VeiculoFactory.build("KKK1111", "C-Class", "Mercedes", 400_000.0, 2,
                  40_000));
            veiculos.add(VeiculoFactory.build("LLL2222", "Model S", "Tesla", 500_000.0, 0, 0));
            veiculos.add(VeiculoFactory.build("MMM3333", "Portofino", "ferrari", 1_800_000.0, 3,
                  70_000));
            veiculos.add(VeiculoFactory.build("NNN4444", "Aventador", "LAMBORGHINI", 2_500_000.0, 1,
                  5_000));
            veiculos.add(
                  VeiculoFactory.build("OOO5555", "Macan", "Porsche", 800_000.0, 4, 90_000));
            veiculos.add(VeiculoFactory.build("PPP6666", "F-150", "Ford", 180_000.0, 0, 0));
            veiculos.add(
                  VeiculoFactory.build("QQQ7777", "Hilux", "Toyota", 250_000.0, 7, 150_000));
            veiculos.add(VeiculoFactory.build("RRR8888", "M3", "BMW", 400_000.0, 5, 60_000));
            veiculos.add(VeiculoFactory.build("SSS9999", "Q7", "Audi", 350_000.0, 3, 40_000));
            veiculos.add(VeiculoFactory.build("TTT0000", "Silverado", "Chevrolet", 280_000.0, 2,
                  15_000));
            veiculos.add(
                  VeiculoFactory.build("UUU1111", "Accord", "Honda", 130_000.0, 6, 90_000));
            veiculos.add(
                  VeiculoFactory.build("VVV2222", "Altima", "Nissan", 140_000.0, 4, 50_000));
            veiculos.add(VeiculoFactory.build("WWW3333", "E-Class", "Mercedes", 450_000.0, 1,
                  10_000));
            veiculos.add(
                  VeiculoFactory.build("XXX4444", "Model X", "Tesla", 600_000.0, 2, 20_000));
            veiculos.add(
                  VeiculoFactory.build("YYY5555", "LaFerrari", "Ferrari", 3_500_000.0, 0, 0));
            veiculos.add(VeiculoFactory.build("ZZZ6666", "Urus", "Lamborghini", 3_000_000.0, 5,
                  80_000));
            veiculos.add(VeiculoFactory.build("AAA7777", "Panamera", "PorschE", 900_000.0, 6,
                  110_000));
            veiculos.add(
                  VeiculoFactory.build("BBB8888", "Explorer", "Ford", 280_000.0, 3, 70_000));
            veiculos.add(VeiculoFactory.build("CCC9999", "RAV4", "Toyota", 220_000.0, 1, 30_000));
            veiculos.add(VeiculoFactory.build("DDD0000", "i8", "BMW", 700_000.0, 0, 0));
            veiculos.add(VeiculoFactory.build("EEE1111", "911 Turbo", "Porsche", 1_200_000.0, 2,
                  50_000));
        } catch (ReflectiveOperationException e) {
        }
    }
}
