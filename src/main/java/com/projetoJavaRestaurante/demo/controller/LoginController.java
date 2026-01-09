package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.model.Restaurante;
import com.projetoJavaRestaurante.demo.model.Usuario;
import com.projetoJavaRestaurante.demo.model.enums.PerfilUsuario;
import com.projetoJavaRestaurante.demo.repository.RestauranteRepository;
import com.projetoJavaRestaurante.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String dashboard() {
        return "instrucoes";
    }

    @PostMapping("/logar")
    public String loginUsuario(Usuario usuario, Model model, HttpServletResponse response) {
        Usuario user = this.usuarioRepository.login(usuario.getEmailUsuario(), usuario.getSenhaUsuario());
        if(usuarioRepository != null){
            return "redirect:/instrucoes";
        }

        model.addAttribute("erro", "Usuario Invalido!");
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro(){
        return "cadastro";
    }

    @GetMapping("/instrucoes")
    public String instrucoes(){
        return "instrucoes";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastro(@Valid Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return "redirect:/cadastro";
        }

        usuarioRepository.save(usuario);
        restauranteRepository.save(usuario.getRestaurante());

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
