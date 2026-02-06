package com.kamilly.meson.repository;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComandaRepository {

    List<Comanda> findAllByRestaurante(Restaurante restaurante);

    List<Comanda> findAllByMesa(Mesa mesa);


}
