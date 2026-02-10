package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.service.ComandaService;
import com.kamilly.meson.service.MesaService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/garcom/mesas")
@RequiredArgsConstructor
public class MesaGarcomController {

    private final MesaService mesaService;
    private final UsuarioService usuarioService;
    private final ComandaService comandaService;

    @GetMapping
    public String listarMesas(Model model, Mesa mesa){
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        List<Mesa> mesas = mesaService.listarMesas(restaurante);
        List<Comanda> comandas = comandaService.buscarComandasAbertasMesa(mesa, restaurante);
        model.addAttribute("mesas", mesas);
        model.addAttribute("comandas", comandas);
        return "garcom/mesas/lista";
    }

    @GetMapping("/{id}")
    public String detalhe(@PathVariable Long id, Model model){
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
        model.addAttribute("mesa", mesa);
        model.addAttribute("comandas", comandaService.buscarComandasAbertasMesa(mesa, restaurante));
        return "garcom/mesas/detalhe";
    }

//    @GetMapping
//    public String detalheMesa(Long id, Model model){
//        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
//        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
//
//        return "redirect:/garcom/mesas";
//    }

}
