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
            HttpResponse<String> response = null;
            try {
                response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }

        String json = response.body();
            return json;
    }

}
