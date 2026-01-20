package com.kamilly.meson.controller;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String listarCategorias(Model model){
        List<CategoriaProduto> categorias = categoriaService.listarCategorias();
        model.addAttribute("categorias", categorias);

        return "categorias";
    }

//    @GetMapping
//    public ModelAndView listaCategoria() {
//        CategoriaProduto cat1 = new CategoriaProduto();
//        cat1.setId(2L);
//        cat1.setNome("categoria 1");
//        cat1.setAtiva(true);
//
//        CategoriaProduto cat2 = new CategoriaProduto();
//        cat1.setId(3L);
//        cat1.setNome("categoria 2");
//        cat1.setAtiva(true);
//
//        List<CategoriaProduto> categorias = new ArrayList<>();
//        categorias.add(cat1);
//        categorias.add(cat2);
//
//        ModelAndView model = new ModelAndView("/categorias");
//        model.addObject("categorias", categorias);
//        return model;
//    }
}
