package com.projetoJavaRestaurante.demo.model;

import com.projetoJavaRestaurante.demo.model.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
//@Builder
@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    @Column(name = "nome_usuario", nullable = false)
    private String nome;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String email;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_usuario", nullable = false)
    private PerfilUsuario perfilUsuario;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;
}
