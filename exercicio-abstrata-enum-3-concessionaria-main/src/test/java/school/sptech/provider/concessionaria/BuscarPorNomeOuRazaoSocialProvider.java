package school.sptech.provider.concessionaria;

import static school.sptech.factory.VeiculoFactory.veiculos;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.EmpresaFactory;
import school.sptech.factory.FinanciamentoPFFactory;
import school.sptech.factory.FinanciamentoPJFactory;
import school.sptech.factory.PessoaFactory;

public class BuscarPorNomeOuRazaoSocialProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        // Pessoas físicas
        Object pf1 = PessoaFactory.build("João Guilherme", "11111111111");
        Object pf2 = PessoaFactory.build("Maria Silva", "22222222222");
        Object pf3 = PessoaFactory.build("Carlos Pereira", "33333333333");
        Object pf4 = PessoaFactory.build("Ana Beatriz Costa", "44444444444");
        Object pf5 = PessoaFactory.build("José dos Anzóis", "55555555555");
        Object pf6 = PessoaFactory.build("BEATRIZ MARQUES", "66666666666");

        // Empresas
        Object pj1 = EmpresaFactory.build("Padaria da Maria", "12345678000100", "MEI");
        Object pj2 = EmpresaFactory.build("Construtora Silva", "99887766000155", "LTDA");
        Object pj3 = EmpresaFactory.build("Transportes Costa Ltda", "11223344000188", "LTDA");
        Object pj4 = EmpresaFactory.build("Oficina João Turbo", "44332211000199", "ME");
        Object pj5 = EmpresaFactory.build("João & João LTDA", "1231231230001", "LTDA");

        // Financiamentos PF
        Object f1 = FinanciamentoPFFactory.build(1, 1, veiculos.get(0), pf1);
        Object f2 = FinanciamentoPFFactory.build(1, 1, veiculos.get(1), pf2);
        Object f3 = FinanciamentoPFFactory.build(1, 1, veiculos.get(2), pf3);
        Object f4 = FinanciamentoPFFactory.build(1, 1, veiculos.get(3), pf4);
        Object f5 = FinanciamentoPFFactory.build(1, 1, veiculos.get(4), pf5);
        Object f6 = FinanciamentoPFFactory.build(1, 1, veiculos.get(5), pf6);

        // Financiamentos PJ
        Object f7 = FinanciamentoPJFactory.build(1, 1, veiculos.get(6), pj1);
        Object f8 = FinanciamentoPJFactory.build(1, 1, veiculos.get(7), pj2);
        Object f9 = FinanciamentoPJFactory.build(1, 1, veiculos.get(8), pj3);
        Object f10 = FinanciamentoPJFactory.build(1, 1, veiculos.get(9), pj4);
        Object f11 = FinanciamentoPJFactory.build(1, 1, veiculos.get(10), pj5);

        return Stream.of(
              Arguments.of("Auto Belvedere", List.of(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11),
                    "João",
                    List.of(f1, f10, f11)),
              Arguments.of("JetCar Veículos", List.of(f1, f2, f7), "MARIA",
                    List.of(f2, f7)),
              Arguments.of("Top Premium", List.of(f4, f6, f8), "beatriz",
                    List.of(f4, f6)),
              Arguments.of("DreamGarage", List.of(f1, f2, f3, f4, f5), "SILVA",
                    List.of(f2)),
              Arguments.of("Alta Rota", List.of(f7, f8, f9, f10), "silva",
                    List.of(f8)),
              Arguments.of("Estrela Motors", List.of(f3, f4, f5, f6), "José",
                    List.of(f5)),
              Arguments.of("Lux Auto", List.of(f3, f4, f5, f6), "anzóis",
                    List.of(f5)),
              Arguments.of("KM1000", List.of(f9), "Costa",
                    List.of(f9)),
              Arguments.of("Planet Motors", List.of(f10), "OFICINA",
                    List.of(f10)),
              Arguments.of("Brasil Motors", List.of(f4, f6, f10), "turbo",
                    List.of(f10)),
              Arguments.of("UniCar", List.of(f1, f2, f3, f4, f5, f6), "carla",
                    List.of()), // não encontra "carla"
              Arguments.of("AutoNorte", List.of(f1, f2, f3, f4), "GUilHeRmE",
                    List.of(f1)),
              Arguments.of("ZeroKM Express", List.of(f1, f2, f3, f4, f5), "an",
                    List.of(f4, f5)), // pega Ana e Anzóis
              Arguments.of("Expressa Veículos", List.of(f1, f2, f3, f6), "maRqueS",
                    List.of(f6)), // caixa mista e sobrenome
              Arguments.of("Vazio Motors", List.of(), "joão", List.of()) // lista vazia
        );
    }


}
