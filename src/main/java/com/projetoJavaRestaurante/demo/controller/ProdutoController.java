package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.model.Produto;
import com.projetoJavaRestaurante.demo.repository.ProdutoRepository;
import com.projetoJavaRestaurante.demo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public String getAllProducts(){
        List<Produto> produtoList = produtoService.listarProdutos()

    }
}
