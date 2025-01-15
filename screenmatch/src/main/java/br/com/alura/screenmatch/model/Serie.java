package br.com.alura.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {

    //Atributos da classe série
    private String titulo;
    private String poster;
    private Integer totalTemporadas;
    private Genero genero;
    private String atores;
    private String sinopse;
    private Double avaliacao;

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
        this.sinopse = dadosSerie.sinopse();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao()))
                .orElse(0);

    }

    //Getters e Setters da classe
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
}
