package com.kamilly.meson.service;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.repository.MesaRepository;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final UsuarioService usuarioService;

    public Mesa listarMesas(Long id) {
        Usuario usuario = usuarioService.getUsuarioLogado();
        Restaurante restaurante = usuario.getRestaurante();

        return mesaRepository.findByRestaurante(restaurante);
    }
}
