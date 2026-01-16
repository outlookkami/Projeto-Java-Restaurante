package com.projetoJavaRestaurante.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rota")
public class CursoController {

    @GetMapping("/")
    public String primeiraMsg() {
        return "Funcionou";
    }
}
