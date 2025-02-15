package br.com.alura.screenmatch.dto;

import br.com.alura.screenmatch.model.Genero;

public record SerieDto(Long id,
                       String titulo,
                       String poster,
                       Integer totalTemporadas,
                       Genero genero,
                       String atores,
                       String sinopse,
                       Double avaliacao){
}
