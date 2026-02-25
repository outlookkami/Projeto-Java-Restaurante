package com.kamilly.meson.controller.AdminGeral;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-geral")
@RequiredArgsConstructor
public class AdminGeralController {
    private final AdminGeralService adminGeralService;
    private final RestauranteService restauranteService;

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
