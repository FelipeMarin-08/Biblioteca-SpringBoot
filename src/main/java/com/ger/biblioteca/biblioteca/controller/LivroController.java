package com.ger.biblioteca.biblioteca.controller;

import com.ger.biblioteca.biblioteca.repository.LivroRepository;
import com.ger.biblioteca.biblioteca.model.Livro;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final List<Livro> livros = new ArrayList<>();
    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

        repository.save(livro);

        livros.add(livro);

        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("ISBN: " + livro.getIsbn());

        return "livros/confirmacao";
    }

    // Listar todos os livros cadastrados
    @GetMapping("/lista")
    public String listar(Model model) {

        livros.clear();
        livros.addAll(repository.findAll());

        model.addAttribute("livros", livros);

        return "livros/lista";
    }

    // Excluir um livro
    @GetMapping("/livros/excluir/{id}")
    public String excluirLivro(
            @PathVariable("id") Long id) {

        repository.deleteById(id);

        return "redirect:/livros";
    }

    // Editar um livro
    @GetMapping("/editar/{id}")
    public String editarLivro(
            @PathVariable("id") Long id,
            Model model) {

        Livro livro = repository.findById(id)
                .orElseThrow();

        model.addAttribute("livro", livro);

        return "livros/formulario";
    }

    // Atualizar um livro
    @PostMapping("/atualizar/{id}")
    public String atualizarLivro(
            @PathVariable("id") Long id,
            @ModelAttribute("livro") Livro livro) {

        Livro livroAtual = repository.findById(id)
                .orElseThrow();

        livroAtual.setTitulo(livro.getTitulo());
        livroAtual.setAutor(livro.getAutor());
        livroAtual.setIsbn(livro.getIsbn());

        repository.save(livroAtual);

        return "redirect:/livros/lista";
    }
}