package com.kamilly.meson.service;

import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

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
}
