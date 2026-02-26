package com.kamilly.meson.dto.response;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.enums.StatusPedido;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class PedidoResDTO {
    private StatusPedido status;
    private BigDecimal valor;
    private List<ItemPedidoResDTO> itens;

    public PedidoResDTO(StatusPedido status, BigDecimal valor, List<ItemPedidoResDTO> itens) {
        this.status = status;
        this.valor = valor;
        this.itens = itens;
    }
}
