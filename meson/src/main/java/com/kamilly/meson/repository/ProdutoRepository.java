package com.kamilly.meson.repository;

import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByIdAndRestaurante(Long id, Restaurante restaurante);

    List<Produto> findAllByRestaurante(Restaurante restaurante);

    List<Produto> findByNomeContainingIgnoreCaseAndRestaurante(String nome, Restaurante restaurante);

    List<Produto> findByIdCategoriaAndRestaurante(Long idCategoria, Restaurante restaurante);

    List<Produto> findByNomeContainingIgnoreCaseAndIdCategoriaAndRestaurante(String nome, Long idCategoria, Restaurante restaurante);

    @Transactional
    void deleteByIdAndRestaurante(Long id, Restaurante restaurante);
}
