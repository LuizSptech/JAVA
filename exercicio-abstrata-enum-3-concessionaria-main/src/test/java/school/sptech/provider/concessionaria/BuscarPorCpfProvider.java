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

public class BuscarPorCpfProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        Object p1 = PessoaFactory.build("João Guilherme", "11111111111");
        Object p2 = PessoaFactory.build("Maria Silva", "22222222222");
        Object p3 = PessoaFactory.build("Carlos Pereira", "33333333333");
        Object p4 = PessoaFactory.build("Ana Costa", "44444444444");

        Object fp1 = FinanciamentoPFFactory.build(1, 1, veiculos.get(0), p1);
        Object fp2 = FinanciamentoPFFactory.build(1, 1, veiculos.get(1), p1);
        Object fp3 = FinanciamentoPFFactory.build(1, 1, veiculos.get(2), p2);
        Object fp4 = FinanciamentoPFFactory.build(1, 1, veiculos.get(3), p2);
        Object fp5 = FinanciamentoPFFactory.build(1, 1, veiculos.get(4), p2);
        Object fp6 = FinanciamentoPFFactory.build(1, 1, veiculos.get(5), p3);
        Object fp7 = FinanciamentoPFFactory.build(1, 1, veiculos.get(6), p3);
        Object fp8 = FinanciamentoPFFactory.build(1, 1, veiculos.get(7), p3);
        Object fp9 = FinanciamentoPFFactory.build(1, 1, veiculos.get(8), p4);
        Object fp10 = FinanciamentoPFFactory.build(1, 1, veiculos.get(9), p1);

        // Empresas
        Object e1 = EmpresaFactory.build("Padaria da Marcia", "12345678000199", "MEI");
        Object e2 = EmpresaFactory.build("Construtora ABC", "99887766000155", "LTDA");

        Object fe1 = FinanciamentoPJFactory.build(1, 1, veiculos.get(10), e1);
        Object fe2 = FinanciamentoPJFactory.build(1, 1, veiculos.get(11), e1);
        Object fe3 = FinanciamentoPJFactory.build(1, 1, veiculos.get(12), e2);

        return Stream.of(
              Arguments.of("AutoLux Motors", List.of(fp1, fp2, fp10, fp3, fp4, fp5, fe1, fe2),
                    "11111111111",
                    List.of(fp1, fp2, fp10)),
              Arguments.of("Rota Veloz Veículos", List.of(fp6, fp7, fp8, fp9, fe3), "44444444444",
                    List.of(fp9)),
              Arguments.of("Garage Premium", List.of(fp1, fp2, fp3, fp4, fe1), "55555555555",
                    List.of()),
              Arguments.of("DreamCar Multimarcas", List.of(fp3, fp4, fp5, fp6, fe2), "33333333333",
                    List.of(fp6)),
              Arguments.of("Conecta Motors", List.of(fp1, fp3, fp6, fp10, fe3), "11111111111",
                    List.of(fp1, fp10)),
              Arguments.of("Estrela do Asfalto", List.of(fp3, fp4, fp5, fe1, fe2), "22222222222",
                    List.of(fp3, fp4, fp5)),
              Arguments.of("ZeroKM Veículos", List.of(fe1, fe2, fe3), "11111111111", List.of())
        );
    }

}
