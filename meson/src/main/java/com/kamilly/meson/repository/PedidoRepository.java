package com.kamilly.meson.repository;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByComandaAndRestaurante(Comanda comanda, Restaurante restaurante);

    List<Pedido> findAllByRestauranteAndStatus(Restaurante restaurante, StatusPedido statusPedido);

    @Query(""" 
            SELECT DISTINCT p
            FROM Pedido p
            LEFT JOIN FETCH p.itens i
            LEFT JOIN FETCH i.produto
            WHERE p.comanda.id = :comandaId
            """)
    List<Pedido> buscarPedidos(@Param("comandaId") Long comandaId, Restaurante restaurante);

    @Query("""
            SELECT MAX(p.numeroDia)
            FROM Pedido p
            WHERE p.dataReferencia = :hoje
            """)
    Integer buscarUltimoNumeroDia(LocalDate hoje);
}
