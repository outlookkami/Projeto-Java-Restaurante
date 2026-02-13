package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.*;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/garcom/comandas")
@RequiredArgsConstructor
public class ComandaGarcomController {

    private final ComandaService comandaService;
    private final UsuarioService usuarioService;
    private final MesaService mesaService;
    private final PedidoService pedidoService;
    private final ProdutoService produtoService;

    @GetMapping
    public String listarComandas(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        List<Comanda> comandas = comandaService.listarComandasAbertasRestaurante(restaurante);
        model.addAttribute("comandas", comandas);
        return "garcom/comandas/lista";
    }

    @PostMapping
    public String criarComanda(@RequestParam Long mesaId, @RequestParam String nomeCliente) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(mesaId, restaurante);
        comandaService.abrirComanda(mesaId, nomeCliente, restaurante);
        return "redirect:/garcom/mesas";
    }

    @GetMapping("/{id}/detalhe")
    public String detalheComanda(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Comanda comanda = comandaService.buscarComandaPorId(id, restaurante);
        List<Pedido> pedidos = pedidoService.buscarPedidosPorComanda(id, restaurante);
        model.addAttribute("comandas", comanda);
        model.addAttribute("pedidos", pedidos);
        return "garcom/comandas/detalheComanda :: detalheComanda";
    }

    @GetMapping("/{id}/novo-pedido")
    public String novoPedido(@PathVariable Long id, Model model) {
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        Comanda comanda = comandaService.buscarComandaPorId(id, restaurante);
        List<Produto> produtos = produtoService.listarProdutos(restaurante);
        Pedido pedido = new Pedido();
        pedido.setComanda(comanda);
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(pedido);
        pedido.setItens(new ArrayList<>());
        pedido.getItens().add(itemPedido);
        model.addAttribute("pedidos", pedido);
        model.addAttribute("produtos", produtos);
        return "garcom/pedidos/adicionarPedido :: adicionarPedido";
    }

}
