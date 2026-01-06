package com.projetoJavaRestaurante.demo.model;

import com.projetoJavaRestaurante.demo.model.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int id;

    @Column(nullable = false)
    private String nomeUsuario;

    @Column(nullable = false)
    private String emailUsuario;

    @Column(nullable = false)
    private String senhaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PerfilUsuario perfilUsuario;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao =  LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority>  getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(perfilUsuario.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
