package school.sptech.factory;

import static net.bytebuddy.matcher.ElementMatchers.named;

import java.util.ArrayList;
import java.util.List;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.objenesis.ObjenesisStd;
import school.sptech.Observador;

/**
 * Implementação falsa de {@link Observador}, criada via ByteBuddy, que apenas registra as
 * notícias recebidas em {@link ReceberPublicacaoFake#getRecebidas()}.
 */
public class ObservadorFake {

    public static class ReceberPublicacaoFake {

        private final List<Object> recebidas = new ArrayList<>();

        @RuntimeType
        public void receberPublicacao(@Argument(0) Object noticia) {
            recebidas.add(noticia);
        }

        public List<Object> getRecebidas() {
            return recebidas;
        }
    }

    public static Object build(ReceberPublicacaoFake delegacao) {
        Class<?> tipo = new ByteBuddy()
              .subclass(Object.class)
              .implement(Observador.class)
              .method(named("receberPublicacao"))
              .intercept(MethodDelegation.to(delegacao))
              .make()
              .load(Observador.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
              .getLoaded();

        return new ObjenesisStd().newInstance(tipo);
    }
}
