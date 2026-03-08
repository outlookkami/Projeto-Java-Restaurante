package com.kamilly.meson.repository;

import com.kamilly.meson.dto.response.ProdutoMaisVendidoDTO;
import com.kamilly.meson.model.ItemPedido;
import com.kamilly.meson.model.enums.StatusItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    List<ItemPedido> findAllByPedidoIdAndStatusItemIn(Long pedidoId, List<StatusItemPedido> statusItem);

    List<ItemPedido> findByPedidoId(Long pedidoId);

    List<ItemPedido> findByPedidoComandaId(Long comandaId);



    @Query("""
            SELECT new com.kamilly.meson.dto.response.ProdutoMaisVendidoDTO(
                i.produto.nome,
                SUM(i.quantidade)            
            )
            FROM ItemPedido i
            WHERE i.pedido.dataCriacao BETWEEN :inicio AND :fim
            AND i.pedido.status = 'ENTREGUE'
            GROUP BY i.produto.nome
            ORDER BY SUM(i.quantidade) DESC          
            """)
    List<ProdutoMaisVendidoDTO> produtosMaisVendidos(LocalDateTime inicio, LocalDateTime fim);
}
