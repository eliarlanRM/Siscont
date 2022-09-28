package br.com.eliarlan.siscont.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eliarlan.siscont.model.StatusTitulo;
import br.com.eliarlan.siscont.model.Titulo;
import br.com.eliarlan.siscont.repository.TituloRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    private static final String CADASTRO_VIEW = "titulo/formulario";

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
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(new Titulo());
        return mv;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView edicao(@PathVariable("id") Titulo titulo) {
        ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
        mv.addObject(titulo);
        return mv;
    }

    @PostMapping
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
        if (errors.hasErrors()) {
            return CADASTRO_VIEW;
        }
        tituloRepository.save(titulo);

        attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
        return "redirect:/titulos";
    }

    @DeleteMapping("{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
        tituloRepository.deleteById(id);

        attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
        return "redirect:/titulos";
    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }

}
