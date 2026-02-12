package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.service.ComandaService;
import com.kamilly.meson.service.MesaService;
import com.kamilly.meson.service.PedidoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String listarMesas(Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        List<Mesa> mesas = mesaService.listarMesas(restaurante);
        Map<Long, List<Comanda>> comandasMesa = new HashMap<>();
        Map<Long, List<Pedido>> pedidosComanda = new HashMap<>();
        for(Mesa mesa : mesas){
            List<Comanda> comandas = comandaService.buscarComandasAbertasMesa(mesa.getId(), restaurante);
            comandasMesa.put(
              mesa.getId(),
              comandas != null ? comandas : new ArrayList<>()
            );

            for(Comanda comanda : comandas){
                List<Pedido> pedidos = pedidoService.listarPedidosPorComanda(comanda.getId(), restaurante);

            }
        }

        model.addAttribute("mesas", mesas);
        model.addAttribute("comandasMesa", comandasMesa);
        model.addAttribute("pedidosComanda", pedidosComanda);
        return "garcom/mesas/lista";
    }


    @GetMapping("/{id}/detalhe")
    public String detalheMesa(@PathVariable Long id, Model model){
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
        List<Comanda> comandas = comandaService.buscarComandasAbertasMesa(id, restaurante);
        model.addAttribute("mesa", mesa);
        model.addAttribute("comandas", comandas);
        return "garcom/mesas/detalheMesa :: detalheMesa";
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
