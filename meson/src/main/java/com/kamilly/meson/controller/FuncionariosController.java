package com.kamilly.meson.controller;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/funcionarios")
public class FuncionariosController {
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarFuncionarios(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("perfis", usuarioService.perfisPermitidosCadastro(usuarioLogado.getPerfilUsuario()));
        model.addAttribute("mostrarModal", false);
        return "admin/funcionarios";
    }

    @GetMapping("/novo")
    public String novoFuncionarios(Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        PerfilUsuario perfilUsuario = usuarioService.getUsuarioLogado().getPerfilUsuario();
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("mostrarModal", true);
        model.addAttribute("perfis", usuarioService.perfisPermitidosCadastro(perfilUsuario));

        return "admin/funcionarios";
    }

//    @GetMapping("/editar/{id}")
//    public String editarFuncionario(Model model, @PathVariable Long id) {
//        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
//    }
//
    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute("funcionario") Usuario funcionario, Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
//        Usuario usuario = new Usuario();
//        PerfilUsuario perfilUsuario = usuario.getPerfilUsuario();
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        usuarioService.salvarFuncionario(funcionario, restaurante);
        return "admin/funcionarios";
    }
//
//    @PostMapping("/excluir")
//    public String excluirFuncionario(){
//
//    }

}
