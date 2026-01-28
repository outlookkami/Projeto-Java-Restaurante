package com.kamilly.meson.repository;

import com.kamilly.meson.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndRestaurante(String email, Long idRestaurante);


    @Transactional //@Transactional para que caso dê erro, o email não seja excluído
    void deleteByEmail(String email);


}
