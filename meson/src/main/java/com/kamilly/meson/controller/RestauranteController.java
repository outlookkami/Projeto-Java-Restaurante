package com.kamilly.meson.controller;

import com.kamilly.meson.dto.request.RestauranteReqDTO;
import com.kamilly.meson.service.CnpjService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cnpj")
public class RestauranteController {

    private final CnpjService cnpjService;

    public RestauranteController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }

    @GetMapping("/{cnpj}")
//    @ResponseBody
    public RestauranteReqDTO getCnpj(@PathVariable String cnpj) {
        return cnpjService.getCnpj(cnpj);
    }
}
