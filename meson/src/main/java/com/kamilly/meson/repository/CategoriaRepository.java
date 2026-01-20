package com.kamilly.meson.repository;

import com.kamilly.meson.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaProduto, Long> {
}
