package com.kamilly.meson.repository;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.enums.StatusItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findAllByPedidoAndStatusItemIn(Long pedidoId, List<StatusItemPedido> statusItem);
}
