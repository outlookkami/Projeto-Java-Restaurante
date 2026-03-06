package com.kamilly.meson.repository;

import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.enums.StatusItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findAllByPedidoIdAndStatusItemIn(Long pedidoId, List<StatusItemPedido> statusItem);

    List<ItemPedido> findByPedidoId(Long pedidoId);

    List<ItemPedido> findByPedidoComandaId(Long comandaId);

//    @Query("""
//            SELECT ip
//            FROM ItemPedido ip
//            WHERE ip.pedido.comanda.id = :comandaId
//            """)
//    List<ItemPedido> buscarItensPorComanda(@Param("comandaId") Long comandaId);
}
