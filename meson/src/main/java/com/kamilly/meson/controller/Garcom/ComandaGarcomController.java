package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.*;
import com.kamilly.meson.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/garcom/comandas")
@RequiredArgsConstructor
public class ComandaGarcomController {

    private final ComandaService comandaService;
    private final UsuarioService usuarioService;
    private final MesaService mesaService;
    private final PedidoService pedidoService;
    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final ItemPedidoService itemPedidoService;

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
        List<Mesa> mesas = mesaService.listarMesas(restaurante);
        List<Pedido> pedidos = pedidoService.buscarPedidosPorComanda(id, restaurante);
        Map<Long, List<Pedido>> pedidosComanda = new HashMap<>();
        pedidosComanda.put(id, pedidos);
        model.addAttribute("mesas", mesas);
        model.addAttribute("comanda", comanda);
        model.addAttribute("pedidosComanda", pedidosComanda);
        model.addAttribute("categorias", categoriaService.listarCategorias(restaurante));
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        return "garcom/comandas/detalheComanda";
    }

    @PostMapping("/trocar-mesa")
    public String trocaMesaComanda(@RequestParam Long comandaId, @RequestParam Long novaMesaId, Model model) {
        comandaService.trocarMesa(comandaId, novaMesaId);
        return "redirect:/garcom/mesas";
    }

    @GetMapping("/{id}/fechamento")
    public String fechamentoComanda(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Comanda comanda = comandaService.buscarComandaPorId(id, restaurante);
        List<ItemPedido> itens = comandaService.listarItensComanda(id);
        model.addAttribute("itens", itens);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("comanda", comanda);
        return "garcom/comandas/fechamentoComanda";
    }

//    @PostMapping("")
//    public String fecharComanda() {
//
//    }

}
