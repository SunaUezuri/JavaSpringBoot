package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Falando para a aplicação que esta classe é um controller
@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servico;

    //Anotação para o método GET
    @GetMapping
    public List<SerieDto> obterSeries(){
        return servico.obterTodasAsSeries();
    }

    @GetMapping("/top5")
    public List<SerieDto> obterTop5Series() {
        return servico.obterTop5Series();
    }
}
