package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Genero;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAutor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Genero genero);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(Integer temporadas, Double avaliacao);

    @Query("select s from Serie s where s.totalTemporadas <= :totalTemporadas and s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAvaliacao(Integer totalTemporadas, Double avaliacao);

    @Query("select e from Serie s join s.episodios e where e.titulo ilike %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("select e from Serie s join s.episodios e where s = :serie order by e.avaliacao desc limit 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("select e from Serie s join s.episodios e where s = :serie and year(e.dataLancamento) >= :anoLancamento ")
    List<Episodio> episodiosPorSerieEAno(int anoLancamento, Serie serie);

    @Query("SELECT s FROM Serie s " +
            "JOIN s.episodios e " +
            "GROUP BY s " +
            "ORDER BY MAX(e.dataLancamento) DESC LIMIT 5")
    List<Serie> encontrarEpisodiosMaisRecentes();

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Integer numero);
}
