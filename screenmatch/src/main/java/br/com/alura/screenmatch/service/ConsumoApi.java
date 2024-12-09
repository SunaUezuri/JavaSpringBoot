package br.com.alura.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    //Criando um método para fazer uma requisição
    public String obterDados(String endereco){
            //Objeto client para receber o método HTTP
            HttpClient client = HttpClient.newHttpClient();
            //Objeto que faz o request da API a partir de um link
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();
            //Objeto para receber uma lista de Strings
            HttpResponse<String> response = null;
            try {
                /*
                * Criando um response utilizando o client para indicar
                * o tipo de informação que ele deve receber e como
                * ele deve o manejar
                */
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                //Exceções para caso haja algum tipo de erro
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        String json = response.body();
            return json;
    }

}
