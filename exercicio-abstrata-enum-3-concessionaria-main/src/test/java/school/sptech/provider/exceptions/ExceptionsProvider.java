package school.sptech.provider.exceptions;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.exceptions.DocumentoInvalidoException;
import school.sptech.exceptions.PagamentoInvalidoException;
import school.sptech.exceptions.RequisicaoDeFinanciamentoInvalidaException;
import school.sptech.exceptions.ValorInexistenteException;

public class ExceptionsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context)
          throws Exception {
        return Stream.of(
              Arguments.of(PagamentoInvalidoException.class, RuntimeException.class,
                    "PagamentoInvalidoException deve ser unchecked"),
              Arguments.of(RequisicaoDeFinanciamentoInvalidaException.class,
                    RuntimeException.class,
                    "RequisicaoDeFinanciamentoInvalidaException deve ser unchecked"),
              Arguments.of(DocumentoInvalidoException.class, RuntimeException.class,
                    "DocumentoInvalidoException deve ser unchecked"),
              Arguments.of(ValorInexistenteException.class, RuntimeException.class,
                    "ValorInexistenteException deve ser unchecked")
        );
    }
}
