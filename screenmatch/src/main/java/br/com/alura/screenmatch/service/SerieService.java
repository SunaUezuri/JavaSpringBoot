package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    private List<SerieDto> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getPoster(),
                        s.getTotalTemporadas(), s.getGenero(), s.getAtores(),
                        s.getSinopse(), s.getAvaliacao()))
                .collect(Collectors.toList());
    }
}
