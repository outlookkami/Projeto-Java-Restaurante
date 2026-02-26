package com.kamilly.meson.service;

import com.kamilly.meson.config.UsuarioDetails;
import com.kamilly.meson.model.Produto;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import com.kamilly.meson.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.saveAndFlush(usuario);
    }

    public void salvarFuncionario(Usuario usuario, Restaurante restaurante){
        if(usuario.getPerfilUsuario() != null) {
            usuario.setPerfilUsuario(usuario.getPerfilUsuario());
        } else {
            throw new RuntimeException("Cargo não selecionado.");
        }

        String senhaPadrao = "meson123";

        usuario.setAtivo(true);
        usuario.setSenha(passwordEncoder.encode(senhaPadrao));
        usuario.setTrocarSenha(Boolean.TRUE);
        usuario.setRestaurante(restaurante);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarFuncionario(String nome, Restaurante restaurante, PerfilUsuario perfilUsuario){
        List<PerfilUsuario> perfis = perfisPermitidosCadastro(perfilUsuario);
        if((nome == null || nome.isBlank()) && perfilUsuario == null ) {
            return usuarioRepository.findAllByRestaurante(restaurante);
        }

        if(perfilUsuario != null && (nome == null || nome.isBlank())) {
            return usuarioRepository.findAllByPerfilUsuarioAndRestaurante(perfilUsuario, restaurante);
        }

        if(perfilUsuario == null) {
            return usuarioRepository.findByNomeContainingIgnoreCaseAndRestaurante(nome, restaurante);
        }
        return usuarioRepository.findByNomeContainingIgnoreCaseAndPerfilUsuarioAndRestaurante(nome, perfilUsuario, restaurante);
    }

    public Usuario buscarUsuarioEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public Usuario buscarFuncionarioPorId(Long id, Restaurante restaurante) {
        return usuarioRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    public void deleteUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public void deletarFuncionario(Long id, Restaurante restaurante) {
        Usuario funcionario = usuarioRepository.findByIdAndRestaurante(id, restaurante)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        funcionario.setAtivo(false);
        usuarioRepository.save(funcionario);
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

        usuarioRepository.save(usuarioAtualizado);
    }

    public Usuario criarAdmin(Usuario usuario, Restaurante restaurante) {
        usuario.setPerfilUsuario(PerfilUsuario.ADMIN);
        usuario.setRestaurante(restaurante);
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth== null || !auth.isAuthenticated()) {
            return null;
        }

        UsuarioDetails details = (UsuarioDetails) auth.getPrincipal();
        return details.getUsuario();
    }

    public List<PerfilUsuario> perfisPermitidosCadastro(PerfilUsuario perfilUsuario) {
        if (perfilUsuario == PerfilUsuario.ADMIN) {
            return List.of(PerfilUsuario.ADMIN, PerfilUsuario.GARCOM, PerfilUsuario.COZINHA);
        }

        if (perfilUsuario == PerfilUsuario.ADMIN_GERAL) {
            return List.of(PerfilUsuario.ADMIN_GERAL, PerfilUsuario.ADMIN, PerfilUsuario.GARCOM, PerfilUsuario.COZINHA);
        }

        return List.of();
    }

    public List<Usuario> listarFuncionarios(Restaurante restaurante) {
        List<PerfilUsuario> perfisFuncionarios = List.of(PerfilUsuario.ADMIN, PerfilUsuario.GARCOM, PerfilUsuario.COZINHA);
        return usuarioRepository.findByRestauranteAndPerfilUsuarioIn(restaurante, perfisFuncionarios);
    }
}
