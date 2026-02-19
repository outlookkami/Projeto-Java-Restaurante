package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.service.ProdutoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/garcom/pedidos")
@RequiredArgsConstructor
public class PedidoGarcomController {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;

    @GetMapping("/{comandaId}")
    public String incluirPedido(@PathVariable Long comandaId, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("comandaId", comandaId);
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        return "pedidos/adicionarPedido :: adicionarPedido";
    }

//    @PostMapping
}
