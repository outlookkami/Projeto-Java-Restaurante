package com.kamilly.meson.repository;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Pedido;
import com.kamilly.meson.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByComandaAndRestaurante(Comanda comanda, Restaurante restaurante);

}
