package br.com.alura.screenmatch.main;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    //Scanner para ler as informações
    private Scanner leitura = new Scanner(System.in);

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    //Criando constantes para representar as informações da API
    private static final String ENDERECO = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=" + System.getenv("OMDB_API");

    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository repositorio;
    private List<Serie> series = new ArrayList<>();
    private Optional<Serie> serieBusca;

    public Main(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    //Método para exibir o menu
    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {

            var menu = """
                    1 - Buscar séries
                    2 - Buscar episódios
                    3 - Listar séries buscadas
                    4 - Buscar série por título
                    5 - Buscar séries por ator
                    6 - Melhores séries
                    7 - Buscar séries por gênero
                    8 - Buscar séries pela quantidade de temporadas
                    9 - Buscar um episódio por trecho
                    10 - Buscar top 5 episódios
                    11 - Buscar episódios a partir de uma data
                                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;

                case 3:
                    listarSeriesBuscadas();
                    break;

                case 4:
                    buscarSeriePorTitulo();
                    break;

                case 5:
                    buscarSeriesPorAtor();
                    break;

                case 6:
                    buscarTop5Series();
                    break;

                case 7:
                    buscarSeriesPorCategoria();
                    break;

                case 8:
                    buscarSeriesQuantidadeDeTemporadas();
                    break;

                case 9:
                    buscarEpisodioPorTrecho();
                    break;

                case 10:
                    buscarTop5EpisodiosPorSerie();
                    break;

                case 11:
                    buscarEpisodiosAposUmaData();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    //Método para exibir os dados da série
    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    //Método que faz a busca na API pela série a partir do nome
    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        return conversor.obterDados(json, DadosSerie.class);
    }

    //Método que permite ver os episódios de uma série a partir do nome da mesma
    private void buscarEpisodioPorSerie() {
        listarSeriesBuscadas();
        System.out.println("Digite o nome da série desejada: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serie.isPresent()){
            var serieEncontrada = serie.get();

            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumoApi.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);

            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada.");
        }


    }

    //Método para exibir todas as séries buscadas pelo usuário anteriormente
    private void listarSeriesBuscadas(){
        series = repositorio.findAll();
        List<Serie> series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()){
            System.out.println("Dados da série: \n" + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void buscarSeriesPorAtor() {
        System.out.println("Insira o nome do ator");
        var nomeAutor = leitura.nextLine();
        System.out.println("Avaliações a partir de que valor?");
        var avaliacao = leitura.nextDouble();
        List<Serie> seriesEncontradas = repositorio
                .findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAutor, avaliacao);
        System.out.println("Séries em que " + nomeAutor + " atua: ");
        seriesEncontradas.forEach(s ->
                System.out.println(s.getTitulo() + " avaliação: " + s.getAvaliacao()));
    }

    private void buscarTop5Series() {
        List<Serie> series = repositorio.findTop5ByOrderByAvaliacaoDesc();
        series.forEach(s ->
                System.out.println("Série: " + s.getTitulo() + " Avaliação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Digite o gênero que deseja pesquisar: ");
        var nomeGenero = leitura.nextLine();
        Genero genero = Genero.fromPortugues(nomeGenero);
        List<Serie> series = repositorio.findByGenero(genero);
        System.out.println("Séries da categoria: " + nomeGenero);
        series.forEach(System.out::println);
    }

    private void buscarSeriesQuantidadeDeTemporadas() {
        System.out.println("Insira a quantidade de temporadas: ");
        var temporadas = leitura.nextInt();
        System.out.println("Insira a avaliação mínima: ");
        var avaliacao = leitura.nextDouble();

        System.out.println("Séries filtradas: ");

        List<Serie> series = repositorio
                .seriesPorTemporadaEAvaliacao(temporadas, avaliacao);
        series.forEach(System.out::println);
    }

    private void buscarEpisodioPorTrecho(){
        System.out.println("Insira um trecho do título do episódio");
        var trecho = leitura.nextLine();

        List<Episodio> episodiosEncontrados = repositorio.episodiosPorTrecho(trecho);
        episodiosEncontrados.forEach(e ->
                System.out.printf("Série: %s Temporada %s - Episódio: %s - %s\n",
                        e.getSerie().getTitulo(), e.getTemporada(),
                        e.getNumeroEpisodio(), e.getTitulo()));
    }

    private void buscarTop5EpisodiosPorSerie(){
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = repositorio.topEpisodiosPorSerie(serie);
            topEpisodios.forEach(e ->
                    System.out.printf("Título: %s, Temporada %s - Episódio: %s - Avaliação: %s \n",
                            e.getTitulo(), e.getTemporada(),
                            e.getNumeroEpisodio(), e.getAvaliacao()));
        }
    }

    private void buscarEpisodiosAposUmaData(){
        buscarSeriePorTitulo();
        if (serieBusca.isPresent()){
            System.out.println("Digite o ano limite de lançamento:");
            var anoLancamento = leitura.nextInt();
            Serie serie = serieBusca.get();
            leitura.nextLine();
            List<Episodio> episodiosAno = repositorio.episodiosPorSerieEAno(anoLancamento, serie);
            episodiosAno.forEach(System.out::println);
        }
    }

}
