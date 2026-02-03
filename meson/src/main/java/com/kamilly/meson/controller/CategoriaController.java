package com.kamilly.meson.controller;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.service.CategoriaService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String listarCategorias(Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        model.addAttribute("categorias", categoriaService.listarCategorias(restaurante));
        model.addAttribute("categoria", new CategoriaProduto());
        model.addAttribute("mostrarModal", false);
        return "admin/categorias";
    }

    @GetMapping("/nova")
    public String novaCategoria(Model model){
        model.addAttribute("categoria", new CategoriaProduto());
        model.addAttribute("mostrarModal", true);
        return "admin/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model){
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        CategoriaProduto categoria = categoriaService.buscarCategoriaPorId(id, restaurante);
        model.addAttribute("categorias", categoriaService.listarCategorias(restaurante));
        model.addAttribute("categoria", categoriaService.buscarCategoriaPorId(id, restaurante));
        model.addAttribute("mostrarModal", true);
        return "admin/categorias";
    }

    @PostMapping("/salvar")
    public String salvarCategoria(CategoriaProduto categoria) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        categoriaService.salvarCategoria(categoria, restaurante);
        return "redirect:/admin/categorias";
    }

    @PostMapping("/excluir")
    public String excluirCategoria(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        CategoriaProduto categoria = categoriaService.buscarCategoriaPorId(id, restaurante);
        model.addAttribute("categorias", categoriaService.buscarCategoriaPorId(id, restaurante));
        categoriaService.deletarCategoria(id, restaurante);
        return "redirect:/admin/categorias";
    }
}
