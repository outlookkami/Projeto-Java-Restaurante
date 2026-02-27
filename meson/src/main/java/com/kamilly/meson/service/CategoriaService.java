package com.kamilly.meson.service;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public List<CategoriaProduto> listarCategorias(Restaurante restaurante){
        return categoriaRepository.findAllByRestaurante(restaurante);
    }

    public CategoriaProduto buscarCategoriaPorId(Long id, Restaurante restaurante){
        return categoriaRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
    }

    public void salvarCategoria(CategoriaProduto categoria, Restaurante restaurante){
        categoria.setRestaurante(restaurante);
        categoria.setAtiva(true);
        categoriaRepository.save(categoria);
    }

    @Transactional
    public void deletarCategoria(Long id, Restaurante restaurante){
       CategoriaProduto categoria= categoriaRepository
                .findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
        categoriaRepository.delete(categoria);
    }


}
