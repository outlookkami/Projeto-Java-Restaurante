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

    public void salvarProduto(Produto produto, Restaurante restaurante){
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
