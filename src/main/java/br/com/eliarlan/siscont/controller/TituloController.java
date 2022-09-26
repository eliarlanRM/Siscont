package br.com.eliarlan.siscont.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.eliarlan.siscont.model.StatusTitulo;
import br.com.eliarlan.siscont.model.Titulo;
import br.com.eliarlan.siscont.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    @Autowired
    private TituloRepository tituloRepository;

    @GetMapping
    public ModelAndView pesquisar() {
        List<Titulo> todosTitulos = tituloRepository.findAll();
        ModelAndView mv = new ModelAndView("titulo/listar");
        mv.addObject("titulos", todosTitulos);
        return mv;
    }

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("titulo/formulario");
        return mv;
    }

    @PostMapping
    public ModelAndView salvar(Titulo titulo) {
        tituloRepository.save(titulo);
        ModelAndView mv = new ModelAndView("titulo/formulario");
        mv.addObject("mensagem", "Título salvo com sucesso!");
        return mv;
    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }

}
