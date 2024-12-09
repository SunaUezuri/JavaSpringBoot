package br.com.alura.screenmatch.model;

//Usado para demonstrar ao sistema que aquela declaração é um apelido para um dado no JSON
//E ele serve apenas para ler o JSON diferente do Property que também envia
//Mas pode ser usado de maneira da qual é possível colocar um array para buscar várias chaves com um range de nomes definidos
import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {

    
}
