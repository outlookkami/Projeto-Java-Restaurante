package com.kamilly.meson.service;

import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Usuario getUsuarioLogado() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Usuário não autenticado");
        }

        String email = authentication.getName(); // username/email

        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

//    public Usuario getRestauranteUsuarioLogado(){
//        Optional<Usuario> usuarioRestaurante = usuarioRepository.findByEmailAndRestaurante(getRestauranteUsuarioLogado().getEmail(), getRestauranteUsuarioLogado().getId());
//        return usuarioRestaurante.orElseThrow(() -> new RuntimeException("Não foi encontrado um restaurante nos dados do usuário."));
//    }

//    Usuario usuario = usuarioRepository
//            .findByEmailAndRestaurante(email, idRestaurante)
//            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"))
}
