package br.com.alura.screenmatch.model;

//Usado para demonstrar ao sistema que aquela declaração é um apelido para um dado no JSON
//E ele serve apenas para ler o JSON diferente do Property que também envia
//Mas pode ser usado de maneira da qual é possível colocar um array para buscar várias chaves com um range de nomes definidos
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Fazendo com que o JSON ignore as propriedades que não queremos que sejam mapeadas
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("Poster") String poster,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("Actors") String atores,
                         @JsonAlias("Genre") String genero,
                         @JsonAlias("Plot") String sinopse,
                         @JsonAlias("imdbRating") String avaliacao) {
}
