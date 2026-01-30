package com.kamilly.meson.controller;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.MesaRepository;
import com.kamilly.meson.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static com.kamilly.meson.model.enums.StatusMesa.DISPONIVEL;

@Controller
@RequestMapping("/admin/mesas")
@RequiredArgsConstructor
public class MesaAdminController {

    private final MesaService mesaService;
    private final MesaRepository mesaRepository;

    @GetMapping
    public String listarMesas(Model model) {
        List<Mesa> mesas = mesaService.listarMesas();
        model.addAttribute("mesas", mesas);
        return "admin/mesas";
    }

    @PostMapping("/salvar")
    public String salvarMesa(Mesa mesa, @AuthenticationPrincipal Usuario usuarioLogado) {
        mesa.setStatus(mesa.getStatus());

        mesa.setRestaurante(usuarioLogado.getRestaurante());
        mesaService.salvarMesa(mesa);
        return "redirect:/admin/mesas";
    }
}
