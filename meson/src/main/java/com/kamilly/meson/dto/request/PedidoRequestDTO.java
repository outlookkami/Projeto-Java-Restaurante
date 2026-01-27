package com.kamilly.meson.dto.request;

import com.kamilly.meson.model.ItemPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {
    private Long idComanda;
    private List<Long> produtosIds;
    private List<ItemPedido> itens;
//    public Long getIdComanda() {}
 }
