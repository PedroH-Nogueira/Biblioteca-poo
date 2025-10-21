package com.prova.demo.service;


import com.prova.demo.model.Livro;
import com.prova.demo.repository.AlmoxarifadoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final AlmoxarifadoRepository repository;

    public LivroService(AlmoxarifadoRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listarTodos() { return repository.findAll(); }

    public Optional<Livro> buscarPorId(Long id) { return repository.findById(id); }

    public Livro salvar(Livro livro) { return repository.save(livro); }

    public Livro atualizar(Long id, Livro novoLivro) {
        return repository.findById(id).map(livro -> {
            livro.setTitulo(novoLivro.getTitulo());
            livro.setAutor(novoLivro.getAutor());
            livro.setAnoPublicacao(novoLivro.getAnoPublicacao());
            livro.setCategoria(novoLivro.getCategoria());
            livro.setDisponivel(novoLivro.isDisponivel());
            return repository.save(livro);
        }).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deletar(Long id) { repository.deleteById(id); }

    public Livro alugarLivro(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        if (!livro.isDisponivel()) {
            throw new RuntimeException("Livro já está emprestado!");
        }
        livro.setDisponivel(false);
        return repository.save(livro);
    }

    public Livro devolverLivro(Long id) {
        Livro livro = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        if (livro.isDisponivel()) {
            throw new RuntimeException("Livro já está disponível!");
        }
        livro.setDisponivel(true);
        return repository.save(livro);
    }
}

