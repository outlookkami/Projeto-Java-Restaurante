package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.service.PedidoService;
import com.kamilly.meson.service.ProdutoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/garcom/pedidos")
@RequiredArgsConstructor
public class PedidoGarcomController {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    @GetMapping("/{comandaId}")
    public String incluirPedido(@PathVariable Long comandaId, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("comandaId", comandaId);
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        return "pedidos/adicionarPedido :: adicionarPedido";
    }

    @GetMapping
    public String listarPedidos(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        List<Pedido> pedidos = pedidoService.listarPedidosAbertosPorRestaurante(restaurante);
        model.addAttribute("pedidos", pedidos);
        return "garcom/pedidos/lista";
    }

    @PostMapping
    public String salvarPedido(@RequestParam Long comandaId, @RequestParam List<Long> produtosIds, @RequestParam List<Integer> quantidades, @RequestParam(required = false) List<String> observacoes) {
       Pedido pedido = pedidoService.abrirPedido(comandaId, usuarioService.getUsuarioLogado());
       for (int i = 0; i < produtosIds.size(); i++) {
           if (quantidades.get(i) > 0){
               String obs = null;
               if (observacoes != null && observacoes.size() > i) {
                   obs = observacoes.get(i);
               }

               pedidoService.adicionarItem(
                       pedido.getId(),
                       produtosIds.get(i),
                       quantidades.get(i),
                       obs
               );
           }
       }
        return "redirect:/garcom/comandas/" + comandaId + "/detalhe";
    }
}
