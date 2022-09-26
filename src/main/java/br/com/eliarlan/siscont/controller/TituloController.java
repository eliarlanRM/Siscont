package br.com.eliarlan.siscont.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eliarlan.siscont.model.Titulo;
import br.com.eliarlan.siscont.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping("/novo")
    public String novo() {
        return "titulo/formulario";
    }

    @PostMapping
    public String salvar(Titulo titulo) {
        tituloRepository.save(titulo);
        return "titulo/formulario";
    }
}
