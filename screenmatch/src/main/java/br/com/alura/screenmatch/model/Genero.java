package br.com.alura.screenmatch.model;

public enum Genero {

    ACAO("Action"),
    COMEDIA("Comedy"),
    ROMANCE("Romance"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ANIMACAO("Animation");


    private String generoOmdb;

    //Construtor para o Gênero baseado nas categorias do OMDB
    Genero(String categoriaOmdb){
        this.generoOmdb = categoriaOmdb;
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
}
