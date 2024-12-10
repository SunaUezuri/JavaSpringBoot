package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    //Scanner para ler as informações
    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    //Criando constantes para representar as informações da API
    private static final String ENDERECO = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=961ef0ac";
    private static final String SEASON = "&season=";

    //Método para exibir o menu
    public void exibirMenu(){
        System.out.println("Digite o nome da série para a busca: ");

        var nomeSerie = leitura.nextLine();

        //Requisitando a API
        var json = consumoApi.obterDados(
                ENDERECO
                        + nomeSerie.replace(" ", "+")
                        + API_KEY);

        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i <= dados.totalTemporadas(); i++){
			json = consumoApi.obterDados(ENDERECO +
                    nomeSerie.replace(" ", "+") +
                    SEASON + i +
                    API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);

//        for(int i = 0; i < dados.totalTemporadas(); i++){
//            List<DadosEpisodio> episodios = temporadas.get(i).episodios();
//
//            for(int j = 0; j < episodios.size(); j++){
//                System.out.println(episodios.get(j).titulo());
//            }
//        }

        // "->" = LAMBDA, são funções que são utilizados para melhorar a visualização do código, semelhante às Arrow Functions
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episódios");

        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

    }
}
