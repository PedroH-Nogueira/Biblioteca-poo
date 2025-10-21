package com.prova.demo.controller;

import com.prova.demo.model.Livro;
import com.prova.demo.service.LivroService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livro> listar() { return service.listarTodos(); }

    @GetMapping("/{id}")
    public Livro buscar(@PathVariable Long id) {
        return service.buscarPorId(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    @PostMapping
    public Livro criar(@RequestBody Livro livro) { return service.salvar(livro); }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livro) {
        return service.atualizar(id, livro);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) { service.deletar(id); }

    @PutMapping("/{id}/alugar")
    public Livro alugar(@PathVariable Long id) { return service.alugarLivro(id); }

    @PutMapping("/{id}/devolver")
    public Livro devolver(@PathVariable Long id) { return service.devolverLivro(id); }
}

