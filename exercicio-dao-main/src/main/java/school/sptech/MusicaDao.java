package school.sptech;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MusicaDao {

    private final JdbcTemplate jdbcTemplate;

    public MusicaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* Escreva os métodos abaixo */

        public List<Musica> findAll(){
                List<Musica> musica = jdbcTemplate.query(
                        "SELECT * FROM musica;",
                        new BeanPropertyRowMapper<>(Musica.class));
                        return musica;
        }

        public Musica findById(Integer id){


               List<Musica> musica = jdbcTemplate.query(
                       "SELECT * from musica where id = ?",
                       new BeanPropertyRowMapper<>(Musica.class),id);



        return musica.isEmpty() ? null : musica.get(0);
        }


        public List<Musica> findByNomeLike(String nome){
            String parametro = "%" + nome + "%";
            String sql = "select * from musica where lower(nome) like lower(?)";
            List<Musica> musicas = jdbcTemplate.query(
                    sql, new BeanPropertyRowMapper<>(Musica.class), parametro
            );

            return musicas;

        }


        public List<Musica> findByArtista(String artista){
            List<Musica> musicas = jdbcTemplate.query(
                    "SELECT * FROM musica WHERE artista = ? ",
                    new BeanPropertyRowMapper<>(Musica.class),artista);
            return musicas;
        }


        public List<Musica> findByAlbum(String album){
            List<Musica> musicas = jdbcTemplate.query(
                    "SELECT * FROM musica WHERE album = ?",
                    new BeanPropertyRowMapper<>(Musica.class),album);
            return musicas;
        }


        public List<Musica> findByDuracaoGreaterThan(Integer duracao){
            List<Musica> musicas = jdbcTemplate.query(
                    "SELECT * FROM musica WHERE duracao > ?",
                    new BeanPropertyRowMapper<>(Musica.class),duracao
            );
            return musicas;
        }

        public List<Musica> findByAlbumAndNomeLike(String album, String nome){
            List<Musica> musicas = jdbcTemplate.query(
                    "SELECT * FROM musica where album = ? and nome ILIKE  CONCAT('%', ?, '%')",
                    new BeanPropertyRowMapper<>(Musica.class),album, nome
            );
            return musicas;
        }

    public void save(Musica musica) {

        if(musica.getId() == null) {
            jdbcTemplate.update(
                    "INSERT INTO musica (nome,artista,album,duracao) VALUES (?,?,?,?)"
                    , musica.getNome(),musica.getArtista(),musica.getAlbum(),musica.getDuracao());

        }
        jdbcTemplate.update(
                "UPDATE musica SET nome = ?,artista = ?,album = ?,duracao = ? WHERE id = ?"
                , musica.getNome(),musica.getArtista(),musica.getAlbum(),musica.getDuracao(),musica.getId());


    }

    public void deleteById(Integer id) {

        if(id == null || id<0) {
            return;
        }

        jdbcTemplate.update(
                "DELETE FROM musica WHERE id = ?"
                , id);
    }


}
