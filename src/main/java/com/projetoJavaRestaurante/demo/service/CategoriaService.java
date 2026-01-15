package com.projetoJavaRestaurante.demo.service;

import com.projetoJavaRestaurante.demo.dto.response.CategoriaResponseDTO;
import com.projetoJavaRestaurante.demo.model.CategoriaProduto;
import com.projetoJavaRestaurante.demo.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaProduto> listar() {
        this.categoriaRepository.findAll();
        return(List<CategoriaProduto>)  this.categoriaRepository.findAll();
    }

    public void salvar(CategoriaResponseDTO categoriaResponseDto) {
        CategoriaProduto categoriaProduto = new CategoriaProduto();
        categoriaProduto.setNome(categoriaResponseDto.getNome());
        categoriaRepository.save(categoriaProduto);
    }

    public CategoriaProduto buscarPorId(Long id) {
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    public void atualizar(Long id, CategoriaResponseDTO categoriaResponseDto) {
        CategoriaProduto categoriaProduto = buscarPorId(id);
        categoriaProduto.setNome(categoriaResponseDto.getNome());
        categoriaRepository.save(categoriaProduto);
    }

    public void desativar(Long id) {
        CategoriaProduto categoriaProduto = buscarPorId(id);
        categoriaProduto.setAtiva(false);
        categoriaRepository.save(categoriaProduto);
    }


}
