package com.kamilly.meson.model;

import com.kamilly.meson.model.enums.PerfilUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_usuario", nullable = false, length = 80)
    private String nome;

    @Column(name = "email_usuario", unique = true, nullable = false)
    private String email;

    @Column(name = "senha_usuario", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_usuario", nullable = false, length = 20)
    private PerfilUsuario perfilUsuario;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao =  LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

//    Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + perfilUsuario.name())
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override public boolean isAccountNonExpired() { return true;}
    @Override public boolean isAccountNonLocked() { return true;}
    @Override public boolean isCredentialsNonExpired() { return true;}
    @Override public boolean isEnabled() { return true;}

}
