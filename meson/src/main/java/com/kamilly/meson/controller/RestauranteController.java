package com.kamilly.meson.controller;

import com.kamilly.meson.dto.request.RestauranteDTO;
import com.kamilly.meson.service.CnpjService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cnpj")
public class RestauranteController {

    private final CnpjService cnpjService;

    public RestauranteController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}")
    public RestauranteDTO getCnpj(@PathVariable String cnpj) {
        return cnpjService.getCnpj(cnpj);
    }
}
