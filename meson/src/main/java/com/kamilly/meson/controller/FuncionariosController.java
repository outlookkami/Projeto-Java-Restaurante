package com.kamilly.meson.controller;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/funcionarios")
public class FuncionariosController {
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarFuncionarios(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("funcionarios", usuarioService.listarUsuarios());
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("mostrarModal", false);
        return "admin/funcionarios";
    }

//    @GetMapping("/editar/{id}")
//    public String editarFuncionario(Model model, @PathVariable Long id) {
//        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
//    }
//
//    @PostMapping("/salvar")
//    public String salvarFuncionario(){
//
//    }
//
//    @PostMapping("/excluir")
//    public String excluirFuncionario(){
//
//    }

}
