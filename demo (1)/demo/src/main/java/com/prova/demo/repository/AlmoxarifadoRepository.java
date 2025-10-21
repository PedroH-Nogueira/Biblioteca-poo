package com.prova.demo.repository;
import com.prova.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlmoxarifadoRepository extends JpaRepository<Livro, Long> {
}

