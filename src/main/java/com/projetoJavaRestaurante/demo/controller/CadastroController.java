package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.model.Restaurante;
import com.projetoJavaRestaurante.demo.model.Usuario;
import com.projetoJavaRestaurante.demo.repository.RestauranteRepository;
import com.projetoJavaRestaurante.demo.repository.UsuarioRepository;
import com.projetoJavaRestaurante.demo.model.enums.PerfilUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CadastroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/cadastro/restaurante")
    public String cadastroRestaurante(Model model) {
        return "cadastro/restaurante";
    }

    @GetMapping("/admin")
    public String cadastroAdmin() {
        return "cadastro/admin";
    }

    @GetMapping("/instrucoes")
    public String instrucoes() {
        return "instrucoes";
    }

    @RequestMapping(value = "/cadastro/restaurante", method = RequestMethod.POST)
    public String cadastroRestaurante(@Valid Restaurante restaurante, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/cadastro/restaurante";
        }

        restauranteRepository.save(restaurante);

        return "redirect:/cadastro/admin";
    }

    @RequestMapping(value = "/cadastro/admin", method = RequestMethod.POST)
    String cadastroAdmin(@Valid Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/cadastro/admin";
        }

        usuario.setPerfilUsuario(PerfilUsuario.ADMIN);
        usuarioRepository.save(usuario);

        return "redirect:/instrucoes";
    }


//    @PostMapping("/cadastro")
//    public String cadastrar(@ModelAttribute Usuario usuario, Restaurante restaurante){
//        usuario.setPerfilUsuario(PerfilUsuario.ADMIN);
//
//        usuarioRepository.save(usuario);
//        restauranteRepository.save(restaurante);
//
//        return "redirect:/instrucoes";
//    }

}
