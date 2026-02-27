package com.kamilly.meson.controller.AdminGeral;

import com.kamilly.meson.dto.response.RestauranteAnaliseResDTO;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.service.AdminGeralService;
import com.kamilly.meson.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin-geral")
@RequiredArgsConstructor
public class AdminGeralController {
    private final AdminGeralService adminGeralService;
    private final RestauranteService restauranteService;

    @GetMapping("/analisarRestaurantes")
    public String analisarRestaurantes(Model model) {
        List<Restaurante> restaurantes = restauranteService.listarRestaurantesPendentes();
        model.addAttribute("restaurantes", restaurantes);
        return "admin-geral/analisarRestaurantes";
    }

    @GetMapping("/analisarRestaurantes/{id}")
    @ResponseBody
    public RestauranteAnaliseResDTO buscarRestaurante(@PathVariable Integer id) {
        return restauranteService.buscarParaAnalise(id);
    }

    @GetMapping("/analisarRestaurantes/aprovar/{id}")
    public String aprovarRestaurante(@PathVariable Integer id) {
        restauranteService.aprovarRestaurante(id);
        return "redirect:/admin-geral/analisarRestaurantes";
    }

    @GetMapping("/analisarRestaurantes/recusar/{id}")
    private String recusarRestaurante(@PathVariable Integer id) {
        restauranteService.recusarRestaurante(id);
        return "redirect:/admin-geral/analisarRestaurantes";
    }
}
