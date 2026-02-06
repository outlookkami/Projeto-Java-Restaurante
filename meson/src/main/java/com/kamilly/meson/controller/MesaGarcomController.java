package com.kamilly.meson.controller;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.service.MesaService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/garcom/mesas")
@RequiredArgsConstructor
public class MesaGarcomController {

    private final MesaService mesaService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarMesas(Model model){
        Restaurante restaurante =  usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("mesas", mesaService.listarMesas(restaurante));
        return "garcom/mesas/lista";
    }

}
