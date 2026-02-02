package com.kamilly.meson.repository;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Restaurante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaProduto, Long> {
    Optional<CategoriaProduto> findByIdAndRestaurante(Long id, Restaurante restaurante);

    List<CategoriaProduto> findAllByRestaurante(Restaurante restaurante);

    @Transactional
    void deleteById(Long id);
}
