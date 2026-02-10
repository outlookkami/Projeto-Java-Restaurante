package com.kamilly.meson.controller;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.service.CategoriaService;
import com.kamilly.meson.service.ProdutoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CardapioController {

    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;


    @GetMapping("/garcom/cardapio")
    public String garcomCardapio(Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        model.addAttribute("categorias", categoriaService.listarCategorias(restaurante));
        return "garcom/cardapio";
    }
}
