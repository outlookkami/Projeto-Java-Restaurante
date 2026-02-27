package com.kamilly.meson.service;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.enums.StatusItemPedido;
import com.kamilly.meson.repository.ItemPedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> listarItensPedido(Long pedidoId) {
        List<StatusItemPedido> statusItem = List.of(StatusItemPedido.ENVIADO, StatusItemPedido.EM_PREPARO);
        return itemPedidoRepository.findAllByPedidoAndStatusItemIn(pedidoId, statusItem);
    }
}
