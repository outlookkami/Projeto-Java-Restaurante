package com.kamilly.meson.service;

import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
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

    public List<Produto> listarProdutos(Restaurante restaurante){
        return produtoRepository.findAllByRestaurante(restaurante);
    }

    public Produto buscarProdutoPorId(Long id, Restaurante restaurante){
        return  produtoRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public List<Produto> buscarProduto(String nome, Restaurante restaurante, Long idCategoria){
        if ((nome == null || nome.isBlank()) && idCategoria == null) {
            return produtoRepository.findAllByRestaurante(restaurante);
        }

        if (idCategoria != null && (nome == null || nome.isBlank())) {
            return produtoRepository.findByIdCategoriaAndRestaurante(idCategoria, restaurante);
        }

        if (idCategoria == null) {
            return produtoRepository.findByNomeContainingIgnoreCaseAndRestaurante(nome, restaurante);
        }

        return produtoRepository.findByNomeContainingIgnoreCaseAndIdCategoriaAndRestaurante(nome, idCategoria, restaurante);
    }

    public void salvarProduto(Produto produto, Restaurante restaurante){
        if(produto.getIdCategoria() != null) {
            produto.setCategoria(produto.getIdCategoria().getNome());
        } else {
            throw new RuntimeException("Categoria não selecionada.");
        }
        produto.setRestaurante(restaurante);
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
