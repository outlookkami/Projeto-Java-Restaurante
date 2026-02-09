package com.kamilly.meson.service;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.ComandaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComandaService {

    private final ComandaRepository comandaRepository;

    public List<Comanda> buscarComandasAbertasMesa(Mesa mesa, Restaurante restaurante, StatusComanda statusComanda) {
        statusComanda = StatusComanda.ABERTA;
        return comandaRepository.findAllByMesaAndRestauranteAndStatus(mesa, mesa.getRestaurante(), statusComanda);
    }

}
