package com.kamilly.meson.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemPedidoResDTO {
    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal subtotal;

    public ItemPedidoResDTO(String nomeProduto, Integer quantidade, BigDecimal subtotal) {
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.subtotal = subtotal;
    }
}
