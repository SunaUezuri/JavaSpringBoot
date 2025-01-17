package br.com.alura.screenmatch.model;

import br.com.alura.screenmatch.service.traducao.ConsultaMyMemory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

//Anotação que indica que a classe será uma entidade no banco
@Entity
//Anotação que muda o nome da tabela no banco
@Table(name = "t_series")
public class Serie {

    //Chave primária da classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Atributos da classe série
    @Column(unique = true)
    private String titulo;

    private String poster;

    @Column(name = "temporadas")
    private Integer totalTemporadas;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private String atores;

    private String sinopse;

    private Double avaliacao;

    @Transient
    private List<Episodio> episodios = new ArrayList<>();

    //toString para uma melhor exibição no terminal
    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\nPoster: " + poster +
                "\nGênero: " + genero +
                "\nTotal de temporadas: " + totalTemporadas +
                "\nAtores: " + atores +
                "\nSinopse: " + sinopse +
                "\nAvaliação: " + avaliacao + "\n";
    }

    //Construtor da classe
    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.poster = dadosSerie.poster();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.genero = Genero.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.sinopse = ConsultaMyMemory.obterTraducao(dadosSerie.sinopse().trim());
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao()))
                .orElse(0);

    }

    //Getters e Setters da classe
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }
}
