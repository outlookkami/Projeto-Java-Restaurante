package com.kamilly.meson.controller;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.repository.CategoriaRepository;
import com.kamilly.meson.service.CategoriaService;
import com.kamilly.meson.service.ImagemService;
import com.kamilly.meson.service.ProdutoService;
import com.kamilly.meson.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final UsuarioService usuarioService;
    private final ImagemService imagemService;
    private final CategoriaService categoriaService;
    private final CategoriaRepository categoriaRepository;

    @GetMapping
    public String listarProdutos(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Restaurante restaurante = usuarioLogado.getRestaurante();

        List<CategoriaProduto> categorias = categoriaRepository.findAllByRestaurante(restaurante);
        model.addAttribute("produtos", produtoService.listarProdutos(restaurante));
        model.addAttribute("categorias", categoriaService.listarCategorias(restaurante));
        model.addAttribute("produto", new  Produto());
        return "admin/produtos";
    }

    @GetMapping("/novo")
    public String novoProduto(Model model, @AuthenticationPrincipal Usuario usuarioLogado) {
        Produto produto = new Produto();
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();

        List<CategoriaProduto> categorias = categoriaRepository.findAllByRestaurante(restaurante);
        model.addAttribute("produtos", produto);
        model.addAttribute("categorias", categorias);
        model.addAttribute("mostrarModal", true);
        return "admin/produtos";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        Produto produto = produtoService.buscarProdutoPorId(id, restaurante);
        model.addAttribute("produtos", produto);
        model.addAttribute("mostrarModal", true);
        return "admin/produtos";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, @RequestParam("imagem") MultipartFile imagem, @AuthenticationPrincipal Usuario usuarioLogado) {
        String imagemUrl = imagemService.salvar(imagem);
        produto.setImagemUrl(imagemUrl);
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        produtoService.salvarProduto(produto, restaurante);
        return "redirect:/admin/produtos";
    }

    @PostMapping("/excluir")
    public String excluirProduto(@PathVariable Long id) {
        Restaurante restaurante = usuarioService.getUsuarioLogado().getRestaurante();
        produtoService.deletarProduto(id, restaurante);
        return "redirect:/admin/produtos";
    }
}
