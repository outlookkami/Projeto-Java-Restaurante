package com.kamilly.meson.service;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaProduto> listarCategorias(){
        return categoriaRepository.findAll();
    }
}
