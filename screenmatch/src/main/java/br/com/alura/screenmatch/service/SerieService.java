package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.EpisodioDto;
import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Genero;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDto> obterTodasAsSeries() {
        return converteDados(repositorio.findAll()) ;
    }

    public List<SerieDto> obterTop5Series() {
        return converteDados(repositorio.findTop5ByOrderByAvaliacaoDesc());

    }

    public List<SerieDto> obterLancamento() {
        return converteDados(repositorio.encontrarEpisodiosMaisRecentes());
    }

    public SerieDto obterPorId(Long id) {

        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDto(s.getId(), s.getTitulo(), s.getPoster(),
                    s.getTotalTemporadas(), s.getGenero(), s.getAtores(),
                    s.getSinopse(), s.getAvaliacao());
        } else {
            return null;
        }
    }

    public List<EpisodioDto> obterTodasAsTemporadas(Long id) {

        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return  s.getEpisodios()
                    .stream()
                    .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public List<EpisodioDto> obterTemporadaPorNumero(Long id, Integer numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDto(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<SerieDto> obterSeriesPorGenero(String genero) {
        Genero categoria = Genero.fromPortugues(genero);

        return converteDados(repositorio.findByGenero(categoria));
    }

    //Métodos privados da classe
    private List<SerieDto> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getPoster(),
                        s.getTotalTemporadas(), s.getGenero(), s.getAtores(),
                        s.getSinopse(), s.getAvaliacao()))
                .collect(Collectors.toList());
    }



}
