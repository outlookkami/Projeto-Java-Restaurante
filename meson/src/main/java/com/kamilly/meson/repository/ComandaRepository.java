package com.kamilly.meson.repository;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{

    List<Comanda> findAllByRestaurante(Restaurante restaurante);

    List<Comanda> findAllByMesaAndRestauranteAndStatus(Mesa mesa, Restaurante restaurante, StatusComanda statusComanda);

    boolean existsByMesaAndStatus(Long mesaId, StatusComanda status);

}
