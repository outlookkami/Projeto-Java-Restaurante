package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.model.Usuario;
import com.projetoJavaRestaurante.demo.model.enums.PerfilUsuario;
import com.projetoJavaRestaurante.demo.model.enums.StatusUsuario;
import com.projetoJavaRestaurante.demo.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioLogado;

    @GetMapping("/login")
    public String login(){
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

//    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
//    public String cadastro(@Valid Usuario usuario, BindingResult result){
//        if(result.hasErrors()){
//            return "redirect:/cadastro";
//        }
//
//        usuarioLogado.save(usuario);
//
//        return "";
//    }

    @PostMapping("/cadastro")
    public String cadastrar(@ModelAttribute Usuario usuario){
        usuario.setPerfilUsuario(PerfilUsuario.ADMIN);
        usuario.setAtivo(StatusUsuario.ATIVO);

        usuarioLogado.save(usuario);

        return "redirect:/instrucoes";
    }
}
