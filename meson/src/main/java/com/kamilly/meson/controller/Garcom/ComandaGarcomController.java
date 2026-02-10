package com.kamilly.meson.controller.Garcom;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.service.ComandaService;
import com.kamilly.meson.service.MesaService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/garcom/comandas")
@RequiredArgsConstructor
public class ComandaGarcomController {

    private final ComandaService comandaService;
    private final UsuarioService usuarioService;
    private final MesaService mesaService;

    @GetMapping
    public String listarComandas(Model model, Mesa mesa) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();

        model.addAttribute("comandas", comandaService.buscarComandasAbertasMesa(mesa, restaurante));
        return "garcom/mesas";
    }

    @PostMapping
    public String criarComanda(@RequestParam Long mesaId, @RequestParam String nomeCliente) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(mesaId, restaurante);
        comandaService.abrirComanda(mesaId, nomeCliente, restaurante);
        return "redirect:/garcom/mesas";
    }

}
