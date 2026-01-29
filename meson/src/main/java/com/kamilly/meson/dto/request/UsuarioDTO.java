package com.kamilly.meson.dto.request;

import com.kamilly.meson.model.enums.PerfilUsuario;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UsuarioDTO {
    private String nome;
    @Enumerated(EnumType.STRING)
    private PerfilUsuario perfil;
    private String email;
    private String senha;
}
