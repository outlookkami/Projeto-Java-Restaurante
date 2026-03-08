package com.kamilly.meson.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoMaisVendidoDTO {
    private String nomeProduto;
    private Long quantVendas;
}
