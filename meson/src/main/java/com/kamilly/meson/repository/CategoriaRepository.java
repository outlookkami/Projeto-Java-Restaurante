package com.kamilly.meson.repository;

import com.kamilly.meson.model.CategoriaProduto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaProduto, Long> {
    Optional<CategoriaProduto> findByNome(String nome);

    @Transactional
    void deleteById(Long id);
}
