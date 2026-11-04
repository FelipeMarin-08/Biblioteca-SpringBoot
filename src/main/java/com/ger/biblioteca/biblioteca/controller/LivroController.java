
package com.ger.biblioteca.biblioteca.controller;

import com.ger.biblioteca.biblioteca.model.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

        import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();

    // Página inicial
    @GetMapping
    public String inicio() {
        return "livros/inicio";
    }

    // Exibir formulário de cadastro
    @GetMapping("/formulario")
    public String exibirFormulario(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/formulario";
    }

    // Cadastrar um livro
    @PostMapping
    public String cadastrarLivro(
            @ModelAttribute("livro") Livro livro) {

        livros.add(livro);

        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("ISBN: " + livro.getIsbn());

        return "livros/confirmacao";
    }

    // Listar todos os livros cadastrados
    @GetMapping("/lista")
    public String listar(Model model) {
        model.addAttribute("livros", livros);
        return "livros/lista";
    }


    @GetMapping("/excluir/{indice}")
    public String excluirLivro(@PathVariable("indice") int indice) {
        livros.remove(indice);
        return "redirect:/livros";
    }
}
