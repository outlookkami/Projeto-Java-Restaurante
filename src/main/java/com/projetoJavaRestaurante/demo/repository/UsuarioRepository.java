package com.projetoJavaRestaurante.demo.repository;


import com.projetoJavaRestaurante.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findById(long id);
    //    Usuario findByEmail(String email);
//
//    public boolean existsByEmail(String email);
//
//    @Query(
//            value = "SELECT * FROM meson_restaurantes.usuarios WHERE email_usuario = :email AND senha_usuario = :senha",
//            nativeQuery = true
//    )
//    Usuario login(
//            @Param("email") String email,
//            @Param("senha") String senha
//    );
}

