package com.kamilly.meson.repository;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{

    Optional<Comanda> findByIdAndRestaurante(Long comandaId, Restaurante restaurante);

    List<Comanda> findAllByRestaurante(Restaurante restaurante);

    List<Comanda> findAllByMesaAndRestauranteAndStatus(Mesa mesa, Restaurante restaurante, StatusComanda statusComanda);

    List<Comanda> findAllByRestauranteAndStatus(Restaurante restaurante, StatusComanda statusComanda);

    boolean existsByMesaAndStatus(Long mesaId, StatusComanda status);

    @Query("""
            SELECT DISTINCT c FROM Comanda c
            LEFT JOIN FETCH c.pedidos p 
            LEFT JOIN FETCH p.itens 
            WHERE c.mesa = :mesa
            AND c.restaurante = :restaurante
            AND c.status = :status
            """)
    List<Comanda> buscarAbertasComPedidos(
            @Param("mesa") Mesa mesa,
            @Param("restaurante") Restaurante restaurante,
            @Param("status") StatusComanda status);
}
