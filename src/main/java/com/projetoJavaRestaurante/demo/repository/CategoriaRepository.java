package com.projetoJavaRestaurante.demo.repository;

import com.projetoJavaRestaurante.demo.model.CategoriaProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaProduto, Long> {
//    CategoriaProduto findByName(String nome);
//    CategoriaProduto findByDescricao(String descricao);

}
