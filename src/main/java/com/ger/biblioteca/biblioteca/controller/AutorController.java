package com.ger.biblioteca.biblioteca.controller;

import com.ger.biblioteca.biblioteca.model.Autor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/livros/autores")
public class AutorController {

    private final List<Autor> Autores = new ArrayList<>();

    @GetMapping("/autorFormulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("autor", new Autor());
        return "livros/autores/autorFormulario";
    }

    @PostMapping
    public String cadastrarAutor(
            @ModelAttribute("autor") Autor autor) {

        Autores.add(autor);

        System.out.println("Nome: " + autor.getNome());
        System.out.println("Nacionalidade: " + autor.getNacionalidade());

        return "livros/autores/confirmacaoAutor";
    }

    @GetMapping("/listarAutores")
    public String listarAutores(Model model) {
        model.addAttribute("Autores", Autores);
        return "livros/autores/listaAutores";
    }

    @GetMapping("/excluir/{indice}")
    public String excluirAutor(
            @PathVariable("indice") int indice) {

        Autores.remove(indice);

        return "redirect:/livros/autores/listarAutores";
    }

    @GetMapping("/editar/{indice}")
    public String editarAutor(
            @PathVariable("indice") int indice,
            Model model) {

        model.addAttribute("autor", Autores.get(indice));
        model.addAttribute("indice", indice);

        return "livros/autores/autorFormulario";
    }

    @PostMapping("/atualizar/{indice}")
    public String atualizarAutor(
            @PathVariable("indice") int indice,
            @ModelAttribute("autor") Autor autor) {

        Autores.set(indice, autor);

        return "redirect:/livros/autores/listarAutores";
    }
}