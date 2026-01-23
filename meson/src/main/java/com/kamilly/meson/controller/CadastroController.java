package com.kamilly.meson.controller;

import com.kamilly.meson.dto.request.RestauranteReqDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @GetMapping("/admin")
    public String admin(){
        return "cadastro/admin";
    }

//    @Autowired
//    private RestauranteRepository restauranteRepository;

    @GetMapping("/restaurante")
    public String cadRest(Model model){
        model.addAttribute("restauranteDTO", new RestauranteReqDTO());
        return "cadastro/restaurante";
    }

    @PostMapping("/salvar")
    public String salvarRestaurante(@Valid @ModelAttribute RestauranteReqDTO restauranteDTO){
        System.out.println(restauranteDTO.getCnpj());
        return "redirect:/cadastro/admin";
    }

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
