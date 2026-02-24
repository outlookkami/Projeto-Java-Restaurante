package com.kamilly.meson.service;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.repository.CategoriaRepository;
import com.kamilly.meson.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public List<Produto> listarProdutos(Restaurante restaurante){
        return produtoRepository.findAllByRestaurante(restaurante);
    }

    public Produto buscarProdutoPorId(Long id, Restaurante restaurante){
        return  produtoRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public List<Produto> buscarProduto(String nome, Restaurante restaurante, CategoriaProduto categoria){
        if ((nome == null || nome.isBlank()) && categoria == null) {
            return produtoRepository.findAllByRestaurante(restaurante);
        }

        if (categoria != null && (nome == null || nome.isBlank())) {
            return produtoRepository.findByCategoriaAndRestaurante(categoria, restaurante);
        }

        if (categoria == null) {
            return produtoRepository.findByNomeContainingIgnoreCaseAndRestaurante(nome, restaurante);
        }

        return produtoRepository.findByNomeContainingIgnoreCaseAndCategoriaAndRestaurante(nome, categoria, restaurante);
    }

    public void salvarProduto(Produto produto, Restaurante restaurante){
        Long idCategoria = produto.getCategoria().getId();
        CategoriaProduto categoria = categoriaRepository.findByIdAndRestaurante(idCategoria, restaurante)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        produto.setRestaurante(restaurante);
        produto.setCategoria(categoria);
        produtoRepository.save(produto);
    }

    @Transactional
    public void deletarProduto(Long id, Restaurante restaurante){
        Produto produto = produtoRepository
                .findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        produtoRepository.delete(produto);
    }
}
