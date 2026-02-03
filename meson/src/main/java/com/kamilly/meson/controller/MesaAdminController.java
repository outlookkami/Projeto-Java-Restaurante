package com.kamilly.meson.controller;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.MesaRepository;
import com.kamilly.meson.service.MesaService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.kamilly.meson.model.enums.StatusMesa.DISPONIVEL;

@Controller
@RequestMapping("/admin/mesas")
@RequiredArgsConstructor
public class MesaAdminController {

    private final MesaService mesaService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarMesas(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("mesas", mesaService.listarMesas(restaurante));
        model.addAttribute("mesa", new Mesa());
        model.addAttribute("mostrarModal", false);
        return "admin/mesas";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
        model.addAttribute("mesas", mesaService.listarMesas(restaurante));
        model.addAttribute("mesa", mesaService.buscarMesaPorId(id, restaurante));
        model.addAttribute("mostrarModal", true);
        return "admin/mesas";
    }

    @PostMapping("/salvar")
    public String salvarMesa(Mesa mesa, @AuthenticationPrincipal Usuario usuarioLogado) {
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        mesaService.salvarMesa(mesa, restaurante);
        mesa.setStatus(mesa.getStatus());
        return "redirect:/admin/mesas";
    }

    @PostMapping("/excluir/{id}")
    public String excluirMesa(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Mesa mesa = mesaService.buscarMesaPorId(id, restaurante);
        model.addAttribute("mesa", mesaService.buscarMesaPorId(id, restaurante));
        mesaService.deletarMesa(id, restaurante);
        return "redirect:/admin/mesas";
    }
}
