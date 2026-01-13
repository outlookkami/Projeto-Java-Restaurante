package com.projetoJavaRestaurante.demo.repository;

import com.projetoJavaRestaurante.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    List<Produto> findByNome(String nome);
}
