package com.kamilly.meson.service;

import com.kamilly.meson.model.CategoriaProduto;
import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.StatusComanda;
import com.kamilly.meson.model.enums.StatusMesa;
import com.kamilly.meson.repository.ComandaRepository;
import com.kamilly.meson.repository.MesaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final UsuarioService usuarioService;
    private final ComandaRepository comandaRepository;

    public List<Mesa> listarMesas(Restaurante restaurante) {
        return mesaRepository.findAllByRestaurante(restaurante);
    }

    public Mesa buscarMesaPorId(Long id, Restaurante restaurante){
        return mesaRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada."));
    }

    public void salvarMesa(Mesa mesa, Restaurante restaurante){
        mesa.setRestaurante(restaurante);
        mesaRepository.save(mesa);
    }

    @Transactional
    public void deletarMesa(Long id, Restaurante restaurante){
        Mesa mesa = mesaRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada."));
        mesaRepository.delete(mesa);
    }
}
