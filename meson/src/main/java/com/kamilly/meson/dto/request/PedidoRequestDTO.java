package com.kamilly.meson.dto.request;

import com.kamilly.meson.model.ItemPedido;
import java.util.List;

public class PedidoRequestDTO {
    private Long idComanda;
    private List<Long> produtosIds;
    private List<ItemPedido> itens;
//    public Long getIdComanda() {}
 }
