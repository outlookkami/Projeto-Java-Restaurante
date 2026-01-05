package com.projetoJavaRestaurante.demo.controller;

import com.projetoJavaRestaurante.demo.service.CadastroService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class CadastroController {
    private CadastroService cadastroService;

    public String register(@RequestBody CadastroRequest request){
        return cadastroService.cadastrar(request);
    }
}
