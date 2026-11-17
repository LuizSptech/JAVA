package school.sptech.provider.jornal;

import java.util.List;
import school.sptech.factory.EstatisticaJornalistaFactory;
import school.sptech.factory.EstatisticaTemaFactory;
import school.sptech.factory.LeitorFactory;
import school.sptech.factory.NoticiaFactory;

/**
 * Massa de dados compartilhada pelos providers de Jornal. Cada provider deve criar uma nova
 * instância, para que os cenários não compartilhem objetos alterados por outros testes.
 */
public class ObservadoresFixture {

    public final Object n1, n2, n3, n4, n5;
    public final Object l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
    public final Object ej1, ej2, ej3, ej4, ej5;
    public final Object et1, et2, et3, et4;

    public ObservadoresFixture() throws ReflectiveOperationException {
        n1 = NoticiaFactory.build("Titulo 1", "TECNOLOGIA", "Vagner Moura", 10);
        n2 = NoticiaFactory.build("Titulo 2", "ESPORTE", "Neymar Jr", 5);
        n3 = NoticiaFactory.build("Titulo 3", "POLITICA", "Paulo Souza", 8);
        n4 = NoticiaFactory.build("Titulo 4", "TECNOLOGIA", "Ada Lovelace", 12);
        n5 = NoticiaFactory.build("Titulo 5", "ESPORTE", "Marta Silva", 7);

        // (nome, email, publicacoesRecebidas, publicacoesFavoritas, temaFavorito)
        l1 = LeitorFactory.build("Ana", "ana@email.com", List.of(), List.of(), "TECNOLOGIA");
        l2 = LeitorFactory.build("Bruno", "bruno@email.com", List.of(), List.of(), "ESPORTE");
        l3 = LeitorFactory.build("Carla", "carla@email.com", List.of(n5), List.of(), "POLITICA");
        l4 = LeitorFactory.build("Daniel", "daniel@email.com", List.of(n1, n3), List.of(n1), "TECNOLOGIA");
        l5 = LeitorFactory.build("Eva", "eva@email.com", List.of(n2, n4), List.of(n2), "ESPORTE");
        l6 = LeitorFactory.build("Fábio", "fabio@email.com", List.of(n3), List.of(), "ENTRETENIMENTO");
        l7 = LeitorFactory.build("Gisele", "gisele@email.com", List.of(n1, n5), List.of(n5), "ESPORTE");
        l8 = LeitorFactory.build("Hugo", "hugo@email.com", List.of(n4), List.of(n4), "TECNOLOGIA");
        l9 = LeitorFactory.build("Isabela", "isabela@email.com", List.of(n2, n3), List.of(n3), "POLITICA");
        l10 = LeitorFactory.build("João", "joao@email.com", List.of(n1, n2, n4), List.of(n1, n4), "TECNOLOGIA");

        // (qtdPublicacoesRecebidas, jornalista, qtdPublicacoesJornalista)
        ej1 = EstatisticaJornalistaFactory.build(0, "Ana", 0);      //  0,00%
        ej2 = EstatisticaJornalistaFactory.build(6, "Bruno", 5);    // 83,33%
        ej3 = EstatisticaJornalistaFactory.build(10, "Carla", 4);   // 40,00%
        ej4 = EstatisticaJornalistaFactory.build(3, "Daniel", 1);   // 33,33%
        ej5 = EstatisticaJornalistaFactory.build(8, "Eva", 3);      // 37,50%

        // (tema, qtdPublicacoesTema, qtdPublicacoesRecebidas)
        et1 = EstatisticaTemaFactory.build("POLITICA", 0, 0);       //  0,00%
        et2 = EstatisticaTemaFactory.build("TECNOLOGIA", 2, 5);     // 40,00%
        et3 = EstatisticaTemaFactory.build("ENTRETENIMENTO", 4, 10); // 40,00%
        et4 = EstatisticaTemaFactory.build("ESPORTE", 1, 3);        // 33,33%
    }
}
