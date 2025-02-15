package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.dto.SerieDto;
import br.com.alura.screenmatch.model.Serie;
import br.com.alura.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//Falando para a aplicação que esta classe é um controller
@RestController
public class SerieController {

    @Autowired
    private SerieRepository repositorio;

    //Anotação para o método GET
    @GetMapping("/series")
    public List<SerieDto> obterSeries(){
        return repositorio.findAll()
                .stream()
                .map(s -> new SerieDto(s.getId(), s.getTitulo(), s.getPoster(),
                        s.getTotalTemporadas(), s.getGenero(), s.getAtores(),
                        s.getSinopse(), s.getAvaliacao()))
                .collect(Collectors.toList());
    }

}
