package com.kamilly.meson.controller;

import com.kamilly.meson.config.UsuarioDetails;
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
    public String listarFuncionarios(Model model, @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        Usuario usuarioLogado = usuarioDetails.getUsuario();
        Restaurante restaurante = usuarioLogado.getRestaurante();
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("perfis", usuarioService.perfisPermitidosCadastro(usuarioLogado.getPerfilUsuario()));
        model.addAttribute("mostrarModal", false);
        return "admin/funcionarios";
    }

    @GetMapping("/buscar")
    public String buscarFuncionarios(@RequestParam(required = false) String nome, @RequestParam(required = false) PerfilUsuario perfilUsuario, Model model, @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        Usuario usuarioLogado = usuarioDetails.getUsuario();
        Restaurante restaurante = usuarioLogado.getRestaurante();
        model.addAttribute("funcionarios", usuarioService.buscarFuncionario(nome, restaurante, perfilUsuario));
        return "admin/funcionarios :: tabelaFuncionarios";
    }

    @GetMapping("/novo")
    public String novoFuncionario(Model model, @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        Usuario usuarioLogado = usuarioDetails.getUsuario();
        Restaurante restaurante = usuarioLogado.getRestaurante();
        PerfilUsuario perfilUsuario = usuarioLogado.getPerfilUsuario();
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("mostrarModal", true);
        model.addAttribute("perfis", usuarioService.perfisPermitidosCadastro(perfilUsuario));

        return "admin/funcionarios";
    }

    @GetMapping("/editar/{id}")
    public String editarFuncionario(Model model, @PathVariable Long id) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Usuario funcionario = usuarioService.buscarFuncionarioPorId(id, restaurante);
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        model.addAttribute("funcionario", usuarioService.buscarFuncionarioPorId(id, restaurante));
        model.addAttribute("mostrarModal", true);
        return "admin/funcionarios";
    }

    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute("funcionario") Usuario funcionario, Model model, @AuthenticationPrincipal UsuarioDetails usuarioDetails) {
        Usuario usuarioLogado = usuarioDetails.getUsuario();
        Restaurante restaurante = usuarioLogado.getRestaurante();
//        PerfilUsuario perfilUsuario = usuario.getPerfilUsuario();
        model.addAttribute("funcionario", new Usuario());
        model.addAttribute("funcionarios", usuarioService.listarFuncionarios(restaurante));
        usuarioService.salvarFuncionario(funcionario, restaurante);
        return "admin/funcionarios";
    }

    @PostMapping("/excluir/{id}")
    public String excluirFuncionario(@ModelAttribute Long id, Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Usuario funcionario = usuarioService.buscarFuncionarioPorId(id, restaurante);
        model.addAttribute("funcionarios", usuarioService.buscarFuncionarioPorId(id, restaurante));
        usuarioService.deletarFuncionario(id, restaurante);
        return "redirect:/admin/funcionarios";
    }

}
