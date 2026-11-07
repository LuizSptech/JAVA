package school.sptech.provider.veiculo;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.provider.Arguments;
import school.sptech.factory.VeiculoFactory;

public class VeiculoArgumentHelper {

    public static final List<Arguments> veiculosValorFinal = new ArrayList<>();
    public static final List<Arguments> veiculosValorPj = new ArrayList<>();
    public static final List<Arguments> veiculosValorPf = new ArrayList<>();

    static {
        if (VeiculoFactory.veiculos != null && !VeiculoFactory.veiculos.isEmpty()) {

            veiculosValorFinal.addAll(List.of(
                  Arguments.of(VeiculoFactory.veiculos.get(0), 2_159_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(1), 1_923_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(2), 980_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(3), 280_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(4), 149_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(5), 332_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(6), 243_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(7), 257_200.0),
                  Arguments.of(VeiculoFactory.veiculos.get(8), 134_800.0),
                  Arguments.of(VeiculoFactory.veiculos.get(9), 177_700.0),
                  Arguments.of(VeiculoFactory.veiculos.get(10), 390_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(11), 500_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(12), 1_922_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(13), 2_725_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(14), 843_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(15), 180_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(16), 225_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(17), 377_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(18), 337_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(19), 273_900.0),
                  Arguments.of(VeiculoFactory.veiculos.get(20), 117_700.0),
                  Arguments.of(VeiculoFactory.veiculos.get(21), 131_900.0),
                  Arguments.of(VeiculoFactory.veiculos.get(22), 445_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(23), 587_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(24), 3_850_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(25), 3_146_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(26), 930_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(27), 268_100.0),
                  Arguments.of(VeiculoFactory.veiculos.get(28), 216_300.0),
                  Arguments.of(VeiculoFactory.veiculos.get(29), 700_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(30), 1_293_500.0)
            ));
            veiculosValorPj.addAll(List.of(
                  Arguments.of(VeiculoFactory.veiculos.get(0), "SA", 2_482_850.0),
                  Arguments.of(VeiculoFactory.veiculos.get(1), "ME", 2_019_675.0),
                  Arguments.of(VeiculoFactory.veiculos.get(2), "LTDA", 1_078_550.0),
                  Arguments.of(VeiculoFactory.veiculos.get(3), "ONG", 280_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(4), "COOPERATIVA", 150_995.0),
                  Arguments.of(VeiculoFactory.veiculos.get(5), "MEI", 341_960.0),
                  Arguments.of(VeiculoFactory.veiculos.get(6), "LTDA", 267_850.0),
                  Arguments.of(VeiculoFactory.veiculos.get(7), "ME", 270_060.0),
                  Arguments.of(VeiculoFactory.veiculos.get(8), "COOPERATIVA", 136_148.0),
                  Arguments.of(VeiculoFactory.veiculos.get(9), "MEI", 183_031.0),
                  Arguments.of(VeiculoFactory.veiculos.get(10), "ONG", 390_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(11), "SA", 575_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(12), "SA", 2_210_875.0),
                  Arguments.of(VeiculoFactory.veiculos.get(13), "LTDA", 2_997_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(14), "MEI", 868_805.0),
                  Arguments.of(VeiculoFactory.veiculos.get(15), "ONG", 180_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(16), "ME", 236_250.0),
                  Arguments.of(VeiculoFactory.veiculos.get(17), "COOPERATIVA", 380_770.0),
                  Arguments.of(VeiculoFactory.veiculos.get(18), "LTDA", 371_250.0),
                  Arguments.of(VeiculoFactory.veiculos.get(19), "ME", 287_595.0),
                  Arguments.of(VeiculoFactory.veiculos.get(20), "ONG", 117_700.0),
                  Arguments.of(VeiculoFactory.veiculos.get(21), "MEI", 135_857.0),
                  Arguments.of(VeiculoFactory.veiculos.get(22), "SA", 511_750.0),
                  Arguments.of(VeiculoFactory.veiculos.get(23), "SA", 675_050.0),
                  Arguments.of(VeiculoFactory.veiculos.get(24), "SA", 4_427_500.0),
                  Arguments.of(VeiculoFactory.veiculos.get(25), "LTDA", 3_460_600.0),
                  Arguments.of(VeiculoFactory.veiculos.get(26), "MEI", 958_415.0),
                  Arguments.of(VeiculoFactory.veiculos.get(27), "ME", 281_505.0),
                  Arguments.of(VeiculoFactory.veiculos.get(28), "ONG", 216_300.0),
                  Arguments.of(VeiculoFactory.veiculos.get(29), "SA", 805_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(30), "COOPERATIVA", 1_306_435.0)
            ));

            veiculosValorPf.addAll(List.of(
                  Arguments.of(VeiculoFactory.veiculos.get(0), 2_590_800.0),
                  Arguments.of(VeiculoFactory.veiculos.get(1), 2_308_200.0),
                  Arguments.of(VeiculoFactory.veiculos.get(2), 1_176_600.0),
                  Arguments.of(VeiculoFactory.veiculos.get(3), 336_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(4), 179_400.0),
                  Arguments.of(VeiculoFactory.veiculos.get(5), 398_400.0),
                  Arguments.of(VeiculoFactory.veiculos.get(6), 292_200.0),
                  Arguments.of(VeiculoFactory.veiculos.get(7), 308_640.0),
                  Arguments.of(VeiculoFactory.veiculos.get(8), 161_760.0),
                  Arguments.of(VeiculoFactory.veiculos.get(9), 213_240.0),
                  Arguments.of(VeiculoFactory.veiculos.get(10), 468_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(11), 600_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(12), 2_307_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(13), 3_270_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(14), 1_012_200.0),
                  Arguments.of(VeiculoFactory.veiculos.get(15), 216_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(16), 270_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(17), 452_400.0),
                  Arguments.of(VeiculoFactory.veiculos.get(18), 405_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(19), 328_680.0),
                  Arguments.of(VeiculoFactory.veiculos.get(20), 141_240.0),
                  Arguments.of(VeiculoFactory.veiculos.get(21), 158_280.0),
                  Arguments.of(VeiculoFactory.veiculos.get(22), 534_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(23), 704_400.0),
                  Arguments.of(VeiculoFactory.veiculos.get(24), 4_620_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(25), 3_775_200.0),
                  Arguments.of(VeiculoFactory.veiculos.get(26), 1_116_600.0),
                  Arguments.of(VeiculoFactory.veiculos.get(27), 321_720.0),
                  Arguments.of(VeiculoFactory.veiculos.get(28), 259_560.0),
                  Arguments.of(VeiculoFactory.veiculos.get(29), 840_000.0),
                  Arguments.of(VeiculoFactory.veiculos.get(30), 1_552_200.0)
            ));
        }
    }


}
