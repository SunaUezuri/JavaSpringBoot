package br.com.alura.screenmatch.service;

public interface IConverteDados {

    /*
    * Este "T" é chamado de Generics, ele serve quando não se sabe
    * o tipo de dado exato que algo do sistema receberá.
    */
    <T> T obterDados(String json, Class<T> classe);
}
