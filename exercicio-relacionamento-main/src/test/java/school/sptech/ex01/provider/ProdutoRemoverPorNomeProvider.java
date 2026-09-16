package school.sptech.ex01.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.ex01.Produto;

import java.util.List;
import java.util.stream.Stream;
import school.sptech.ex01.util.ProdutoMock;

public class ProdutoRemoverPorNomeProvider implements ArgumentsProvider {

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

    return Stream.of(
        Arguments.of(List.of(p7, p2, p4), List.of("Perfume Elegance 50ml"), List.of(p2, p4)),
        Arguments.of(List.of(p3, p8, p1, p10), List.of("Teste"), List.of(p3, p8, p1, p10)),
        Arguments.of(List.of(p9, p5), List.of("Cadeira Gamer Confort"), List.of(p5)),
        Arguments.of(List.of(p2, p7, p3, p1, p4, p6), List.of("Smartphone XYZ", "Fone de Ouvido Bluetooth"), List.of(p2, p7, p3, p6)),
        Arguments.of(List.of(p2, p7, p3, p1, p4, p6), List.of("Tênis de Corrida Speed", "Fone de Ouvido Bluetooth"), List.of(p7, p3, p1, p6)),
        Arguments.of(List.of(p8), List.of("Relógio SmartTech"), List.of()),
        Arguments.of(List.of(), List.of("Relógio SmartTech"), List.of())
    );
  }
}
