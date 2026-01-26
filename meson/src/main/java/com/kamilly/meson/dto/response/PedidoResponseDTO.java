package com.kamilly.meson.dto.response;

import com.kamilly.meson.model.enums.StatusPedido;

import java.math.BigDecimal;

public class PedidoResponseDTO {
    private Long idPedido;
    private Long idComanda;
    private Integer numeroMesa;
    private StatusPedido statusPedido;
    private BigDecimal total;
}
