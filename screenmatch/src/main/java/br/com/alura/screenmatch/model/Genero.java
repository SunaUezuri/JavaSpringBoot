package br.com.alura.screenmatch.model;

public enum Genero {

    ACAO("Action", "Ação"),
    COMEDIA("Comedy", "Comédia"),
    ROMANCE("Romance", "Romance"),
    CRIME("Crime", "Crime"),
    DRAMA("Drama", "Drama"),
    ANIMACAO("Animation", "Animação");


    private String generoOmdb;

    private String categoriaPotugues;

    //Construtor para o Gênero baseado nas categorias do OMDB
    Genero(String categoriaOmdb, String categoriaPotugues){
        this.generoOmdb = categoriaOmdb;
        this.categoriaPotugues = categoriaPotugues;
    }

    //Método para converter os dados da API da OMDB para os dados presentes nesta classe
    public static Genero fromString(String texto){
        for (Genero genero : Genero.values()) {
            if (genero.generoOmdb.equalsIgnoreCase(texto)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para tratar.");
    }

    public static Genero fromPortugues(String texto){
        for (Genero genero : Genero.values()) {
            if (genero.categoriaPotugues.equalsIgnoreCase(texto)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para tratar.");
    }
}
