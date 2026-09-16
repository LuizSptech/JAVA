package school.sptech.ex01.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex01.Produto;

import java.util.List;
import java.util.stream.Stream;
import school.sptech.ex01.util.ProdutoMock;

public class ProdutoQuantidadePorCategoriaProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
    Produto p1 = ProdutoMock.novo("Smartphone XYZ", "Eletrônicos", 1500.00);
    Produto p2 = ProdutoMock.novo("Tênis de Corrida Speed", "Vestuário", 299.99);
    Produto p3 = ProdutoMock.novo("Cafeteira Expresso Plus", "Eletrodomésticos", 489.90);
    Produto p4 = ProdutoMock.novo("Fone de Ouvido Bluetooth", "Acessórios", 259.90);
    Produto p5 = ProdutoMock.novo("Notebook Gamer Alpha", "Informática", 5800.00);
    Produto p6 = ProdutoMock.novo("Mochila Montanha 60L", "Esporte e Lazer", 350.00);
    Produto p7 = ProdutoMock.novo("Perfume Elegance 50ml", "Beleza e Perfumaria", 210.00);
    Produto p8 = ProdutoMock.novo("Relógio SmartTech", "Acessórios", 1200.00);
    Produto p9 = ProdutoMock.novo("Cadeira Gamer Confort", "Móveis", 899.99);
    Produto p10 = ProdutoMock.novo("Livro - O Mundo de Sofia", "Livros", 59.90);
    Produto p11 = ProdutoMock.novo("Tablet SuperView 8", "Eletrônicos", 1200.00);
    Produto p12 = ProdutoMock.novo("Jaqueta Corta Vento", "Vestuário", 199.99);
    Produto p13 = ProdutoMock.novo("Liquidificador PowerMix", "Eletrodomésticos", 289.90);
    Produto p14 = ProdutoMock.novo("Caixa de Som Portátil", "Acessórios", 349.90);
    Produto p15 = ProdutoMock.novo("PC Desktop Office Pro", "Informática", 3200.00);
    Produto p16 = ProdutoMock.novo("Barraca Iglu 4 Pessoas", "Esporte e Lazer", 450.00);
    Produto p17 = ProdutoMock.novo("Batom Matte Luxo", "Beleza e Perfumaria", 45.00);
    Produto p18 = ProdutoMock.novo("Pulseira de Atividade", "Acessórios", 599.99);
    Produto p19 = ProdutoMock.novo("Escrivaninha Studio", "Móveis", 759.99);
    Produto p20 = ProdutoMock.novo("Livro - A Arte da Guerra", "Livros", 29.90);
    Produto p21 = ProdutoMock.novo("Smartwatch HealthPro", "Eletrônicos", 899.00);
    Produto p22 = ProdutoMock.novo("Caixa de Som WiFi", "Eletrônicos", 479.00);
    Produto p23 = ProdutoMock.novo("Drone Explorer Vision", "Eletrônicos", 1500.00);
    Produto p24 = ProdutoMock.novo("Sofá Retrátil Luxo", "Móveis", 2500.00);
    Produto p25 = ProdutoMock.novo("Estante de Livros", "Móveis", 899.99);

    List<Produto> amostra1 = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);
    List<Produto> amostra2 = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
    List<Produto> amostra3 = List.of(p1, p2, p3, p4, p5, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);
    List<Produto> amostra4 = List.of(p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25);

    return Stream.of(
        Arguments.of(amostra1, "Eletrônicos", 2),
        Arguments.of(amostra2, "Eletrônicos", 1),
        Arguments.of(amostra1, "Acessórios", 4),
        Arguments.of(amostra2, "Acessórios", 2),
        Arguments.of(amostra3, "Fotografia", 0),
        Arguments.of(amostra3, "Vestuário", 2),
        Arguments.of(amostra4, "Eletrônicos", 4),
        Arguments.of(amostra4, "Móveis", 3)
    );
  }
}
