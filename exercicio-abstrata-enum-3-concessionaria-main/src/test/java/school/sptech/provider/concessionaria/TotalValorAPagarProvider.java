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

public class TotalValorAPagarProvider implements ArgumentsProvider {

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
        Object pj1 = EmpresaFactory.build("Padaria da Maria", "12345678000100", "LTDA");
        Object pj2 = EmpresaFactory.build("Construtora Silva", "99887766000155", "ME");
        Object pj3 = EmpresaFactory.build("Transportes Costa Ltda", "11223344000188", "COOPERATIVA");
        Object pj4 = EmpresaFactory.build("Oficina João Turbo", "44332211000199", "MEI");
        Object pj5 = EmpresaFactory.build("João & João LTDA", "1231231230001", "ONG");


        // Financiamentos PF
        Object f1 = FinanciamentoPFFactory.build(10, 1, veiculos.get(0), pf1);
        Object f2 = FinanciamentoPFFactory.build(1, 1, veiculos.get(1), pf2);
        Object f3 = FinanciamentoPFFactory.build(100, 30, veiculos.get(2), pf3);
        Object f4 = FinanciamentoPFFactory.build(280, 133, veiculos.get(3), pf4);
        Object f5 = FinanciamentoPFFactory.build(10, 7, veiculos.get(4), pf5);
        Object f6 = FinanciamentoPFFactory.build(332, 178, veiculos.get(5), pf6);

        // Financiamentos Pj
        Object f7 = FinanciamentoPJFactory.build(5, 1, veiculos.get(6), pj1);
        Object f8 = FinanciamentoPJFactory.build(10, 5, veiculos.get(7), pj2);
        Object f9 = FinanciamentoPJFactory.build(2, 1, veiculos.get(8), pj3);
        Object f10 = FinanciamentoPJFactory.build(100, 100, veiculos.get(9), pj4);
        Object f11 = FinanciamentoPJFactory.build(390, 389, veiculos.get(10), pj5);

        return Stream.of(
              Arguments.of("Auto Belvedere", List.of(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11), 3_988_744.0),
              Arguments.of("JetCar Veículos", List.of(f1, f2, f7), 2_546_000.0),
              Arguments.of("Top Premium", List.of(f4, f6, f8), 496_230.0),
              Arguments.of("DreamGarage", List.of(f1, f2, f3, f4, f5), 3_385_560.0),
              Arguments.of("Alta Rota", List.of(f7, f8, f9, f10), 417_384.0),
              Arguments.of("Estrela Motors", List.of(f3, f4, f5, f6), 1_238_640.0),
              Arguments.of("Lux Auto", List.of(f3, f4, f5, f6), 1_238_640.0),
              Arguments.of("KM1000", List.of(f9), 68_074.0),
              Arguments.of("Planet Motors", List.of(f10), 0.0),
              Arguments.of("Brasil Motors", List.of(f4, f6, f10), 361_200.0),
              Arguments.of("UniCar", List.of(f1, f2, f3, f4, f5, f6), 3_570_360.0),
              Arguments.of("AutoNorte", List.of(f1, f2, f3, f4), 3_331_740.0),
              Arguments.of("ZeroKM Express", List.of(f1, f2, f3, f4, f5), 3_385_560.0),
              Arguments.of("Expressa Veículos", List.of(f1, f2, f3), 3_155_340.0),
              Arguments.of("Vazio Motors", List.of(), 0.0)
        );

    }
}
