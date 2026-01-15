package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.dto.response.CategoriaResponseDTO;
import com.projetoJavaRestaurante.demo.service.CategoriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("categorias", service.listar());
        return "categoria/lista";
    }

    @GetMapping("/nova")
    public String nova(Model model) {
        model.addAttribute("categoriaDTO", new CategoriaResponseDTO());
        return "categoria/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute CategoriaResponseDTO categoriaResponseDto) {
        service.salvar(categoriaResponseDto);
        return "redirect:/categorias";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
       var categoria = service.buscarPorId(id);
       CategoriaResponseDTO categoriaResponseDto = new CategoriaResponseDTO();
       categoriaResponseDto.setId(categoria.getId());
       categoriaResponseDto.setNome(categoria.getNome());
       model.addAttribute("categoriaDTO", categoriaResponseDto);
       return "categoria/formulario";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@PathVariable("id") Long id, @ModelAttribute CategoriaResponseDTO categoriaResponseDto) {
        service.atualizar(id, categoriaResponseDto);
        return "redirect:/categorias";
    }

    @GetMapping("/desativar/{id}")
    public String desativar(@PathVariable Long id){
        service.desativar(id);
        return "redirect:/categorias";
    }
}
