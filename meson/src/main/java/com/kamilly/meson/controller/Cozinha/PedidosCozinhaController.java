package com.kamilly.meson.controller.Cozinha;

import com.kamilly.meson.dto.response.ComandaResDTO;
import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.repository.PedidoRepository;
import com.kamilly.meson.service.ItemPedidoService;
import com.kamilly.meson.service.PedidoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cozinha/pedidos")
@RequiredArgsConstructor
public class PedidosCozinhaController {

    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;
    private final UsuarioService usuarioService;
    private final PedidoRepository pedidoRepository;

    @GetMapping
    public String listarPedidosCozinha(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
//        List<Pedido> pedidos = pedidoService.listarPedidosAbertosPorRestaurante(restaurante);
        List<Pedido> pedidos = pedidoService.listarPedidosCozinha(restaurante);
        Map<Long, List<ItemPedido>> itensPedido = new HashMap<>();
        for(Pedido pedido : pedidos) {
            List<ItemPedido> itens = itemPedidoService.listarItensPedido(pedido.getId());
            itensPedido.put(
                    pedido.getId(),
                    itens != null ? itens : new ArrayList<>()
            );
        }

        Map<Long, Long> itensRestantes = new HashMap<>();
        for(Pedido pedido : pedidos) {
            itensRestantes.put(
              pedido.getId(),
              itemPedidoService.contagemItensRestantes(pedido)
            );
        }
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("itens", itensPedido);
        model.addAttribute("itensRestantes", itensRestantes);
        return "cozinha/pedidos";
    }

    @PostMapping("/item/{id}/status")
    public String atualizarStatusItem(@PathVariable Long id, @RequestParam String acao) {
        itemPedidoService.atualizarStatus(id, acao);
        return "redirect:/cozinha/pedidos";
    }

//    Pedido pedidoId = pedidoRepository.findById(pedido.getId()).orElseThrow();
//    List<ItemPedido> itensRestantes = pedidoService.contagemItensRestantes(pedidoId);
}
