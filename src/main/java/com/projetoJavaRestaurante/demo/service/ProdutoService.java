package com.projetoJavaRestaurante.demo.service;

import com.projetoJavaRestaurante.demo.model.Produto;
import com.projetoJavaRestaurante.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
//    private final ProdutoRepository produtoRepository;
//
//    public ProdutoService(ProdutoRepository produtoRepository) {
//        this.produtoRepository = produtoRepository;
//    }
//
//    public List<Produto> listarProdutos(){
//        this.produtoRepository.findAll();
//        return produtoRepository.findAll();
//    }

    @Autowired
    private ProdutoRepository produtoRepository;

//    public List<Produto> listarProdutos(){
//        List<Produto> produtoList = produtoRepository.listarProdutos();
//        return produtoList;
//    }


}
