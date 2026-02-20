package com.kamilly.meson.controller.Cozinha;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cozinha/pedidos")
@RequiredArgsConstructor
public class PedidosCozinhaController {

    @GetMapping
    public String listarPedidosCozinha() {
        return "cozinha/pedidos";
    }
}
