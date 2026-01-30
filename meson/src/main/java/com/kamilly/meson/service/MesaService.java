package com.kamilly.meson.service;

import com.kamilly.meson.model.Mesa;
import com.kamilly.meson.repository.MesaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final MesaRepository mesaRepository;
    private final UsuarioService usuarioService;

//    public Mesa listarMesas(Long id) {
//        Usuario usuario = usuarioService.getUsuarioLogado();
//        Restaurante restaurante = usuario.getRestaurante();
//
//        return mesaRepository.findByRestaurante(restaurante);
//    }

    public List<Mesa> listarMesas(){
        return mesaRepository.findAll();
    }

    public void salvarMesa(Mesa mesa){
        mesaRepository.save(mesa);
    }
}
