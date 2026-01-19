package com.kamilly.meson.controller;

import com.kamilly.meson.dto.request.RestauranteDTO;
import com.kamilly.meson.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.


@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @GetMapping("/admin")
    public String admin(){
        return "cadastro/admin";
    }

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/restaurante")
    public String cadRest(Model model){
        model.addAttribute("restauranteDTO", new RestauranteDTO());
        return "cadastro/admin";
    }

    @PostMapping("/salvar")
    public String salvarRestaurante(@Valid)

//    @PostMapping("/cadastro/restaurante")
//    public String cadastrarRestaurante(@ModelAttribute RestauranteDTO restauranteDTO){
//        System.out.println(restauranteDTO.getCnpj());
//        return "redirect:/cadastro/admin";
//    }
//
//    @PostMapping("/cadastro/restaurante")
//    public String cadastrarRestaurante(@RequestParam("cnpj") String cnpj){
//        System.out.println(cnpj);
//        return "redirect:/cadastro/admin";
//    }

    @GetMapping("/instrucoes")
    public String instrucoes(){
        return "cadastro/instrucoes";
    }
}
