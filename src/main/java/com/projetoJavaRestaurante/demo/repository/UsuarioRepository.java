package com.projetoJavaRestaurante.demo.repository;


import com.projetoJavaRestaurante.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);

//    public boolean existsByEmailUsuario(String emailUsuario);
//
//    @Query(
//            value = "SELECT * FROM meson_restaurantes.usuarios WHERE email_usuario = :emailUsuario AND senha_usuario = :senhaUsuario",
//            nativeQuery = true
//    )
//    Usuario login(
//            @Param("emailUsuario") String emailUsuario,
//            @Param("senhaUsuario") String senhaUsuario
//    );
}

