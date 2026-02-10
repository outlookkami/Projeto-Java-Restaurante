package com.kamilly.meson.service;

import com.kamilly.meson.model.Comanda;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComandaService {

    private final ComandaRepository comandaRepository;
    private final MesaRepository mesaRepository;

    public List<Comanda> buscarComandasAbertasMesa(Long mesaId, Restaurante restaurante) {
        Mesa mesa = mesaRepository.findById(mesaId).orElseThrow(() -> new RuntimeException("Mesa não encontrada."));
        return comandaRepository.findAllByMesaAndRestauranteAndStatus(mesa, restaurante, StatusComanda.ABERTA);
    }

    public Comanda abrirComanda(Long mesaId, String nomeCliente, Restaurante restaurante) {
        Mesa mesa = mesaRepository.findByIdAndRestaurante(mesaId, restaurante).orElseThrow();
        Comanda comanda = new Comanda();
        comanda.setMesa(mesa);
        comanda.setNomeCliente(nomeCliente);
        comanda.setRestaurante(restaurante);
        comanda.setStatus(StatusComanda.ABERTA);
        comanda.setDataAbertura(LocalDateTime.now());
        mesa.setStatus(StatusMesa.OCUPADA);
        return comandaRepository.save(comanda);
    }

}
