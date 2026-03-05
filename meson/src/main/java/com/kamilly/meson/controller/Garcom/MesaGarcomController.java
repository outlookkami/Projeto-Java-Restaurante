package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.dto.response.ComandaResDTO;
import com.kamilly.meson.model.*;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/garcom/mesas")
@RequiredArgsConstructor
public class MesaGarcomController {

    private final MesaService mesaService;
    private final UsuarioService usuarioService;
    private final ComandaService comandaService;
    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;

    @GetMapping
    public String listarMesas(Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        List<Mesa> mesas = mesaService.listarMesas(restaurante);
        Map<Long, List<ComandaResDTO>> comandasMesa = new HashMap<>();
        Map<Long, List<Pedido>> pedidosComanda = new HashMap<>();
        Map<Long, List<ItemPedido>> itensPedido = new HashMap<>();
        for(Mesa mesa : mesas){
            List<ComandaResDTO> comandas = comandaService.buscarComandasAbertasMesa(mesa.getId(), restaurante);
            comandasMesa.put(
              mesa.getId(),
              comandas != null ? comandas : new ArrayList<>()
            );

            for(ComandaResDTO comanda : comandas){
                List<Pedido> pedidos = pedidoService.listarPedidosPorComanda(comanda.getId(), restaurante);

            }
        }

        model.addAttribute("mesas", mesas);
        model.addAttribute("comandasMesa", comandasMesa);
        model.addAttribute("pedidosComanda", pedidosComanda);
        model.addAttribute("itensPedido", itensPedido);
        return "garcom/mesas/lista";
    }


    @GetMapping("/{id}/detalhe")
    public String detalheMesa(@PathVariable Long id, Model model){
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
        List<ComandaResDTO> comandas = comandaService.buscarComandasAbertasMesa(id, restaurante);
        model.addAttribute("mesa", mesa);
        model.addAttribute("comandas", comandas);
        return "garcom/mesas/detalheMesa :: detalheMesa";
    }

    @PostMapping("/item/{id}/status")
    public String atualizarStatusItem(@PathVariable Long id, @RequestParam String acao) {
        itemPedidoService.atualizarStatus(id, acao);
        return "redirect:/garcom/mesas";
    }

//    @GetMapping("/{id}")
//    public String detalhe

//    @GetMapping
//    public String detalheMesa(Long id, Model model){
//        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
//        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
//
//        return "redirect:/garcom/mesas";
//    }

}
