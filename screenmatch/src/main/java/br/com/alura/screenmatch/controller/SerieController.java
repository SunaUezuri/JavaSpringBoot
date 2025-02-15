package br.com.alura.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Falando para a aplicação que esta classe é um controller
@RestController
public class SerieController {

    //Anotação para o método GET
    @GetMapping("/series")
    public String obterSeries(){
        return "Hello World!";
    }

}
