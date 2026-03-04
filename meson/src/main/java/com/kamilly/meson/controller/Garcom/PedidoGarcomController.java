package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.*;
import com.kamilly.meson.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/garcom/pedidos")
@RequiredArgsConstructor
public class PedidoGarcomController {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final ComandaService comandaService;
    private final ItemPedidoService itemPedidoService;


    @GetMapping("/{comandaId}")
    public String incluirPedido(@PathVariable Long comandaId, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Comanda comanda = comandaService.buscarComandaPorId(comandaId, restaurante);
        List<Produto> produtos = produtoService.listarProdutos(restaurante);
        model.addAttribute("comanda", comanda);
        model.addAttribute("comandaId", comandaId);
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        return "garcom/comandas/detalheComanda";
        //        return "pedidos/adicionarPedido :: adicionarPedido";
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
        return "redirect:/garcom/mesas";
    }

    @GetMapping("/{id}/detalhe")
    public String detalhePedido (@PathVariable Long id, Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Pedido pedido = pedidoService.buscarPedidoPorId(id, restaurante);
        List<ItemPedido> itens = itemPedidoService.listarItensPedido(id);
        Map<Long, List<ItemPedido>> itensPedido = new HashMap<>();
        itensPedido.put(id, itens);
        model.addAttribute("pedido", pedido);
        model.addAttribute("pedidosComanda", itensPedido);
        return "garcom/pedidos/detalhePedido";
    }
}
