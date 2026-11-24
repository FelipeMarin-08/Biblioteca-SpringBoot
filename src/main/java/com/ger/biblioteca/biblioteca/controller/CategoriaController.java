package com.ger.biblioteca.biblioteca.controller;

import com.ger.biblioteca.biblioteca.model.Categoria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/livros/categorias")
public class CategoriaController {

    private final List<Categoria> Categorias = new ArrayList<>();

    @GetMapping("/categoriaFormulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "livros/categorias/categoriaFormulario";
    }

    @PostMapping
    public String cadastrarCategoria(
            @ModelAttribute("categoria") Categoria categoria) {

        Categorias.add(categoria);

        System.out.println("Nome: " + categoria.getNome());
        System.out.println("ID: " + categoria.getId());

        return "livros/categorias/confirmacaoCategoria";
    }

    @GetMapping("/listarCategorias")
    public String listarCategorias(Model model) {
        model.addAttribute("Categorias", Categorias);
        return "livros/categorias/listaCategorias";
    }

    @GetMapping("/excluir/{indice}")
    public String excluirCategoria(
            @PathVariable("indice") int indice) {

        Categorias.remove(indice);

        return "redirect:/livros/categorias/listarCategorias";
    }

    @GetMapping("/editar/{indice}")
    public String editarCategoria(
            @PathVariable("indice") int indice,
            Model model) {

        model.addAttribute("categoria", Categorias.get(indice));
        model.addAttribute("indice", indice);

        return "livros/categorias/categoriaFormulario";
    }

    @PostMapping("/atualizar/{indice}")
    public String atualizarCategoria(
            @PathVariable("indice") int indice,
            @ModelAttribute("categoria") Categoria categoria) {

        Categorias.set(indice, categoria);

        return "redirect:/livros/categorias/listarCategorias";
    }
}