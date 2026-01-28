package com.kamilly.meson.service;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deleteUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Long id, Usuario usuario) {
        Usuario usuarioModel = usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado"));
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioModel.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioModel.getNome())
                .perfilUsuario(usuario.getPerfilUsuario() != null ? usuario.getPerfilUsuario() : usuarioModel.getPerfilUsuario())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : usuarioModel.getSenha())
                .id(usuarioModel.getId())
                .build();

        usuarioRepository.saveAndFlush(usuarioAtualizado);
    }

    public Usuario criarAdmin(Usuario usuario, Restaurante restaurante) {
        usuario.setPerfilUsuario(PerfilUsuario.ADMIN);
        usuario.setRestaurante(restaurante);
        return usuario;
    }

//    Usuario usuario = usuarioRepository
//            .findByEmailAndRestaurante(email, idRestaurante)
//            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
}
