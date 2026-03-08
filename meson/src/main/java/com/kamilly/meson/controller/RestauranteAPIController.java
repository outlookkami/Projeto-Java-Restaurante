package com.kamilly.meson.controller;

import com.kamilly.meson.dto.request.RestauranteReqDTO;
import com.kamilly.meson.service.CnpjService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cnpj")
public class RestauranteAPIController {

    private final CnpjService cnpjService;

    public RestauranteAPIController(CnpjService cnpjService) {
        this.cnpjService = cnpjService;
    }


    @GetMapping("/{cnpj}")
    public RestauranteReqDTO getCnpj(@PathVariable String cnpj) {

        return cnpjService.getCnpj(cnpj);
    }
}

//    @GetMapping("/{cnpj}")
//    public ResponseEntity<?> getCnpj(@PathVariable String cnpj) {
//        try {
//            RestauranteReqDTO resultado = cnpjService.getCnpj(cnpj);
//            return ResponseEntity.ok(resultado);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Erro ao buscar CNPJ: " + e.getMessage());
//        }
//    }
//}

//    @GetMapping("/{cnpj}")
//    public ResponseEntity<?> getCnpj(@PathVariable String cnpj) {
//
//        return cnpjService.getCnpj(cnpj);
//    }
