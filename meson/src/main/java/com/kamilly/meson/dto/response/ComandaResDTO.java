package com.kamilly.meson.dto.response;

import com.kamilly.meson.model.enums.StatusComanda;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ComandaResDTO {
    private Long id;
    private String nomeCliente;
    private short numeroMesa;
    private StatusComanda status;
    private BigDecimal valor;
    private List<PedidoResDTO> pedidos;

    public ComandaResDTO(Long id, String nomeCliente, short numeroMesa, StatusComanda status, BigDecimal valor, List<PedidoResDTO> pedidos) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.numeroMesa = numeroMesa;
        this.status = status;
        this.valor = valor;
        this.pedidos = pedidos;
    }
}
