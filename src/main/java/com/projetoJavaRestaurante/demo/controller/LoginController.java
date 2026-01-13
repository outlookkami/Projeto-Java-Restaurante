package com.projetoJavaRestaurante.demo.controller;


import com.projetoJavaRestaurante.demo.model.Usuario;
import com.projetoJavaRestaurante.demo.repository.RestauranteRepository;
import com.projetoJavaRestaurante.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String dashboard() {
        return "cadastro/instrucoes";
    }

    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) {
        Usuario user = this.usuarioRepository.login(usuario.getEmail(), usuario.getSenha());
        if (user != null) {
            return "redirect:/instrucoes";
        }

        model.addAttribute("erro", "Usuário Inválido!");
        return "login";
    }
}

