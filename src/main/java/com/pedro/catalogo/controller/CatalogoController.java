package com.pedro.catalogo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import com.pedro.catalogo.model.Musica;
import com.pedro.catalogo.service.CatalogoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CatalogoController {

    @Autowired
    CatalogoService catalogoService;

    @RequestMapping(value = "/musicas", method = RequestMethod.GET)
    public ModelAndView getMusicas() {
        ModelAndView response = new ModelAndView("musicas");
        List<Musica> musicas = catalogoService.findAll();
        response.addObject("musicas", musicas);
        return response;
    }

    @RequestMapping(value = "/musicas/{id}", method = RequestMethod.GET)
    public ModelAndView getMusicasDetalhes(@PathVariable("id") long id) {
        ModelAndView response = new ModelAndView("musicasDetalhes");
        Musica musica = catalogoService.findById(id);
        response.addObject("musica", musica);
        return response;
    }

    @RequestMapping(value = "/addMusica", method = RequestMethod.GET)
    public String getForm() {
        return "musicaForm";
    }

    @RequestMapping(value = "/addMusica", method = RequestMethod.POST)
    public String salvarMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Campos obrigatórios não preechidos!");
            return "redirect:/addMusica";
        }
        musica.setData(LocalDate.now());
        catalogoService.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public ModelAndView getEditarMusicas(@PathVariable("id") long id) {
        ModelAndView response = new ModelAndView("musicaForm2");
        Musica musica = catalogoService.findById(id);
        response.addObject("musica", musica);
        return response;
    }


    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String editarMusica(@Valid Musica musica, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Campos obrigatórios não preechidos!");
            return "redirect:/addMusica";
        }
        musica.setData(LocalDate.now());
        catalogoService.save(musica);
        return "redirect:/musicas";
    }

    @RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET)
    public String getExcluirMusicas(@PathVariable("id") long id) {
        catalogoService.excluir(id);
        return "redirect:/musicas";
    }

}
