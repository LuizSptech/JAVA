package school.sptech.factory;

import school.sptech.EstatisticaTema;
import school.sptech.util.ObjectFieldBuilder;

public class EstatisticaTemaFactory {

    public static Object build(String tema, Integer qtdPublicacoesTema, Integer qtdPublicacoesRecebidas) throws ReflectiveOperationException {
        return new ObjectFieldBuilder<>(EstatisticaTema.class)
            .withEnum("tema", tema)
            .with("qtdPublicacoesTema", qtdPublicacoesTema)
            .with("qtdPublicacoesRecebidas", qtdPublicacoesRecebidas)
            .build();
    }
}
