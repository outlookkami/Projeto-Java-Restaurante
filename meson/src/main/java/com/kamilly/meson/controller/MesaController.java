package com.kamilly.meson.controller;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.service.MesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        List<Mesa> mesas = mesaService.listarMesas();
        model.addAttribute("mesas", mesas);
        return "admin/mesas";
    }
}
