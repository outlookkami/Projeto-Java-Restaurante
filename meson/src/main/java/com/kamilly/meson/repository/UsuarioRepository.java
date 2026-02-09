package com.kamilly.meson.repository;

import com.kamilly.meson.dto.request.RestauranteReqDTO;
import com.kamilly.meson.model.Restaurante;
import com.kamilly.meson.model.Usuario;
import com.kamilly.meson.model.enums.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByIdAndRestaurante(Long id, Restaurante restaurante);

    List<Usuario> findAllByRestaurante(Restaurante restaurante);

    List<Usuario> findAllByPerfilUsuarioAndRestaurante(PerfilUsuario perfilUsuario, Restaurante restaurante);

    List<Usuario> findByNomeContainingIgnoreCaseAndRestaurante(String nome, Restaurante restaurante);

    List<Usuario> findByNomeContainingIgnoreCaseAndPerfilUsuarioAndRestaurante(String nome, PerfilUsuario perfilUsuario, Restaurante restaurante);

    List<Usuario> findByRestauranteAndPerfilUsuarioIn(Restaurante restaurante, List<PerfilUsuario> perfis);

    @Transactional //@Transactional para que caso dê erro, o email não seja excluído
    void deleteByEmail(String email);
}
