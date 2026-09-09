package school.sptech.ex04.provider;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

/**
 * comprarIngresso aplica aplicarDesconto(idade, estudante) e aplicarDescontoHorario() e compara
 * o pagamento com o preco ja com os dois descontos. Sucesso apenas se o assento estiver livre
 * (assentosDisponiveis[indice] == true) E pagamento >= preco. Em caso de sucesso o assento passa
 * a ocupado (false) e o metodo retorna true; o atributo valor so e atualizado quando a compra da
 * certo. Em caso de falha, retorna false e o valor permanece o original.
 *
 * Observacao: nos cenarios de sucesso com desconto o pagamento e deixado com folga acima do preco
 * para nao depender de arredondamento de ponto flutuante na comparacao feita pela implementacao.
 */
public class ComprarIngressoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        // valorIni, hora, assentos, indice, pagamento, idade, estudante,
        //   retornoEsp, valorEsp, assentoNoIndiceEsp
        return Stream.of(
              // sucesso sem desconto, pagamento exatamente igual ao preco
              Arguments.of(20.0, 20, arr(true, true, true), 0, 20.0, 30, false, true, 20.0, false),
              // sucesso so com desconto de horario (preco ~18.0)
              Arguments.of(20.0, 10, arr(true, true, true), 1, 18.5, 30, false, true, 18.0, false),
              // sucesso com desconto de idade (<12) + horario (preco ~9.0)
              Arguments.of(20.0, 10, arr(true, true, true), 2, 10.0, 10, false, true, 9.0, false),
              // falha: pagamento insuficiente (preco 20.0) -> valor volta ao original
              Arguments.of(20.0, 20, arr(true, true, true), 0, 5.0, 30, false, false, 20.0, true),
              // falha: assento ja ocupado -> continua ocupado
              Arguments.of(20.0, 20, arr(false, true, true), 0, 100.0, 30, false, false, 20.0, false),
              // sucesso com desconto de estudante 16..20 (preco ~14.0)
              Arguments.of(20.0, 20, arr(true, true, true), 1, 15.0, 18, true, true, 14.0, false),
              // falha: pagamento insuficiente mesmo com desconto (preco ~9.0, paga 8.0)
              Arguments.of(20.0, 10, arr(true, true, true), 2, 8.0, 10, false, false, 20.0, true),
              // sucesso com desconto de estudante >20 (20%) + horario (preco ~14.4)
              Arguments.of(20.0, 15, arr(true, true, true), 0, 100.0, 40, true, true, 14.4, false),
              // falha: assento do meio ocupado
              Arguments.of(20.0, 20, arr(true, false, true), 1, 20.0, 30, false, false, 20.0, false)
        );
    }

    private static Boolean[] arr(boolean... valores) {
        Boolean[] resultado = new Boolean[valores.length];
        for (int i = 0; i < valores.length; i++) {
            resultado[i] = valores[i];
        }
        return resultado;
    }
}
